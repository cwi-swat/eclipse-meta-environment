/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package nl.cwi.sen.metastudio.editor;

import nl.cwi.sen.metastudio.EditorRegistry;
import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.UserInterface;

import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextViewer;

import aterm.ATerm;

public class MetaEditorDoubleClickStrategy
	implements ITextDoubleClickStrategy {
	private MetaEditor _editor;

	public MetaEditorDoubleClickStrategy(MetaEditor editor) {
		_editor = editor;
	}

	public void doubleClicked(ITextViewer part) {
		EditorRegistry registry = UserInterface.getEditorRegistry();
		ATerm editorId = registry.geteditorIdByEditorPart(_editor);
		MetastudioConnection connection = UserInterface.getConnection();
		int pos = part.getSelectedRange().x;

		if (pos < 0)
			return;

		Integer location = new Integer(pos - 1);
		connection.getBridge().postEvent(
			connection.getPureFactory().make(
				"mouse-event(<term>,<int>)",
				editorId,
				location));
	}
}