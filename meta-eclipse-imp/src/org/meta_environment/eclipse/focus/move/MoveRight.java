package org.meta_environment.eclipse.focus.move;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;

public class MoveRight extends AbstractMove  implements IActionDelegate{
	
	public MoveRight(){
		super();
	}
	
	public void selectionChanged(IAction action, ISelection selection){
		// Ignore this.
	}
	
	public void run(IAction action){
		move("move-right(<term>, <str>, <int>, <int>, <int>, <int>)");
	}
}
