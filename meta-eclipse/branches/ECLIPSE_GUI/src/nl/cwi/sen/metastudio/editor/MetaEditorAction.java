/*
 * Created on May 22, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.editor;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.adt.texteditor.Menu;

import org.eclipse.jface.action.Action;

import aterm.ATerm;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MetaEditorAction extends Action {
	private Menu _menu;
	private ATerm _editorId;

	MetaEditorAction(ATerm editorId, Menu menu) {
		_menu = menu;
		_editorId = editorId;
		setText(menu.getSub());
	}

	public void run() {
		MetastudioConnection connection = new MetastudioConnection();
		connection.getBridge().postEvent(
			connection.getPureFactory().make(
				"menu-event(<term>,<term>)",
				_editorId,
				_menu.toTerm()));
	}
}
