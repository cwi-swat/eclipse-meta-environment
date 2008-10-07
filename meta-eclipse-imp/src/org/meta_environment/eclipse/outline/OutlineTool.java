package org.meta_environment.eclipse.outline;

import toolbus.adapter.eclipse.EclipseTool;

public class OutlineTool extends EclipseTool{
	private final static String TOOL_NAME = "outline-tool";
	
	private volatile static OutlineTool instance = null;
	
	public OutlineTool(){
		super(TOOL_NAME);
	}
	
	public static OutlineTool getInstance(){
		if(instance == null){
			synchronized(OutlineTool.class){
				if(instance == null){ // DCL works on volatile fields.
					instance = new OutlineTool();
					instance.connect();
				}
			}
		}
		
		return instance;
	}
}
