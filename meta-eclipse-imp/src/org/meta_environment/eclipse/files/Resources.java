package org.meta_environment.eclipse.files;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.meta_environment.eclipse.Tool;

public class Resources extends Tool implements IResourceChangeListener {
	private static Resources sInstance;
	
	
	private Resources() {
		super("resources");
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	
	static public Resources getInstance() {
		if (sInstance == null) {
			sInstance = new Resources();
		}
		return sInstance;
	}

	public void identifyAllResources() {
		try {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

			root.accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile) {
						Language l = LanguageRegistry.findLanguage(resource
								.getLocation(), (IFile) resource);
						if (l != null) {
							fileCreated(l, resource);
						}
					}
					return true;
				}

			});
		} catch (CoreException e) {
			e.printStackTrace();
		}
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
									(IFile) resource);

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
}
