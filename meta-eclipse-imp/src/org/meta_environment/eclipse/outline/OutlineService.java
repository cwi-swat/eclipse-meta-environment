package org.meta_environment.eclipse.outline;

import org.eclipse.imp.services.ILabelProvider;
import org.eclipse.imp.services.base.TreeModelBuilderBase;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import toolbus.adapter.AbstractTool;
import aterm.ATerm;
import aterm.pure.PureFactory;

public class OutlineService extends TreeModelBuilderBase implements ILabelProvider{
	private final OutlineTool outlineTool;
	private final PureFactory factory;
	
	public OutlineService(){
		super();
		
		outlineTool = OutlineTool.getInstance();
		factory = AbstractTool.getFactory();
	}
	
	public void visitTree(Object root){
		ATerm parseTree = (ATerm) root;
		ATerm factsStore = outlineTool.sendRequest(factory.make("processParseTree(<term>)", parseTree));
		
		System.out.println(factsStore);
		
		// TODO Implement
	}
	
	public Image getImage(Object element){
		// TODO Implement
		return null;
	}

	public String getText(Object element){
		// TODO Implement
		return null;
	}

	public void addListener(ILabelProviderListener listener){
		// TODO Implement
	}

	public void dispose(){
		// TODO Implement
	}

	public boolean isLabelProperty(Object element, String property){
		// TODO Implement
		return false;
	}

	public void removeListener(ILabelProviderListener listener){
		// TODO Implement
	}
}
