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
	
	public void addEditor(ATerm editorID, String fileName, IEditorPart part) {
		list.add(new EditorRegistryModel(editorID, fileName, part));
	}

	public void removeEditor(ATerm editorID) {
		for (int i = 0; i < list.size(); i++) {
			if (editorID.equals(((EditorRegistryModel)list.get(i)).getEditorID())) {
				list.remove(i);
			}
		}
	}
	
	public void removeEditor(IEditorPart part) {
		for (int i = 0; i < list.size(); i++) {
			if (part.equals(((EditorRegistryModel)list.get(i)).getEditorPart())) {
				list.remove(i);
			}
		}
	}

	public IEditorPart getEditorPartByEditorID(ATerm editorID) {
		for (int i = 0; i < list.size(); i++) {
			if (editorID.equals(((EditorRegistryModel)list.get(i)).getEditorID())) {
				return ((EditorRegistryModel)list.get(i)).getEditorPart();
			}
		}
		return null;
	}
	
	public ATerm getEditorIDByEditorPart(IEditorPart part) {
		for (int i = 0; i < list.size(); i++) {
			if (part.equals(((EditorRegistryModel)list.get(i)).getEditorPart())) {
				return ((EditorRegistryModel)list.get(i)).getEditorID();
			}
		}
		return null;
	}
	
	public String getFileNameByEditorID(ATerm editorID) {
		for (int i = 0; i < list.size(); i++) {
			if (editorID.equals(((EditorRegistryModel)list.get(i)).getEditorID())) {
				return ((EditorRegistryModel)list.get(i)).getFileName();
			}
		}
		return null;
	}
	
}
