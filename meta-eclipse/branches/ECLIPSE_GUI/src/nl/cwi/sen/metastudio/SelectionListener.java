/*
 * Created on Aug 1, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class SelectionListener implements ISelectionChangedListener {
	ModulePath module = new ModulePath();

	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		ISelectionProvider source = event.getSelectionProvider();
		
		if (selection instanceof IStructuredSelection)
		{
			IStructuredSelection structured = (IStructuredSelection)selection;
			Object object = structured.getFirstElement();
			if (object instanceof IFile) {
				IFile file = (IFile) object;
				module.setFullPath(file.getLocation());
			}
		}
	}
}
