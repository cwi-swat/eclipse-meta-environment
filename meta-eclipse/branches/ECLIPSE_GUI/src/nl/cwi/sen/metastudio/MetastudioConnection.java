package nl.cwi.sen.metastudio;

import nl.cwi.sen.metastudio.bridge.UserEnvironmentBridge;
import aterm.ATermFactory;

public class MetastudioConnection {
	private static UserEnvironmentBridge _bridge;
	private static ATermFactory _factory;
	
	public MetastudioConnection() {
	}
	
	public MetastudioConnection(UserEnvironmentBridge bridge, ATermFactory factory) {
		_bridge = bridge;
		_factory = factory;
	}

	public UserEnvironmentBridge getBridge() {
		return _bridge;
	}

	public ATermFactory getFactory() {
		return _factory;
	}
}
