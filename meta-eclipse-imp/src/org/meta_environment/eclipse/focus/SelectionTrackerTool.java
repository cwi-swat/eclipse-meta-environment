package org.meta_environment.eclipse.focus;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.meta_environment.eclipse.Tool;

import toolbus.adapter.AbstractTool;
import aterm.ATerm;
import aterm.pure.PureFactory;

public class SelectionTrackerTool extends Tool{
	private final static String TOOL_NAME = "selection-tracker";
	
	private static class InstanceKeeper{
		private final static SelectionTrackerTool instance = new SelectionTrackerTool();
		static{
			instance.connect();
		}
	}
	
	private SelectionTrackerTool(){
		super(TOOL_NAME);
		init();
	}
	
	private void init(){
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		ISelectionService selectionService = workbenchWindow.getSelectionService();
		selectionService.addPostSelectionListener(new SelectionChangeListener(this));
	}
	
	public static SelectionTrackerTool getInstance(){
		return InstanceKeeper.instance;
	}
	
	private static class SelectionChangeListener implements ISelectionListener{
		private final SelectionTrackerTool selectionTrackerTool;
		private final PureFactory factory;
		
		public SelectionChangeListener(SelectionTrackerTool selectionTrackerTool){
			super();
			
			this.selectionTrackerTool = selectionTrackerTool;
			this.factory = AbstractTool.getFactory();
		}

		public void selectionChanged(IWorkbenchPart part, ISelection selection){
			if(selection instanceof ITextSelection){
				ITextSelection textSelection = (ITextSelection) selection;
				
				int startLine = textSelection.getStartLine();
				int endLine = textSelection.getEndLine();
				int offset = textSelection.getOffset();
				int length = textSelection.getLength();
				
				ATerm selected = factory.make("selected(<int>, <int>, <int>, <int>)", startLine, endLine, offset, length);
				
				selectionTrackerTool.sendEvent(selected);
			}
		}
	}
}
