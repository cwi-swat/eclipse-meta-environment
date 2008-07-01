package toolbus_ide;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

public class Activator extends PluginBase {

	public static final String kPluginID = "toolbus_ide";

	public static final String kLanguageName = "tscript";

	public Activator(){
		super();
	}

	private static Activator sInstance = new Activator();

	public static Activator getInstance(){
		return sInstance;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	public String getID() {
		return kPluginID;
	}

	protected static PreferencesService preferencesService = null;

	public static PreferencesService getPreferencesService() {
		if (preferencesService == null) {
			preferencesService = new PreferencesService(ResourcesPlugin.getWorkspace().getRoot().getProject());
			preferencesService.setLanguageName(kLanguageName);
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
		return preferencesService;
	}
}
