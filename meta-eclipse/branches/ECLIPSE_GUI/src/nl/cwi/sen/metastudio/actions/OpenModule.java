package nl.cwi.sen.metastudio.actions;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.Module;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class OpenModule implements IWorkbenchWindowActionDelegate {
	Module module = new Module();

	public OpenModule() {
	}

	public void run(IAction action)  {
		MetastudioConnection connection = new MetastudioConnection();
		String modulePath = module.getFullPath().toString();
		
		connection.getBridge().postEvent(connection.getFactory().make("eclipse-open-initial-module(<str>)", modulePath));		
	}

	public void selectionChanged(IAction action, ISelection selection)  {
	}

	public void dispose()  {
	}

	public void init(IWorkbenchWindow window)  {
	}
}
