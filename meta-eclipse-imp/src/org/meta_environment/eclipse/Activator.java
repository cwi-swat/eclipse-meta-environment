package org.meta_environment.eclipse;

import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.ui.IStartup;
import org.meta_environment.eclipse.actions.LanguageActionsTool;
import org.meta_environment.eclipse.editors.EditorTool;
import org.meta_environment.eclipse.errors.ErrorViewer;
import org.meta_environment.eclipse.files.Resources;

public class Activator extends PluginBase implements IStartup {
	public static final String PLUGIN_ID = "org.meta_environment";
	
	public final LanguageActionsTool languageActionsTool;

	public Activator(){
		super();
		
		languageActionsTool = SingletonLanguageActionTools.languageActionsTool;
	}
	
	private static class SingletonLanguageActionTools{
		private final static LanguageActionsTool languageActionsTool = new LanguageActionsTool();
	}
	
	private static class InstanceKeeper{
		public final static Activator instance = new Activator();
	}

	public static Activator getInstance(){
		return InstanceKeeper.instance;
	}

	public void earlyStartup() {
		new ErrorViewer();
		new Resources();
		new EditorTool();
	}

	public String getID() {
		return PLUGIN_ID;
	}

	public LanguageActionsTool getTheLanguageActionsTool(){
		return languageActionsTool;
	}
}
