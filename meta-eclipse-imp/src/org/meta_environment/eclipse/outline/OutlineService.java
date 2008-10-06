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
		System.out.println("Whoohoo!!!!");
	}
	
	public Image getImage(Object element) {
		// TODO Implement
		System.out.println("Whoohoo!!!!");
		return null;
	}

	public String getText(Object element) {
		// TODO Implement
		System.out.println("Whoohoo!!!!");
		return null;
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Implement
		System.out.println("Whoohoo!!!!");
	}

	public void dispose() {
		// TODO Implement
		System.out.println("Whoohoo!!!!");
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Implement
		System.out.println("Whoohoo!!!!");
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Implement
		System.out.println("Whoohoo!!!!");
	}
}
