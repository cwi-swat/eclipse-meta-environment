package org.meta_environment.eclipse.actions;

import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.services.ILanguageActionsContributor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.meta_environment.eclipse.Tool;

import aterm.ATerm;

public class DynamicLanguageActions extends Tool implements ILanguageActionsContributor {

	public DynamicLanguageActions() {
		super("dynamic-language-actions");
	}

	public void registerAction(String language, ATerm actionDef) {
		System.err.println("register " + language + " " + actionDef);	
	}
	
	public void unregisterAction(String language, ATerm actionDef) {
		System.err.println("unregister " + language + " " + actionDef);
	}
	
	public void contributeToEditorMenu(UniversalEditor editor,
			IMenuManager menuManager) {
		System.err.println("contributing dynamic actions");
	}

	public void contributeToMenuBar(UniversalEditor editor, IMenuManager menu) { }
	public void contributeToStatusLine(UniversalEditor editor, IStatusLineManager statusLineManager) { }
	public void contributeToToolBar(UniversalEditor editor, IToolBarManager toolbarManager) { }
}
