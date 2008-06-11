package org.meta_environment.eclipse.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.meta_environment.eclipse.Tool;


public class EditorTool extends Tool {
	private static EditorTool sInstance;
	
	public static EditorTool getInstance() {
		if (sInstance == null) {
			sInstance = new EditorTool();
		}
		return sInstance;
	}

	private EditorTool(){
		super("editor-tool");
	}
	
	public void open(String filename, String language) {
		System.err.println("ET: file: " + filename + " language: " + language);

		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		
		if (win != null) {
		  IWorkbenchPage page = win.getActivePage();
		  
			System.err.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

		  if (page != null) {
			  IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(filename));
			  try {
				page.openEditor(new FileEditorInput(file), UniversalEditor.EDITOR_ID);
			} catch (PartInitException e) {
				System.err.println("Could not open editor for: " + filename);
				//Activator.getInstance().logException("Could not open editor for: " + filename, e);
			}
		  }
		}
	}
}
