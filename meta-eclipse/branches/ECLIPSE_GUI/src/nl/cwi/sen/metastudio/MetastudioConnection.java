package nl.cwi.sen.metastudio;

import nl.cwi.sen.metastudio.bridge.UserEnvironmentBridge;
import nl.cwi.sen.metastudio.datastructures.DatastructuresFactory;

public class MetastudioConnection {
	private static UserEnvironmentBridge _bridge;
	private static DatastructuresFactory _factory;
	
	public MetastudioConnection() {
	}
	
	public MetastudioConnection(UserEnvironmentBridge bridge, DatastructuresFactory factory) {
		_bridge = bridge;
		_factory = factory;
	}

	public UserEnvironmentBridge getBridge() {
		return _bridge;
	}

	public DatastructuresFactory getFactory() {
		return _factory;
	}
}
