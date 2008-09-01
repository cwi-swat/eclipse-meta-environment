package org.meta_environment.eclipse.viz.graph;

import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class GraphInput implements IEditorInput {

	private String name;
	private IValue value;
	
	public GraphInput(String name, IValue value) {
		this.name = name;
		this.value = value;
	}
	
	public boolean equals(Object other) {
		if (other instanceof GraphInput) {
			GraphInput otherInput = (GraphInput) other;
			if (value != null && name != null) {
				return value.equals(otherInput.getValue()) && name.equals(otherInput.getName());
			}
		}
		return false;
	}
	
	public IValue getValue() {
		return value;
	}
	
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		if (name != null) {
			return name;
		}
		return "";
	}

	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getToolTipText() {
		return getName();
	}

	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

}
