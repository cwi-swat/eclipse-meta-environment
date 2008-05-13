package org.meta_environment.eclipse;

import java.net.InetAddress;
import java.net.UnknownHostException;

import toolbus.ToolBusEclipsePlugin;
import toolbus.adapter.AbstractTool;
import toolbus.adapter.java.JavaToolBridge;
import aterm.ATerm;
import aterm.pure.PureFactory;

public class Tool extends AbstractTool {
	protected static final String TIME_OUT = "time-out";

	protected static final int EVENT_FAILURE_TIMEOUT = 1000 * 10;

	protected static PureFactory factory;

	protected String name = "tool";

	protected static int port = -1;

	protected static InetAddress host;

	protected ATerm acknowlegdement;

	public Tool(String name) {
		this.name = name;
		
		factory = ToolBusEclipsePlugin.getFactory();
		port = ToolBusEclipsePlugin.getPort();
		try{
			host = InetAddress.getLocalHost();
			
			connect(new String[0]);
		}catch (UnknownHostException e){
			e.printStackTrace();
		}catch (InterruptedException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}

	public void connect(String[] args) throws Exception {
		JavaToolBridge bridge = new JavaToolBridge(factory, AbstractTool.REMOTETOOL, this, name, -1, host, port);
		setToolBridge(bridge);
		bridge.run();	
	}

	public void receiveTerminate(ATerm aTerm) {
		toolBridge.terminate();
	}
	
	public void receiveAckEvent(ATerm aTerm) {
		synchronized (this) {
			acknowlegdement = aTerm;
			notifyAll();
		}
	}
}
