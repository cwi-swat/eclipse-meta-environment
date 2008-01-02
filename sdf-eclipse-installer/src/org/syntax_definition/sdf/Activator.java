package org.syntax_definition.sdf;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * This activator lazily installs the SDF tools inside of the bundle of this
 * plugin.
 */
public class Activator extends AbstractUIPlugin {

	private static final String INSTALL_FAILED = "Installation of SDF tools failed";

	// The plug-in ID
	public static final String PLUGIN_ID = "sdf-eclipse-installer";

	// The shared instance
	private static Activator plugin;

	private static final String installPrefix = "install";

	private static final String windowsPrefix = installPrefix;

	private static final String linuxPrefix = installPrefix;

	private static final String distributions = "distributions";

	private static final String linuxInstaller = distributions + File.separator
			+ "linux.bin.sh";

	private static final String windowsInstaller = distributions
			+ File.separator + "windows.zip";

	private BundleContext context;

	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	public File getBinaryPrefix() throws IOException {
		String os = Platform.getOS();
		if (os.equals(Platform.OS_LINUX)) {
			return getFile(context.getBundle(), linuxPrefix + File.separator
					+ "bin");
		} else if (os.equals(Platform.OS_WIN32)) {
			return getFile(context.getBundle(), windowsPrefix + File.separator
					+ "bin");
		} else {
			return null;
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		this.context = context;
		installBinariesIfNeeded(context);
	}

	/**
	 * The idea of this method is to select the proper zip file, depending on
	 * the current platform, unzip it and run some configuration. If the
	 * binaries have been installed before, this method will do nothing. The
	 * installation procedure is different for each platform.
	 * 
	 * @throws BundleException
	 * @throws InterruptedException
	 * 
	 */
	private void installBinariesIfNeeded(BundleContext context)
			throws BundleException, InterruptedException {
		String arch = Platform.getOSArch();

		if (arch.equals(Platform.ARCH_X86)) {
			String os = Platform.getOS();
			if (os.equals(Platform.OS_LINUX)) {
				installLinuxBinariesIfNeeded(context);
			} else if (os.equals(Platform.OS_WIN32)) {
				installWindowsBinariesIfNeeded(context);
			} else {
				throw new BundleException(INSTALL_FAILED);
			}
		} else {
			throw new BundleException(INSTALL_FAILED);
		}
	}

	private void installWindowsBinariesIfNeeded(BundleContext context)
			throws BundleException {
		if (!windowsWasPreviouslyInstalled(context)) {
			try {
				File installer = getFile(context.getBundle(), windowsInstaller);
				File prefix = getFile(context.getBundle(), windowsPrefix);
				Unzipper.unzip(prefix.getAbsolutePath(), installer
						.getAbsolutePath());

			} catch (IOException e) {
				throw new BundleException(INSTALL_FAILED, e);
			}
		}
	}

	private boolean windowsWasPreviouslyInstalled(BundleContext context) {
		return context.getBundle().getResource(
				windowsPrefix + File.separator + "bin") != null;
	}

	/**
	 * Runs the binary installer script for linux providing it with the
	 * appropriate parameters.
	 * 
	 * TODO this method is not robust for all contingencies that may occur while
	 * running the script.
	 * 
	 * @param context
	 * @throws BundleException
	 * @throws InterruptedException
	 */
	private void installLinuxBinariesIfNeeded(BundleContext context)
			throws BundleException, InterruptedException {
		if (!linuxWasPreviouslyInstalled(context)) {
			try {
				File installer = getFile(context.getBundle(), linuxInstaller);

				File prefix = getFile(context.getBundle(), linuxPrefix);

				String[] command = new String[] { installer.getAbsolutePath(),
						"--no-java-check", "--no-clib-check",
						"--no-platform-check", "--no-overwrite-check",
						"--prefix", prefix.getAbsolutePath() };

				Process installScript = Runtime.getRuntime().exec(command);

				OutputStream out = new BufferedOutputStream(installScript
						.getOutputStream());

				// TODO remove the need for these binary location questions
				out.write('\n');
				out.write('\n');
				out.write('\n');
				
				out.close();
				
				installScript.waitFor();

				if (installScript.exitValue() != 0) {
					throw new BundleException(INSTALL_FAILED);
				}

			} catch (IOException e) {
				throw new BundleException(INSTALL_FAILED, e);
			}
		}
	}

	private File getFile(Bundle bundle, String resourcePath) throws IOException {
		URL url = bundle.getResource(resourcePath);

		return new File(FileLocator.toFileURL(url).getPath());
	}

	private boolean linuxWasPreviouslyInstalled(BundleContext context) {
		return context.getBundle().getResource(
				linuxPrefix + File.separator + "bin") != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Activator getInstance() {
		return plugin;
	}

	private static class Unzipper {
		public static void pipe(InputStream in, OutputStream out)
				throws IOException {
			byte[] buffer = new byte[1024];
			int len;

			while ((len = in.read(buffer)) >= 0) {
				out.write(buffer, 0, len);
			}

			in.close();
			out.close();
		}

		public static void unzip(String where, String zip)
				throws BundleException {
			Enumeration entries;
			ZipFile zipFile;

			try {
				zipFile = new ZipFile(zip);
				entries = zipFile.entries();

				while (entries.hasMoreElements()) {
					ZipEntry entry = (ZipEntry) entries.nextElement();
					String relative = entry.getName();
					String absolute = where + File.separator + relative;
					File file = new File(absolute);

					if (entry.isDirectory()) {
						if (!file.mkdir()) {
							throw new BundleException(INSTALL_FAILED);
						}
					} else {
						InputStream input = zipFile.getInputStream(entry);
						FileOutputStream output = new FileOutputStream(file
								.getAbsolutePath());
						pipe(input, new BufferedOutputStream(output));
					}
				}

				zipFile.close();
			} catch (IOException e) {
				throw new BundleException(INSTALL_FAILED, e);
			}
		}
	}
}
