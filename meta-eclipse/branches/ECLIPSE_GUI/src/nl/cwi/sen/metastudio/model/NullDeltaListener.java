/*
 * Created on Jun 5, 2003
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
public class NullDeltaListener implements IModelListener {
	protected static NullDeltaListener soleInstance = new NullDeltaListener();
	public static NullDeltaListener getSoleInstance() {
		return soleInstance;
	}
	
	public void add(DeltaEvent event) {}

	public void remove(DeltaEvent event) {}
}
