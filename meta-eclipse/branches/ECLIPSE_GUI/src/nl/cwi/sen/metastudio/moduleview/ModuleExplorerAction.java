/*
 * Created on May 22, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.moduleview;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.model.Module;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import aterm.ATerm;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ModuleExplorerAction extends Action {
	private ATerm _actionType;
	private ATerm _action;
	private TreeViewer _tree;

	ModuleExplorerAction(String label, ATerm actionType, ATerm action, TreeViewer tree) {
		_actionType = actionType;
		_action = action;
		_tree = tree;
		
		setText(label);
	}

	public void run() {
		MetastudioConnection connection = new MetastudioConnection();
		ISelection selection = _tree.getSelection();

		if (selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			if (first instanceof Module) {
				String fullName = ((Module)first).getModulePath();
				ATerm event = connection.getFactory().make("button-selected(<term>, <str>, <term>)",
							   _actionType, fullName, _action);
				connection.getBridge().postEvent(event);
			}
		}
	}
}
