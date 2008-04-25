package org.meta_environment.eclipse.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.runtime.PluginBase;
import org.meta_environment.eclipse.Activator;

public class Builder extends BuilderBase {
	public static final String builderId = Activator.PLUGIN_ID + ".builder";
	private BuilderTool tool;

	public Builder() {
		this.tool = new BuilderTool();
	}
	
	protected void collectDependencies(IFile file) {
		tool.collectDependencies(file);
	}

	protected void compile(IFile resource, IProgressMonitor monitor) {
		tool.compile(resource, monitor);
	}

	protected String getErrorMarkerID() {
		return builderId + ".error";
	}

	protected String getInfoMarkerID() {
		return builderId + ".info";
	}

	protected String getWarningMarkerID() {
		return  builderId + ".warning";
	}
	
	protected PluginBase getPlugin() {
		return Activator.getInstance();
	}

	protected boolean isNonRootSourceFile(IFile resource) {
		return false;
	}

	protected boolean isOutputFolder(IResource resource) {
		return false;
	}

	protected boolean isSourceFile(IFile resource) {
		return true;
	}
}
