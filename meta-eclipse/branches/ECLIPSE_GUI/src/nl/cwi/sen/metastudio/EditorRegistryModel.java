package nl.cwi.sen.metastudio;

import org.eclipse.ui.IEditorPart;

import aterm.ATerm;

public class EditorRegistryModel {
	private ATerm _editorID;
	private String _fileName;
	private IEditorPart _part;
	
	public EditorRegistryModel(ATerm editorID, String fileName, IEditorPart part) {
		_editorID = editorID;
		_fileName = fileName;
		_part = part;
	}
	
	public ATerm getEditorID() {
		return _editorID;
	}
	
	public String getFileName() {
		return _fileName;
	}
	
	public IEditorPart getEditorPart() {
		return _part;
	}
}
