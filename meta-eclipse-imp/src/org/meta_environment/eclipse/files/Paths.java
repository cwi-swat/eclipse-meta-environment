package org.meta_environment.eclipse.files;

import org.meta_environment.eclipse.Tool;

import aterm.ATermList;

public class Paths extends Tool {

	private static class InstanceKeeper {
		private static Paths sInstance = new Paths();
		static {
			sInstance.connect();
		}
	}
	
	private Paths() {
		super("paths");
	}

	static public Paths getInstance() {
		return InstanceKeeper.sInstance;
	}
	
	public ATermList relativeToAbsolute (ATermList directories) {
		return null;
	}
	
}
