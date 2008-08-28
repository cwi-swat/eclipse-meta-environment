package org.meta_environment.eclipse.files;

import org.meta_environment.eclipse.Tool;

import aterm.ATermList;

public class IO extends Tool {

	private static class InstanceKeeper {
		private static IO sInstance = new IO();
		static {
			sInstance.connect();
		}
	}
	
	private IO() {
		super("io");
	}

	static public IO getInstance() {
		return InstanceKeeper.sInstance;
	}
	
	public ATermList relativeToAbsolute (ATermList directories) {
		return null;
	}
	
}
