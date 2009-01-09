package sglr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class SGLRInvoker implements Runnable{
	static{
		try{
	        System.loadLibrary("ATerm");
	        System.loadLibrary("ConfigAPI");
	        System.loadLibrary("ErrorAPI");
	        System.loadLibrary("ptable");
	        System.loadLibrary("mept");
	        System.loadLibrary("PTMEPT");
	        System.loadLibrary("sglr");
	        System.loadLibrary("SGLRInvoker");
	    }catch(UnsatisfiedLinkError x){
	        throw new RuntimeException(x);
	    }
	}
	
	private volatile static SGLRInvoker instance = null;
	
	private final NotifiableLock readerLock = new NotifiableLock();
	private final NotifiableLock readerDoneLock = new NotifiableLock();
	private final Thread readerThread;
	private volatile boolean running;
	
	private ByteBuffer inputString;
	private String parseTableName;
	
	private byte[] result;
	
	private SGLRInvoker(){
		super();
		
		readerThread = new Thread(this);
		running = false;
	}
	
	public static SGLRInvoker getInstance(){
		if(instance == null){
			synchronized(SGLRInvoker.class){
				if(instance == null){
					instance = new SGLRInvoker();
					instance.start();
				}
			}
		}
		
		return instance;
	}
	
	private void start(){
		running = true;
		readerThread.start();
	}
	
	public void stop(){
		running = false;
		
		synchronized(readerLock){
			readerLock.notified = true;
			readerLock.notify();
		}
	}
	
	public void run(){
		initialize();
		
		while(running){
			synchronized(readerLock){
				while(!readerLock.notified){
					try{
						readerLock.wait();
					}catch(InterruptedException irex){
						// Ignore this; we don't want to know.
					}
					
					if(!running) return;
				}
				readerLock.notified = false;
			}
			
			// Invoke the parser.
			parse();
			
			// Get the result.
			result = getResult();
			
			// Cleanup.
			inputString = null;
			parseTableName = null;
			
			synchronized(readerDoneLock){
				readerDoneLock.notified = true;
				readerDoneLock.notify();
			}
		}
	}
	
	private byte[] buffer = new byte[8192]; // Shared / locked.
	
	public synchronized byte[] parseFromStream(InputStream inputStringStream, String parseTableName) throws IOException{
		if(parseTableName == null) throw new IllegalArgumentException("ParseTableName must not be null.");
		
		ByteArrayOutputStream inputStringData = new ByteArrayOutputStream();
		
		int bytesRead;
		while((bytesRead = inputStringStream.read(buffer)) != -1){
			inputStringData.write(buffer, 0, bytesRead);
		}
		
		return reallyParse(inputStringData.toByteArray(), parseTableName);
	}
	
	public byte[] parse(String inputString, String parseTableName){
		if(inputString == null) throw new IllegalArgumentException("InputString must not be null.");
		if(parseTableName == null) throw new IllegalArgumentException("ParseTableName must not be null.");
		
		return reallyParse(inputString.getBytes(), parseTableName);
	}
	
	private ByteBuffer cachedInputStringBuffer = ByteBuffer.allocateDirect(65536);
	
	private ByteBuffer getInputStringBuffer(int requiredSize){
		int realRequiredSize = requiredSize + 1; // Need one more for \0 termination.
		
		int cachedBufferCapacity = cachedInputStringBuffer.capacity();
		if(realRequiredSize > cachedBufferCapacity){
			cachedInputStringBuffer = ByteBuffer.allocateDirect(realRequiredSize);
		}
		
		cachedInputStringBuffer.clear();
		
		return cachedInputStringBuffer;
	}
	
	private byte[] reallyParse(byte[] inputString, String parseTableName){
		synchronized(readerDoneLock){
			ByteBuffer inputStringBuffer = getInputStringBuffer(inputString.length);
			inputStringBuffer.put(inputString);
			inputStringBuffer.put((byte) 0);
			inputStringBuffer.flip();
			this.inputString = inputStringBuffer;
			this.parseTableName = parseTableName;
			
			synchronized(readerLock){
				readerLock.notified = true;
				readerLock.notify();
			}
		
			/* Wait for it. */
			while(!readerDoneLock.notified){
				try{
					readerDoneLock.wait();
				}catch(InterruptedException irex){
					// Ignore this; we don't want to know.
				}
			}
			readerDoneLock.notified = false;
		}
		
		byte[] parseTree = result;
		
		result = null; // Cleanup.
		
		return parseTree;
	}
	
	private byte[] getResult(){
		ByteBuffer buffer = getResultData();
		
		byte[] data = new byte[buffer.capacity()];
		buffer.get(data);
		return data;
	}
	
	private ByteBuffer getInputString(){
		return inputString;
	}
	
	private String getParseTableName(){
		return parseTableName;
	}
	
	private native void initialize();
	
	private native void parse();
	
	private native ByteBuffer getResultData();
	
	private static class NotifiableLock{
		public boolean notified = false;
	}
}
