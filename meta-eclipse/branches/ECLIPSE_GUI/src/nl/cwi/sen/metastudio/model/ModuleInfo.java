/*
 * Created on Jun 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.model;

import java.util.ArrayList;
import java.util.List;


/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ModuleInfo  {
	private List entryList; 
	private IModelListener listener;
	
	public ModuleInfo() {
		entryList = new ArrayList();
	}
	
	public void addListener(IModelListener listener) {
		this.listener = listener;
	}

	public void removeListener(IModelListener listener) {
		if (this.listener.equals(listener)) {
			this.listener = null;
		}
	}

	public void add(ModuleInfoElement info) {
		entryList.add(info);
		listener.add(new DeltaEvent(this));
	}
	
	public void clear() {
		entryList.clear();
	}
	
	public List getEntries() {
		return entryList;
	}
}
