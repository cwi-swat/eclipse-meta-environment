package org.meta_environment.eclipse.files;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.runtime.RuntimePlugin;
import org.meta_environment.eclipse.Tool;

import aterm.ATerm;
import aterm.pure.binary.BinaryWriter;

public class Resources extends Tool implements IResourceChangeListener {
	private static class InstanceKeeper {
		private static Resources sInstance = new Resources();
		static{
			sInstance.connect();
		}
	}
	
	private Resources() {
		super("resources");
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	
	static public Resources getInstance() {
		return InstanceKeeper.sInstance;
	}

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta[] deltas = event.getDelta().getAffectedChildren();

		try {
			for (IResourceDelta d : deltas) {

				d.accept(new IResourceDeltaVisitor() {
					public boolean visit(IResourceDelta delta)
							throws CoreException {
						IResource resource = delta.getResource();
						
						if (resource instanceof IFile) {
							IPath path = resource.getLocation();
							
							Language l = LanguageRegistry.findLanguage(path,
									null);

							if (l != null) {
								switch (delta.getKind()) {
								case IResourceDelta.OPEN:
									fileCreated(l, resource);
									break;
								case IResourceDelta.ADDED:
									fileCreated(l, resource);
									break;
								case IResourceDelta.CHANGED:
									if ((delta.getFlags() & IResourceDelta.CONTENT) != 0) {
									  fileChanged(l, resource);
									}
									break;
								case IResourceDelta.REMOVED:
									fileRemovedEvent(l, resource);
									break;
								}
							}
							return false;
						}
						return true;
					}
				});

			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private void fileCreated(Language l, IResource resource) {
		
		if (l != null && !resource.isDerived() && resource.isAccessible()) {
			sendEvent(factory.make("create-module(<str>,<str>)", l.getName(),
					resource.getLocation().toOSString()));
		}
	}

	private void fileRemovedEvent(Language l, IResource resource) {
		if (l != null) {
			sendEvent(factory.make("delete-module(<str>,<str>)", l.getName(),
					resource.getLocation().toOSString()));
		}

	}

	private void fileChanged(Language l, IResource resource) throws CoreException {
		if (l != null) {
			sendEvent(factory.make("build-module(<str>,<str>)", l.getName(),
					resource.getLocation().toOSString()
					));
		}
	}
	
	public void saveBuild(String sourcePathStr, String targetExt, String content) {
		try {
			IFile targetFile = getTargetFile(sourcePathStr, targetExt);

			if (targetFile != null) {
				if (targetFile.exists()) {
					targetFile.setContents(new ByteArrayInputStream(content.getBytes()),
							IFile.FORCE,
							new NullProgressMonitor());
				}
				else {
					targetFile.create(new ByteArrayInputStream(content.getBytes()), IFile.FORCE,
							new NullProgressMonitor());
				}
				
				targetFile.setDerived(true);
			}
		} 
		catch (CoreException e) {
			RuntimePlugin.getInstance().logException("could not save build for " + sourcePathStr, e);
		}
		catch (Exception e) {
			RuntimePlugin.getInstance().logException("could not save build for " + sourcePathStr, e);
		}
	}
		
    public void saveBuild(String sourcePathStr, String targetExt, ATerm content) {
    	try {
			IFile targetFile = getTargetFile(sourcePathStr, targetExt);
			
			if (targetFile != null) {
				
				BinaryWriter.writeTermToSAFFile(decode(content), new File(targetFile.getLocation().toOSString()));
					
				targetFile.refreshLocal(0, new NullProgressMonitor());
				targetFile.setDerived(true);
			}
		} 
		catch (CoreException e) {
			RuntimePlugin.getInstance().logException("could not save build for " + sourcePathStr, e);
		}
		catch (Exception e) {
			RuntimePlugin.getInstance().logException("could not save build for " + sourcePathStr, e);
		}
    }

	private IFile getTargetFile(String sourcePathStr, String targetExt)
			throws CoreException {
		IFile sourceFile = getFile(sourcePathStr);
		
		while (targetExt.startsWith(".")) {
			targetExt = targetExt.substring(1);
		}

		if (sourceFile != null) {
			IPath sourcePath = sourceFile.getProjectRelativePath();
			IPath targetPath = sourcePath.removeFileExtension()
					.addFileExtension(targetExt);
			IFile targetFile = sourceFile.getProject().getFile(targetPath);

			return targetFile;
		}

		return null;
	}

	private IFile getFile(String source) {
		return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(source));
	}
}
