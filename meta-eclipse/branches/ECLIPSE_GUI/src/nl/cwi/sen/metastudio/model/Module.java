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
public class Module extends Model {
	public Module (String modulePath, String moduleName) {
		super(modulePath, moduleName);
	}

	public void accept(IModelVisitor visitor, Object argument) {
		visitor.visitModule(this, argument);		
	}
}
