package toolbus_ide;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

/*
 * SMS 27 Mar 2007:  Deleted creation of preferences cache (now obsolete)
 * SMS 28 Mar 2007:
 * 	- Plugin class name now totally parameterized
 *  - Plugin package made a separate parameter
 * SMS 19 Jun 2007:
 * 	- Added kLanguageName (may be used by later updates to the template)
 * 	- Added field and method related to new preferences service; deleted
 *	  code for initializing preference store from start(..) method
 */

public class Activator extends PluginBase {

	public static final String kPluginID = "toolbus_ide";

	public static final String kLanguageName = "tscript";

	public Activator() {
		super();
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
