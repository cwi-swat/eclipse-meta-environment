/*
 * Created on May 22, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.moduleview;

import nl.cwi.sen.metastudio.model.Module;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ModuleExplorerAction extends Action {
	private TreeViewer _tree;

	ModuleExplorerAction(TreeViewer tree, String label) {
		_tree = tree;
		setText(label);
	}

	public void run() {
		ISelection selection = _tree.getSelection();

		if (selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			if (first instanceof Module) {
				System.out.println(
					"+- Module: " + ((Module) first).getModulePath());

//				IFile file = MetastudioPlugin.getWorkspace().getRoot().
//				try {
//					//page.openEditor(file);
//				} catch (Exception e) {
//				}
			}
		}
	}
}
