package org.meta_environment.eclipse.actions;

import org.meta_environment.eclipse.Tool;

public class LanguageActionsTool extends Tool {

	public LanguageActionsTool() {
		super("language-actions");
	}
	
	public void PerformAction (String Action, String language, String Filename) {
		this.sendEvent(factory.make("perform-action(<str>,<str>,<str>)", Action, language, Filename));
	}

}
