package org.meta_environment.eclipse.terms;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ILanguageActionsContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

public class EditorActionContributor implements ILanguageActionsContributor {

	public IAction[] getEditorActions(UniversalEditor editor) {
		final UniversalEditor e = editor;
		List<String> languages = TermEditorTools.getInstance().getLanguages();
		
		IAction[] entries = new IAction[languages.size()];
		int i = 0;
		
		for (final String l : languages) {
		  Action a = new Action("Use " + l) {
			public void run() {
				IParseController controller = e.getParseController();
				ISourceProject project = controller.getProject();
				IPath path = controller.getPath();
				String filename;
				
				if (project != null) {
				  filename = project.getRawProject().getLocation().append(path).makeAbsolute().toOSString();
				}
				else {
				  filename = e.getParseController().getPath().toOSString();
				}
				
				TermEditorTools.getInstance().setLanguage(filename, l);
				e.fParserScheduler.schedule(1000);
			}
		  };
		  entries[i++] = a; 	
		}
		
		return entries;
	}

}
