package org.meta_environment.eclipse.sdf;

import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.ui.IStartup;

public class Activator extends PluginBase implements IStartup {

	public static final String kPluginID = "sdf_meta_eclipse";

	public static final String kLanguageName = "SDF";

	/**
	 * The unique instance of this plugin class
	 */
	protected static Activator sPlugin;

	public static Activator getInstance() {
		if (sPlugin == null) {
			new Activator();
		}
		return sPlugin;
	}

	public Activator() {
		super();
		sPlugin = this;
	}

	public String getID() {
		return kPluginID;
	}

	protected static PreferencesService preferencesService = null;

	public void earlyStartup() {
	}
}
