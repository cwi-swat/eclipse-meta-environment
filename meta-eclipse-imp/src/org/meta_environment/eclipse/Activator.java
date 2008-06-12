package org.meta_environment.eclipse;

import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.ui.IStartup;
import org.meta_environment.eclipse.actions.LanguageActionsTool;
import org.meta_environment.eclipse.editors.EditorTool;
import org.meta_environment.eclipse.errors.ErrorViewer;
import org.meta_environment.eclipse.files.Resources;

public class Activator extends PluginBase implements IStartup {
	public static final String PLUGIN_ID = "org.meta_environment";
	
	public Activator(){
		super();
	}
	
	private static class InstanceKeeper{
		public final static Activator instance = new Activator();
	}

	public static Activator getInstance(){
		return InstanceKeeper.instance;
	}

	public void earlyStartup() {
		ErrorViewer.getInstance();
		Resources.getInstance().identifyAllResources();
		EditorTool.getInstance();
		LanguageActionsTool.getInstance();
	}

	public String getID() {
		return PLUGIN_ID;
	}
}
