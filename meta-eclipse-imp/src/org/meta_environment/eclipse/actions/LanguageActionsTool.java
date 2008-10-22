package org.meta_environment.eclipse.actions;

import toolbus.adapter.eclipse.EclipseTool;

public class LanguageActionsTool extends EclipseTool {
	private static final String TOOL_NAME = "language-actions";
	
	private static class InstanceKeeper{
		private static LanguageActionsTool sInstance = new LanguageActionsTool();
		static{
			sInstance.connect();
		}
	}
	
	private LanguageActionsTool() {
		super(TOOL_NAME);
	}
	
	public static LanguageActionsTool getInstance(){
		return InstanceKeeper.sInstance;
	}
	
	public void performAction (String Action, String language, String Filename) {
		this.sendEvent(factory.make("perform-action(<str>,<str>,<str>)", Action, language, Filename));
	}
	
}
