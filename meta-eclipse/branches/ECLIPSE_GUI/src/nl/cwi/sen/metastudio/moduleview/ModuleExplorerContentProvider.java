/*
 * Created on Jun 4, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.moduleview;

import java.util.Iterator;

import nl.cwi.sen.metastudio.model.DeltaEvent;
import nl.cwi.sen.metastudio.model.Directory;
import nl.cwi.sen.metastudio.model.IModelListener;
import nl.cwi.sen.metastudio.model.Model;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ModuleExplorerContentProvider implements ITreeContentProvider, IModelListener {
	private static Object[] EMPTY_ARRAY = new Object[0];
	protected TreeViewer viewer;
	
	public void dispose() {
	}

	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof Directory) {
			Directory module = (Directory)parentElement;
			return concat(module.getDirectories().toArray(), module.getModules().toArray());
		}
		return EMPTY_ARRAY;
	}

	protected Object[] concat(Object[] object, Object[] more) {
		Object[] both = new Object[object.length + more.length];
		System.arraycopy(object, 0, both, 0, object.length);
		System.arraycopy(more, 0, both, object.length, more.length);
		return both;
	}

	public Object getParent(Object element) {
		if(element instanceof Model) {
			return ((Model)element).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void add(DeltaEvent event) {
		final Object directory = ((Model)event.receiver()).getParent();
		// prevent invalid thread exception and/or not showing last added item when updating tree 
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				viewer.refresh(directory, false);
			}
		});
	}

	public void remove(DeltaEvent event) {
		add(event);
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer)viewer;
		if(oldInput != null) {
			removeListener((Directory)oldInput);
		}
		if(newInput != null) {
			addListener((Directory)newInput);
		}
	}

	protected void removeListener(Directory directory) {
		directory.removeListener(this);
		for (Iterator iterator = directory.getDirectories().iterator(); iterator.hasNext();) {
			Directory aDir = (Directory) iterator.next();
			removeListener(aDir);
		}
	}
	
	protected void addListener(Directory directory) {
		directory.addListener(this);
		for (Iterator iterator = directory.getDirectories().iterator(); iterator.hasNext();) {
			Directory aDir = (Directory) iterator.next();
			addListener(aDir);
		}
	}
}
