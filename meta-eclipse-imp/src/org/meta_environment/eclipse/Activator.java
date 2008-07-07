package org.meta_environment.eclipse;

import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.ui.IStartup;
import org.meta_environment.eclipse.actions.LanguageActionsTool;
import org.meta_environment.eclipse.editors.EditorTool;
import org.meta_environment.eclipse.errors.ErrorViewer;
import org.meta_environment.eclipse.facts.FactsTool;
import org.meta_environment.eclipse.files.BuildResults;
import org.meta_environment.eclipse.files.ResourceChanges;
import org.meta_environment.eclipse.focus.SelectionTracker;
import org.meta_environment.eclipse.jobs.Jobs;

public class Activator extends PluginBase implements IStartup{
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
		ResourceChanges.getInstance();
		BuildResults.getInstance();
		EditorTool.getInstance();
		LanguageActionsTool.getInstance();
		Jobs.getInstance();
		FactsTool.getInstance();
		SelectionTracker.getInstance();
	}

	public String getID() {
		return PLUGIN_ID;
	}
}
