package nl.cwi.sen.metastudio;

import org.eclipse.ui.IEditorPart;

import aterm.ATerm;

public class EditorRegistryModel {
	private ATerm _editorId;
	private String _fileName;
	private IEditorPart _part;
	
	public EditorRegistryModel(ATerm editorId, String fileName, IEditorPart part) {
		_editorId = editorId;
		_fileName = fileName;
		_part = part;
	}
	
	public ATerm geteditorId() {
		return _editorId;
	}
	
	public String getFileName() {
		return _fileName;
	}
	
	public IEditorPart getEditorPart() {
		return _part;
	}
}
