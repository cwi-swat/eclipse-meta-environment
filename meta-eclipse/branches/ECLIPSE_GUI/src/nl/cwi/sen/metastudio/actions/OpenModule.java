package nl.cwi.sen.metastudio.actions;

import nl.cwi.sen.metastudio.Module;
import nl.cwi.sen.metastudio.UserInterface;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class OpenModule implements IWorkbenchWindowActionDelegate {
	Module module = new Module();

	public OpenModule() {
	}

	public void run(IAction action)  {
		String modulePath = module.getFullPath().toString();
		
		UserInterface.bridge.postEvent(UserInterface.factory.make("eclipse-open-initial-module(<str>)", modulePath));		
//		ATermList list = (ATermList)UserInterface.factory.make("[\"Open Module\"]");
//		UserInterface.bridge.postEvent(UserInterface.factory.make("button-selected(<str>,<list>)", "studio-menubar", list));
	}

	public void selectionChanged(IAction action, ISelection selection)  {
	}

	public void dispose()  {
	}

	public void init(IWorkbenchWindow window)  {
	}
}
