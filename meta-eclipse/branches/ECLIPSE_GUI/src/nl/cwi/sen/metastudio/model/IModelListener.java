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
public interface IModelListener {
	public void add(DeltaEvent event);
	public void remove(DeltaEvent event);
}
