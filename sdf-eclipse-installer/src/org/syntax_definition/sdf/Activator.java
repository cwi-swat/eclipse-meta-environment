package org.syntax_definition.sdf;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * This activator lazily installs the SDF tools inside of the bundle of this
 * plugin.
 */
public class Activator extends AbstractUIPlugin {
	public static final String PLUGIN_ID = "sdf-eclipse-installer";

	private static Activator plugin;

	private static final String installPrefix = "install";

	private BundleContext context;

	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;

	}

	public File getBinaryPrefix() throws IOException {
		return getFile(context.getBundle(), installPrefix + File.separator + "bin");
	}

	public String getBinaryPostfix() {
		String os = Platform.getOS();
		if (os.equals(Platform.OS_LINUX)) {
			return "";
		} else if (os.equals(Platform.OS_WIN32)) {
			return ".exe";
		} else {
			return null;
		}
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		this.context = context;
	}

	private File getFile(Bundle bundle, String resourcePath) throws IOException {
		URL url = bundle.getResource(resourcePath);

		return new File(FileLocator.toFileURL(url).getPath());
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Activator getInstance() {
		return plugin;
	}
}
