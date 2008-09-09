package org.meta_environment.eclipse.focus.move;

import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.meta_environment.eclipse.focus.SelectionTrackerTool;

import toolbus.adapter.AbstractTool;
import aterm.ATerm;

public class MoveRight implements IActionDelegate{
	
	public MoveRight(){
		super();
	}
	
	public void selectionChanged(IAction action, ISelection selection){
		// Ignore this.
	}
	
	public void run(IAction action){
		SelectionTrackerTool selectionTracker = SelectionTrackerTool.getInstance();
		
		IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if(editor instanceof UniversalEditor){
			UniversalEditor universalEditor = (UniversalEditor) editor;
			ISelection selection = universalEditor.getSelectionProvider().getSelection();
			if(selection instanceof ITextSelection){
				ITextSelection textSelection = (ITextSelection) selection;
				IParseController parseController = universalEditor.getParseController();
				Object ast = parseController.getCurrentAst();
				if(ast instanceof ATerm){
					ATerm parseTree = (ATerm) ast;
					IPath path = parseController.getPath();
					
					int startLine = textSelection.getStartLine();
					int endLine = textSelection.getEndLine();
					int offset = textSelection.getOffset();
					int length = textSelection.getLength();
					
					ATerm moveRight = AbstractTool.getFactory().make("move-right(<term>, <str>, <int>, <int>, <int>, <int>)", parseTree, path.toOSString(), Integer.valueOf(startLine), Integer.valueOf(endLine), Integer.valueOf(offset), Integer.valueOf(length));
					ATerm movedRight = selectionTracker.sendRequest(moveRight);

					System.out.println(movedRight); // Temp
					// TODO Handle response.
				}
			}
		}
		System.out.println("Moved Right.");
		
		// TODO Implement.
		
	}
}
