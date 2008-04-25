package org.meta_environment.eclipse;

import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.ui.IStartup;
import org.meta_environment.eclipse.errors.ErrorViewer;
import org.meta_environment.eclipse.files.Resources;

public class Activator extends PluginBase implements IStartup {
	public static final String PLUGIN_ID = "org.meta_environment";

	private static Activator plugin;

	public Activator() {
		plugin = this;
	}

	public static Activator getInstance() {
		return plugin;
	}

	public void earlyStartup() {
		new ErrorViewer();
		new Resources();
	}

	public String getID() {
		return PLUGIN_ID;
	}
}
