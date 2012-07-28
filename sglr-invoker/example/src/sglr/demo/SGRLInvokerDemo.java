package sglr.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import sglr.SGLRInvoker;
import aterm.pure.PureFactory;

public class SGRLInvokerDemo{
	private final static PureFactory factory = new PureFactory();
	
	public static void main(String[] args) throws IOException{
		SGLRInvoker sglrInvoker = SGLRInvoker.getInstance();
		
		// A few example calls that parse from a string.
		byte[] data = sglrInvoker.parseFromString("true", "example/booleanTerm.trm.tbl"); // <--
		System.out.println(factory.readFromTextFile(new ByteArrayInputStream(data)));
		
		data = sglrInvoker.parseFromString("false", "example/booleanTerm.trm.tbl"); // <--
		System.out.println(factory.readFromTextFile(new ByteArrayInputStream(data)));
		
		data = sglrInvoker.parseFromString("true & false", "example/booleanTerm.trm.tbl"); // <--
		System.out.println(factory.readFromTextFile(new ByteArrayInputStream(data)));
		
		
		// Some more example calls that parse from an inputstream.
		InputStream stream = new ByteArrayInputStream("true".getBytes());
		data = sglrInvoker.parseFromStream(stream, "example/booleanTerm.trm.tbl"); // <--
		System.out.println(factory.readFromTextFile(new ByteArrayInputStream(data)));

		stream = new ByteArrayInputStream("false".getBytes());
		data = sglrInvoker.parseFromStream(stream, "example/booleanTerm.trm.tbl"); // <--
		System.out.println(factory.readFromTextFile(new ByteArrayInputStream(data)));

		stream = new ByteArrayInputStream("true & false".getBytes());
		data = sglrInvoker.parseFromStream(stream, "example/booleanTerm.trm.tbl"); // <--
		System.out.println(factory.readFromTextFile(new ByteArrayInputStream(data)));
		
		
		sglrInvoker.stop(); // Stop the invoker.
	}
}
