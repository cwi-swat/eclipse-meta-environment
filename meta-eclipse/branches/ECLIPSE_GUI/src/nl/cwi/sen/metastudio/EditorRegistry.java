package nl.cwi.sen.metastudio;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IEditorPart;

import aterm.ATerm;

public class EditorRegistry {
	private static List _list;
	
	public EditorRegistry() {
		if (_list == null) {
			_list = new ArrayList();
		}
	}
	
	public void addEditor(ATerm editorId, String fileName, IEditorPart part) {
		_list.add(new EditorRegistryModel(editorId, fileName, part));
		System.out.println("Editor added: " + editorId + ", " + fileName);
	}

	public void removeEditor(ATerm editorId) {
		for (int i = 0; i < _list.size(); i++) {
			if (editorId.equals(((EditorRegistryModel)_list.get(i)).geteditorId())) {
				_list.remove(i);
			}
		}
	}
	
	public void removeEditor(IEditorPart part) {
		for (int i = 0; i < _list.size(); i++) {
			if (part.equals(((EditorRegistryModel)_list.get(i)).getEditorPart())) {
				_list.remove(i);
			}
		}
	}

	public IEditorPart getEditorPartByeditorId(ATerm editorId) {
		for (int i = 0; i < _list.size(); i++) {
			if (editorId.equals(((EditorRegistryModel)_list.get(i)).geteditorId())) {
				return ((EditorRegistryModel)_list.get(i)).getEditorPart();
			}
		}
		return null;
	}
	
	public ATerm geteditorIdByEditorPart(IEditorPart part) {
		for (int i = 0; i < _list.size(); i++) {
			if (part.equals(((EditorRegistryModel)_list.get(i)).getEditorPart())) {
				return ((EditorRegistryModel)_list.get(i)).geteditorId();
			}
		}
		return null;
	}
	
	public String getFileNameByeditorId(ATerm editorId) {
		for (int i = 0; i < _list.size(); i++) {
			if (editorId.equals(((EditorRegistryModel)_list.get(i)).geteditorId())) {
				return ((EditorRegistryModel)_list.get(i)).getFileName();
			}
		}
		return null;
	}
	
}
