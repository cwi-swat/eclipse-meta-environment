/*
 * Created on May 22, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.editor;

import org.eclipse.jface.action.Action;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AddMetaEditorAction extends Action {
	String menu, label;
	XMLEditor editor;
	
	AddMetaEditorAction (XMLEditor editor, String menu, String label) {
		this.menu = menu;
		this.label = label;
		this.editor = editor;
		setText(label);
	}
	
	public void run() {
		editor.bridge.postEvent(editor.factory.make("menu-event(<str>,<str>,<str>)", menu, label, editor.editorId));
	}
}
