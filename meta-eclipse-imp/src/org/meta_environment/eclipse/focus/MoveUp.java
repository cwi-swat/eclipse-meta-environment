package org.meta_environment.eclipse.focus;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;

public class MoveUp implements IActionDelegate{
	
	public MoveUp(){
		super();
	}
	
	public void selectionChanged(IAction action, ISelection selection){
		// Ignore this.
	}
	
	public void run(IAction action){
		System.out.println("Moved Up.");
	}
}
