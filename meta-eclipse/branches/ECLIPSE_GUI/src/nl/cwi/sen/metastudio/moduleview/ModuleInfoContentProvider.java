package nl.cwi.sen.metastudio.moduleview;

import nl.cwi.sen.metastudio.model.DeltaEvent;
import nl.cwi.sen.metastudio.model.IModelListener;
import nl.cwi.sen.metastudio.model.ModuleInfo;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

public class ModuleInfoContentProvider implements IStructuredContentProvider, IModelListener {
	private static Object[] EMPTY_ARRAY = new Object[0];
	private TableViewer viewer;

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof ModuleInfo) {
			return ((ModuleInfo)inputElement).getEntries().toArray();
		}
		return EMPTY_ARRAY;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TableViewer)viewer;
		if (oldInput != null) {
			((ModuleInfo)oldInput).removeListener(this);
		}
		if (newInput != null) {
			((ModuleInfo)newInput).addListener(this);
		}
	}

	public void add(DeltaEvent event) {
		final Object moduleInfo = ((ModuleInfo)event.receiver());
		// prevent invalid thread exception and/or not showing last added item when updating tree 
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				viewer.refresh(moduleInfo, false);
			}
		});
	}

	public void remove(DeltaEvent event) {
		add(event);
	}
}
