/*
 * Created on May 22, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.editor;

import nl.cwi.sen.metastudio.MetastudioConnection;

import org.eclipse.jface.action.Action;

import aterm.ATerm;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MetaEditorAction extends Action {
	private ATerm _menuEvent;
	private ATerm _editorId;
	
	MetaEditorAction (ATerm editorId, ATerm menuEvent, String label) {
		_menuEvent = menuEvent;
		_editorId = editorId;
		setText(label);
		System.out.println("Added menuEvent: " + menuEvent);
	}
	
	public void run() {
		MetastudioConnection connection = new MetastudioConnection();
		connection.getBridge().postEvent(connection.getFactory().make("menu-event(<term>,<term>)", _editorId, _menuEvent));
	}
}
