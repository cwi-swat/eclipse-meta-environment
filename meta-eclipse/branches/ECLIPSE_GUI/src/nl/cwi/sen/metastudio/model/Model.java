/*
 * Created on Jun 4, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.model;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class Model {
	protected Directory parent;
	protected String moduleName, modulePath;
	protected IModelListener listener = NullDeltaListener.getSoleInstance();
	
	public Model() {
	}

	protected void fireAdd(Object added) {
		listener.add(new DeltaEvent(added));
	}

	protected void fireRemove(Object removed) {
		listener.remove(new DeltaEvent(removed));
	}
	
	public void setName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public Directory getParent() {
		return parent;
	}
	
	/* The receiver should visit the toVisit object and
	 * pass along the argument. */
	public abstract void accept(IModelVisitor visitor, Object argument);
	
	public void addListener(IModelListener listener) {
		this.listener = listener;
	}
	
	public void removeListener(IModelListener listener) {
		if(this.listener.equals(listener)) {
			this.listener = NullDeltaListener.getSoleInstance();
		}
	}

	public Model(String modulePath, String moduleName) {
		this.moduleName = moduleName;
		this.modulePath = modulePath;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	
	public String getModulePath() {
		return modulePath;
	}
}
