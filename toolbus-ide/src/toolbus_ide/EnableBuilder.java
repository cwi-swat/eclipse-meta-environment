package toolbus_ide;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class EnableBuilder implements IWorkbenchWindowActionDelegate {
	private IProject fProject;

	public EnableBuilder() {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {

	}

	public void run(IAction action) {
		new ToolBusNature().addToProject(fProject);
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object first = ss.getFirstElement();

			if (first instanceof IProject) {
				fProject = (IProject) first;
			}
		}
	}

}
