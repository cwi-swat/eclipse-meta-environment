/*
 * Created on May 22, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.editor;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.datastructures.Event_Menu;
import nl.cwi.sen.metastudio.datastructures.Menu;

import org.eclipse.jface.action.Action;

import aterm.ATerm;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MetaEditorAction extends Action {
	private Menu _menuEvent;
	private ATerm _editorId;
	
	MetaEditorAction (ATerm editorId, Menu menuEvent) {
		_menuEvent = menuEvent;
		_editorId = editorId;
		setText(menuEvent.getSub());
	}
	
	public void run() {
		MetastudioConnection connection = new MetastudioConnection();
		Event_Menu event = new Event_Menu();
	}
}
