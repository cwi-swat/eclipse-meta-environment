package org.meta_environment.eclipse.outline;

import org.eclipse.imp.services.ILabelProvider;
import org.eclipse.imp.services.base.TreeModelBuilderBase;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class OutlineService extends TreeModelBuilderBase implements ILabelProvider{
	
	public OutlineService(){
		super();
	}
	
	public void visitTree(Object root){
		// TODO Implement
	}
	
	public Image getImage(Object element) {
		// TODO Implement
		return null;
	}

	public String getText(Object element) {
		// TODO Implement
		return null;
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Implement
	}

	public void dispose() {
		// TODO Implement
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Implement
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Implement
	}
}
