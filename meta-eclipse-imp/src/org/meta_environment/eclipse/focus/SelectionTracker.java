package org.meta_environment.eclipse.focus;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class SelectionTracker{
	private final IWorkbench workbench;
	
	private SelectionTracker(){
		super();
		
		workbench = PlatformUI.getWorkbench();
		
		init();
	}
	
	private void init(){
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		ISelectionService selectionService = workbenchWindow.getSelectionService();
		selectionService.addSelectionListener(new SelectionChangeListener());
	}
	
	private static class InstanceKeeper{
		private final static SelectionTracker instance = new SelectionTracker();
	}
	
	public static SelectionTracker getInstance(){
		return InstanceKeeper.instance;
	}
	
	private static class SelectionChangeListener implements ISelectionListener{
		
		public SelectionChangeListener(){
			super();
		}

		public void selectionChanged(IWorkbenchPart part, ISelection selection){
			if(selection instanceof ITextSelection){
				//ITextSelection textSelection = (ITextSelection) selection;
				//System.out.println(textSelection.getStartLine()+" "+textSelection.getEndLine()+" "+textSelection.getOffset()+" "+textSelection.getLength());
			}
		}
	}
}
