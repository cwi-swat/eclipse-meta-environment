package nl.cwi.sen.metastudio;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IEditorPart;

import aterm.ATerm;

public class EditorRegistry {
	private List list;
	
	public EditorRegistry() {
		list = new ArrayList();
	}
	
	public void addEditor(ATerm editorId, String fileName, IEditorPart part) {
		list.add(new EditorRegistryModel(editorId, fileName, part));
	}

	public void removeEditor(ATerm editorId) {
		for (int i = 0; i < list.size(); i++) {
			if (editorId.equals(((EditorRegistryModel)list.get(i)).getEditorId())) {
				list.remove(i);
			}
		}
	}
	
	public void removeEditor(IEditorPart part) {
		for (int i = 0; i < list.size(); i++) {
			if (part.equals(((EditorRegistryModel)list.get(i)).getPart())) {
				list.remove(i);
			}
		}
	}

	public IEditorPart getEditorPartByeditorId(ATerm editorId) {
		for (int i = 0; i < list.size(); i++) {
			if (editorId.equals(((EditorRegistryModel)list.get(i)).getEditorId())) {
				return ((EditorRegistryModel)list.get(i)).getPart();
			}
		}
		return null;
	}
	
	public ATerm geteditorIdByEditorPart(IEditorPart part) {
		for (int i = 0; i < list.size(); i++) {
			if (part.equals(((EditorRegistryModel)list.get(i)).getPart())) {
				return ((EditorRegistryModel)list.get(i)).getEditorId();
			}
		}
		return null;
	}
	
	public String getFileNameByeditorId(ATerm editorId) {
		for (int i = 0; i < list.size(); i++) {
			if (editorId.equals(((EditorRegistryModel)list.get(i)).getEditorId())) {
				return ((EditorRegistryModel)list.get(i)).getFileName();
			}
		}
		return null;
	}
	
}
