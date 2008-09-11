package org.meta_environment.eclipse.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.language.ILanguageService;
import org.eclipse.imp.utils.StreamUtils;

import toolbus.adapter.eclipse.EclipseTool;

public class BuilderTool extends EclipseTool implements ILanguageService {
	private static final String TOOL_NAME = "builder";

	public BuilderTool() {
		super(TOOL_NAME);
	}

	public void compile(IFile file, IProgressMonitor monitor) {
		try {
			String contents = StreamUtils.readStreamContents(file.getContents());
			sendEvent(factory.make("compile(<str>,<str>)", file.getFullPath().toOSString(), contents));
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public void collectDependencies(IFile file) {
		try {
			String contents = StreamUtils.readStreamContents(file.getContents());
			sendEvent(factory.make("dependencies(<str>,<str>)", file.getFullPath().toOSString(), contents));
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
