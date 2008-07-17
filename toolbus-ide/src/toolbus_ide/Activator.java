package toolbus_ide;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

public class Activator extends PluginBase {
	public static final String kPluginID = "toolbus_ide";
	public static final String kLanguageName = "tscript";
	
	private final static class InstanceKeeper{
		private final static Activator sInstance = new Activator();
	}

	public Activator(){
		super();
	}

	public static Activator getInstance(){
		return InstanceKeeper.sInstance;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	public String getID() {
		return kPluginID;
	}
	
	private volatile static PreferencesService prefsService = null;

	public static PreferencesService getPreferencesService() {
		if (prefsService == null) {
			prefsService = new PreferencesService(ResourcesPlugin.getWorkspace().getRoot().getProject());
			prefsService.setLanguageName(kLanguageName);
			// To trigger the invocation of the preferences initializer:
			try{
				new DefaultScope().getNode(kPluginID);
			}catch(Exception e){
				// If this ever happens, it will probably be because the preferences
				// and their initializer haven't been defined yet.  In that situation
				// there's not really anything to do--you can't initialize preferences
				// that don't exist.  So swallow the exception and continue ...
			}
		}
		return prefsService;
	}
}
