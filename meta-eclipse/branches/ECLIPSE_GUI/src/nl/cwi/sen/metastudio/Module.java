/*
 * Created on Aug 1, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio;

import org.eclipse.core.runtime.IPath;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Module {
	static IPath fullPath;
	
	public Module() {
	}
	
	public IPath getFullPath() {
		return fullPath;
	}
	
	public void setFullPath(IPath path) {
		fullPath = path;
	}
}
