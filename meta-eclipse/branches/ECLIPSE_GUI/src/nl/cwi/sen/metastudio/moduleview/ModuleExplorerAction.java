package nl.cwi.sen.metastudio.moduleview;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.PerspectiveFactory;
import nl.cwi.sen.metastudio.UserInterface;
import nl.cwi.sen.metastudio.model.Module;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.views.navigator.IResourceNavigator;

import aterm.ATerm;

public class ModuleExplorerAction extends Action {
	private ATerm actionType;
	private ATerm action;
	private TreeViewer tree;
	private String label;

	ModuleExplorerAction(
		String label,
		ATerm actionType,
		ATerm action,
		TreeViewer tree) {
		this.actionType = actionType;
		this.action = action;
		this.tree = tree;
		this.label = label;

		setText(label);
	}

	public void run() {
		MetastudioConnection connection = UserInterface.getConnection();
		ISelection selection = tree.getSelection();

		if (selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			if (first instanceof Module) {
				String fullName = ((Module) first).getModulePath();
				String moduleName = ((Module) first).getModuleName();
				if (label == "Edit Term") {
					IResourceNavigator part =
						(IResourceNavigator) PerspectiveFactory
							.getResourceNavigatorPart();
					IStructuredSelection structuredSelection =
						(IStructuredSelection) part.getViewer().getSelection();

					if (selection != null) {
						Object object = structuredSelection.getFirstElement();
						if (object instanceof IFile) {
							String fileName =
								((IFile) object).getLocation().toString();
							ATerm event =
								connection.getPureFactory().make(
									"eclipse-edit-term-file(<str>,<str>)",
									moduleName,
									fileName);
							connection.getBridge().postEvent(event);
						}
					}
				} else {

					ATerm event =
						connection.getPureFactory().make(
							"button-selected(<term>, <str>, <term>)",
							actionType,
							fullName,
							action);
					connection.getBridge().postEvent(event);
				}
			}
		}
	}
}
