package nl.cwi.sen.metastudio;

import aterm.ATerm;
import aterm.ATermList;

public class PopupMenu {
	private static ATermList _menuItems;
	private static ATerm _actionType;
	private static String _moduleName;
	private static boolean _loaded;
	
	public PopupMenu() {
	}
	
	public void setMenu(ATerm actionType, String moduleName, ATermList menuItems) {
		_actionType = actionType;
		_moduleName = moduleName;
		_menuItems = menuItems;
		_loaded = true;
	}
	
	public ATermList getMenu() {
		return _menuItems;
	}
	
	public ATerm getActionType() {
		return _actionType;
	}
	
	public String getModuleName() {
		return _moduleName;
	}
	
	public void invalidate() {
		_loaded = false;
	}
	
	public boolean getLoadedState() {
		return _loaded;
	}
}
