package org.meta_environment.eclipse;

import java.net.InetAddress;
import java.net.UnknownHostException;

import toolbus.ToolBusEclipsePlugin;
import toolbus.adapter.java.AbstractJavaTool;
import toolbus.adapter.java.JavaToolBridge;
import aterm.ATerm;
import aterm.pure.PureFactory;

public class Tool extends AbstractJavaTool{
	protected static final String TIME_OUT = "time-out";

	protected static PureFactory factory = ToolBusEclipsePlugin.getFactory();

	private static InetAddress host;
	static{
		try{
			host = InetAddress.getLocalHost();
		}catch(UnknownHostException uhex){
			uhex.printStackTrace();
		}
	}
	private final static int port = ToolBusEclipsePlugin.getPort();
	
	private final String name;

	public Tool(String name){
		this.name = name;
	}
	
	public void connect(){
		try{
			//connect(new String[0]);
			connectDirectly();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void connectDirectly() throws Exception{
		connectDirectly(ToolBusEclipsePlugin.getToolBus(), Thread.currentThread().getContextClassLoader(), name, -1);
	}
	
	public String getName() {
		return name;
	}

	public void connect(String[] args) throws Exception{
		JavaToolBridge bridge = new JavaToolBridge(factory, this, name, -1, host, port);
		setToolBridge(bridge);
		bridge.run();	
	}

	public void receiveTerminate(ATerm aTerm){
		// Intentionally left empty.
	}
	
	public void receiveAckEvent(ATerm aTerm){
		// Intentionally left empty.
	}
}
