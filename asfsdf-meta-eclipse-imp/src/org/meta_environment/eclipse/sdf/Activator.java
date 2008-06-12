package org.meta_environment.eclipse.sdf;

import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.ui.IStartup;
import org.meta_environment.eclipse.terms.TermEditorTools;

public class Activator extends PluginBase implements IStartup {
	public static final String kPluginID = "sdf_meta_eclipse";
	public static final String kLanguageName = "SDF";
	
	public Activator(){
		super();
	}
	
	private static class InstanceKeeper{
		public static Activator sPlugin = new Activator();
	}

	public static synchronized Activator getInstance(){
		return InstanceKeeper.sPlugin;
	}

	public String getID() {
		return kPluginID;
	}

	protected static PreferencesService preferencesService = null;

	public void earlyStartup(){
		getInstance();
	}
}
