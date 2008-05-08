package org.meta_environment.eclipse.builder;

import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;
import org.meta_environment.eclipse.Activator;

public class Nature extends ProjectNatureBase {
	public final static String natureId = Activator.PLUGIN_ID + ".nature";

	public Nature() {
		super();
	}
	
	public String getBuilderID() {
		return Builder.builderId;
	}

	public IPluginLog getLog() {
		return Activator.getInstance();
	}

	public String getNatureID() {
		return natureId;
	}

	protected void refreshPrefs() {
		// TODO something
	}
	
}
