package nl.cwi.sen.metastudio.graphview;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.UserInterface;

import org.eclipse.jface.action.Action;

import aterm.ATerm;

public class GraphPartAction extends Action {
	private ATerm actionType;
	private ATerm action;
	private String moduleName;

	GraphPartAction(String label, ATerm actionType, ATerm action, String moduleName) {
		this.actionType = actionType;
		this.action = action;
		this.moduleName = moduleName;
		
		setText(label);
	}

	public void run() {
		MetastudioConnection connection = UserInterface.getConnection();

		ATerm event = connection.getPureFactory().make("button-selected(<term>, <str>, <term>)",
					   actionType, moduleName, action);
		connection.getBridge().postEvent(event);
	}
}
