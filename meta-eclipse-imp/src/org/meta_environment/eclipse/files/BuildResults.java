package org.meta_environment.eclipse.files;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.runtime.RuntimePlugin;
import org.eclipse.imp.utils.StreamUtils;
import org.meta_environment.eclipse.Tool;

import aterm.ATerm;
import aterm.pure.binary.BinaryWriter;

public class BuildResults extends Tool {
	private static final String BIN_FOLDER = "bin";
	private static final String EXTENSION_SEPARATOR = ".";

	private static class InstanceKeeper {
		private static BuildResults sInstance = new BuildResults();
		static {
			sInstance.connect();
		}
	}

	private BuildResults() {
		super("build-results");
	}

	static public BuildResults getInstance() {
		return InstanceKeeper.sInstance;
	}

	public void saveBuild(String sourcePathStr, String targetExt, String content) {
		try {
			IFile targetFile = getTargetFile(sourcePathStr, targetExt);

			if (targetFile != null) {
				if (targetFile.exists()) {
					targetFile.setContents(new ByteArrayInputStream(content
							.getBytes()), IFile.FORCE,
							new NullProgressMonitor());
				} else {
					targetFile.create(new ByteArrayInputStream(content
							.getBytes()), IFile.FORCE,
							new NullProgressMonitor());
				}

				targetFile.setDerived(true);
			}
		} catch (CoreException e) {
			RuntimePlugin.getInstance().logException(
					"could not save build for " + sourcePathStr, e);
		} catch (Exception e) {
			RuntimePlugin.getInstance().logException(
					"could not save build for " + sourcePathStr, e);
		}
	}

	public void saveBuild(String sourcePathStr, String targetExt, ATerm content) {
		try {
			IFile targetFile = getTargetFile(sourcePathStr, targetExt);

			if (targetFile != null) {

				BinaryWriter.writeTermToSAFFile(decode(content), new File(
						targetFile.getLocation().toOSString()));

				targetFile.refreshLocal(0, new NullProgressMonitor());
				targetFile.setDerived(true);
			}
		} catch (CoreException e) {
			RuntimePlugin.getInstance().logException(
					"could not save build for " + sourcePathStr, e);
		} catch (Exception e) {
			RuntimePlugin.getInstance().logException(
					"could not save build for " + sourcePathStr, e);
		}
	}

	public void cleanBuild(String sourcePathStr, String targetExt) {

		try {
			IFile targetFile = getTargetFile(sourcePathStr, targetExt);

			if (targetFile != null && targetFile.exists()) {
				targetFile.delete(true, false, new NullProgressMonitor());
				System.err.println("Resources: cleaned " + targetFile);
			}
		} catch (CoreException e) {
			RuntimePlugin.getInstance().logException(
					"could not clean build for " + sourcePathStr, e);
		}
	}

	public ATerm loadBuild(String sourcePathStr, String targetExt) {
		try {
			IFile targetFile = getTargetFile(sourcePathStr, targetExt);

			if (targetFile != null && targetFile.exists()) {
				ATerm build = getFactory().readFromFile(targetFile.getLocation().toOSString());
				
				return getFactory().make("build(<term>)", build);
			}
		} catch (CoreException e) {
			RuntimePlugin.getInstance().logException(
					"could not get build for " + sourcePathStr, e);
		} catch (IOException e) {
			RuntimePlugin.getInstance().logException(
					"could not get build for " + sourcePathStr, e);
		}
		
		return getFactory().make("build(build-not-found)");
	}

	public ATerm loadStrBuild(String sourcePathStr, String targetExt) {
		try {
			IFile targetFile = getTargetFile(sourcePathStr, targetExt);

			if (targetFile != null && targetFile.exists()) {
				String content = StreamUtils.readStreamContents(targetFile.getContents());
				return getFactory().make("build(<str>)", content);
			}
		} catch (CoreException e) {
			RuntimePlugin.getInstance().logException(
					"could not get build for " + sourcePathStr, e);
		}
		
		return getFactory().make("build(\"***build-not-found***\")");	
	}

	private IFile getTargetFile(String sourcePathStr, String targetExt)
			throws CoreException {
		IFile sourceFile = getFile(sourcePathStr);

		while (targetExt.startsWith(EXTENSION_SEPARATOR)) {
			targetExt = targetExt.substring(1);
		}

		if (sourceFile != null) {
			IProject project = sourceFile.getProject();
			IPath sourcePath = sourceFile.getProjectRelativePath();
			IFolder binFolder = project.getFolder(BIN_FOLDER);
			IPath targetPath = binFolder.getProjectRelativePath().append(
					sourcePath.removeFileExtension()
							.addFileExtension(targetExt));

			IPath current = Path.ROOT;
			for (String segment : targetPath.removeLastSegments(1).segments()) {
				current = current.append(segment);
				IFolder path = project.getFolder(current);
				if (!path.exists()) {
					path.create(true, true, null);
				}
			}

			IFile targetFile = sourceFile.getProject().getFile(targetPath);

			return targetFile;
		}

		return null;
	}

	private IFile getFile(String source) {
		return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(
				new Path(source));
	}
}
