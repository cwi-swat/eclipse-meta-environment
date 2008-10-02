package org.meta_environment.eclipse.focus.move;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class MoveUp extends AbstractMove implements IWorkbenchWindowActionDelegate{
	
	public MoveUp(){
		super();
	}
	
	public void run(IAction action){
		move("move-up(<term>, <str>, <int>, <int>, <int>, <int>)");
	}
	
	public void selectionChanged(IAction action, ISelection selection){
		// Ignore this.
	}

	public void init(IWorkbenchWindow window){
		// Do nothing
	}
	
	public void dispose(){
		// Do nothing
	}
}
