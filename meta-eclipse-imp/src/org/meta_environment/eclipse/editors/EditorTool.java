package org.meta_environment.eclipse.editors;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.meta_environment.eclipse.Activator;
import org.meta_environment.eclipse.Tool;

public class EditorTool extends Tool {
	private static class InstanceKeeper {
		private static EditorTool sInstance = new EditorTool();
		static {
			sInstance.connect();
		}
	}

	public static EditorTool getInstance() {
		return InstanceKeeper.sInstance;
	}

	private EditorTool() {
		super("editor-tool");
	}

	public void open(final String filename, String language) {
		System.err.println("ET: file: " + filename + " language: " + language);

		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbench wb = PlatformUI.getWorkbench();
				IWorkbenchWindow win = wb.getActiveWorkbenchWindow();

				if (win != null) {
					IWorkbenchPage page = win.getActivePage();

					if (page != null) {
						IFile file = ResourcesPlugin.getWorkspace().getRoot()
								.getFileForLocation(new Path(filename));

						try {
							if (!file.exists()) {
								file.create(new ByteArrayInputStream(
										new byte[0]), true,
										new NullProgressMonitor());
							}
							page.openEditor(new FileEditorInput(file),
									UniversalEditor.EDITOR_ID);
						} catch (PartInitException e) {
							Activator.getInstance()
									.logException(
											"Could not open editor for: "
													+ filename, e);
						} catch (CoreException e) {
							Activator.getInstance()
									.logException(
											"Could not open editor for: "
													+ filename, e);
						}
					}
				}
			}
		});
	}

	public void editTerm(final String filename, String language, String content) {
		System.err.println("ET: file: " + filename + " language: " + language + " content: " + content);

		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(filename));

		try {
			if(!file.exists()){
				file.create(new ByteArrayInputStream(new byte[0]), true, new NullProgressMonitor());
			}

			file.setContents(new ByteArrayInputStream(content.toString().getBytes()), IResource.FORCE, new NullProgressMonitor());
		} catch (CoreException e) {
			Activator.getInstance().logException("Could not open editor for: " + filename, e);
		}

		open(filename, language);
	}
}
