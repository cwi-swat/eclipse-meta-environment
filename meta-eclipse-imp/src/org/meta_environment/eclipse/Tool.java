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

	private ToolBusEclipsePlugin toolbus;

	protected ATerm acknowlegdement;

	public Tool(String name) {
		this.name = name;
		
		// before a tool does anything, we must make sure that
		// the ToolBus is alive and kicking.
		
		if (toolbus == null || port == -1) {
			toolbus = ToolBusEclipsePlugin.getInstance();
			try {
				factory = (PureFactory) toolbus.getFactory();
				// TODO find a better way of waiting for the ToolBus to start up
				while ((port = toolbus.getPort()) == -1) {
					Thread.sleep(1000);
				}
				host = java.net.InetAddress.getLocalHost();
				
				connect();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void connect() throws Exception {
		connect(new String[0]);
	}

	public void connect(String[] args) throws Exception {
		final JavaToolBridge bridge = new JavaToolBridge(factory,
				AbstractTool.REMOTETOOL, this, name, -1, host, port);
		setToolBridge(bridge);
		bridge.run();	
	}

	public void receiveTerminate(ATerm aTerm) {
		toolBridge.terminate();
	}
	
	/**
	 * Blocking event/return mechanism for communicating with the ToolBus.
	 * By default, this call will fail after a few minutes returning 'time-out'
	 * as an ATerm. Since tools may block the entire Eclipse Platform, this will
	 * allow you to inspect the current state of system via the UI.
	 * 
	 * @param term will be send as an event to the Bus
	 * @return the acknowledgement data send back from the ToolBus script.
	 */
	protected ATerm call(ATerm term) {
		return call(term, EVENT_FAILURE_TIMEOUT);
	}
	
	/**
	 * Blocking event/return mechanism for communicating with the ToolBus.
	 * 
	 * @param term will be send as an event to the Bus
	 * @param timeOut milliseconds will be waited until failure.
	 * @return the acknowledgement data send back from the ToolBus script.
	 */
	protected ATerm call(ATerm term, int timeOut) {
		resetAck();
		sendEvent(term);
		waitForAck(timeOut);
		return acknowlegdement;
	}
	
	private synchronized void waitForAck(int timeOut) {
		
		while (acknowlegdement == null) {
			try {
				wait(timeOut);
			
				if (acknowlegdement == null) {
					acknowlegdement = factory.parse(TIME_OUT);
				}
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void receiveAckEvent(ATerm aTerm) {
		synchronized (this) {
			acknowlegdement = aTerm;
			notifyAll();
		}
	}
	
	private void resetAck() {
		acknowlegdement = null;
	}
}
