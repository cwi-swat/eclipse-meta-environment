package nl.cwi.sen.metastudio.graphview;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.PerspectiveFactory;
import nl.cwi.sen.metastudio.UserInterface;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.views.navigator.IResourceNavigator;

import aterm.ATerm;

public class GraphPartAction extends Action {
	private ATerm actionType;
	private ATerm action;
	private String moduleName;
	private String label;

	GraphPartAction(
		String label,
		ATerm actionType,
		ATerm action,
		String moduleName) {
		this.actionType = actionType;
		this.action = action;
		this.moduleName = moduleName;
		this.label = label;

		setText(label);
	}

	public void run() {
		MetastudioConnection connection = UserInterface.getConnection();

		if (label == "Edit Term") {
			IResourceNavigator part =
				(IResourceNavigator) PerspectiveFactory
					.getResourceNavigatorPart();
			IStructuredSelection selection =
				(IStructuredSelection) part.getViewer().getSelection();

			if (selection != null) {
				Object object = selection.getFirstElement();
				if (object instanceof IFile) {
					String fileName = ((IFile) object).getLocation().toString();
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
					moduleName,
					action);
			connection.getBridge().postEvent(event);
		}
	}
}
