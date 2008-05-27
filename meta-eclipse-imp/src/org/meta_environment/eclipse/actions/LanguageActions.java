package org.meta_environment.eclipse.actions;

import java.util.Vector;

import nl.cwi.sen1.configapi.Factory;
import nl.cwi.sen1.configapi.types.ActionDescription;
import nl.cwi.sen1.configapi.types.Event;

import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.services.ILanguageActionsContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.meta_environment.eclipse.Tool;

import aterm.ATermList;
import aterm.pure.PureFactory;

public class LanguageActions extends Tool implements
		ILanguageActionsContributor {

	private Factory configFactory;

	public LanguageActions() {
		super("language-actions");
		System.out.println("LanguageActions constructor called.");
		this.configFactory = Factory.getInstance(new PureFactory());
	}

	public IAction[] getEditorActions(UniversalEditor editor) {

		System.out.println("attempting to get actions for syntax-editor.");
		ATermList actionList = (ATermList) sendRequest(factory
				.make("get-actions(syntax-editor)"));
		Vector actions = new Vector();

		while (!actionList.isEmpty()) {

			ActionDescription desc = configFactory
					.ActionDescriptionFromTerm(actionList.getFirst());
			Event action = desc.getEvent();

			if (action.isMenu()) {
				actions.add(new Action(action.getName()) {
					public void run() {
						System.out.println("menu activated");
					}
				});

			}
			actionList = actionList.getNext();
		}

		return (Action[]) actions.toArray();
	}
}
