package nl.cwi.sen.metastudio;

import org.eclipse.ui.IEditorPart;

import aterm.ATerm;

public class EditorRegistryModel {
	private ATerm editorId;
	private String fileName;
	private IEditorPart part;
	
	public EditorRegistryModel(ATerm editorId, String fileName, IEditorPart part) {
		setEditorId(editorId);
		setFileName(fileName);
		setPart(part);
	}

	public ATerm getEditorId() {
		return editorId;
	}

	public void setEditorId(ATerm editorId) {
		this.editorId = editorId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public IEditorPart getPart() {
		return part;
	}

	public void setPart(IEditorPart part) {
		this.part = part;
	}
}
