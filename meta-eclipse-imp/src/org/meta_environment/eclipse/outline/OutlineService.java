package org.meta_environment.eclipse.outline;

import org.eclipse.imp.editor.ModelTreeNode;
import org.eclipse.imp.services.ILabelProvider;
import org.eclipse.imp.services.base.TreeModelBuilderBase;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import toolbus.adapter.AbstractTool;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class OutlineService extends TreeModelBuilderBase implements ILabelProvider{
	private final OutlineTool outlineTool;
	private final PureFactory factory;
	
	public OutlineService(){
		super();
		
		outlineTool = OutlineTool.getInstance();
		factory = AbstractTool.getFactory();
	}
	
	private void visitNode(ATermAppl node){
		pushSubItem(node.getName());
		
		if(node.getArity() != 0){
			ATermList children = (ATermList) node.getArgument(0);
			
			while(!children.isEmpty()){
				visitNode((ATermAppl) children.getFirst());
				children = children.getNext();
			}
		}
		
		popSubItem();
	}
	
	public void visitTree(Object root){
		//ATerm parseTree = (ATerm) root;
		//ATermAppl factsStore = outlineTool.sendRequest(factory.make("processParseTree(<term>)", parseTree));
		
		// Temp
		ATermAppl factsStore = (ATermAppl) factory.make("outline(Sorts([<str>]), Productions([<str>, <str>]))", "BoolCon", "\"true\" -> BoolCon", "\"false\" -> BoolCon");
		// End temp
		
		ATerm[] children = factsStore.getArgumentArray();
		for(int i = 0; i < children.length; i++){
			visitNode((ATermAppl) children[i]);
		}
	}

	public String getText(Object element){
		ModelTreeNode mtn = (ModelTreeNode) element;
		return (String) mtn.getASTNode();
	}
	
	public Image getImage(Object element){
		// We don't have icons.
		return null;
	}

	public boolean isLabelProperty(Object element, String property){
		// We don't have properties.
		return false;
	}

	public void addListener(ILabelProviderListener listener){
		// We don't need this.
	}

	public void removeListener(ILabelProviderListener listener){
		// We don't need this.
	}

	public void dispose(){
		// Ignore this.
	}
}
