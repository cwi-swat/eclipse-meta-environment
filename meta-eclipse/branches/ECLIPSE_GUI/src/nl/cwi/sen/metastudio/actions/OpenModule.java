package nl.cwi.sen.metastudio.actions;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.PerspectiveFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.views.navigator.IResourceNavigator;

public class OpenModule implements IWorkbenchWindowActionDelegate {
	public OpenModule() {
	}

	public void run(IAction action)  {
		IResourceNavigator part = (IResourceNavigator)PerspectiveFactory.getResourceNavigatorPart();
		IStructuredSelection selection = (IStructuredSelection)part.getViewer().getSelection();
		
		if (selection != null) {
			Object object = selection.getFirstElement();
			if (object instanceof IFile) {
				MetastudioConnection connection = new MetastudioConnection();
				String modulePath = ((IFile)object).getLocation().toString();
		
				connection.getBridge().postEvent(connection.getFactory().make("eclipse-open-initial-module(<str>)", modulePath));		
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection)  {
	}

	public void dispose()  {
	}

	public void init(IWorkbenchWindow window)  {
	}
}
