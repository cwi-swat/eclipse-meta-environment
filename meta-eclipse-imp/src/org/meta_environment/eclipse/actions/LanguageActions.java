package org.meta_environment.eclipse.actions;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ILanguageActionsContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.meta_environment.eclipse.Activator;

public class LanguageActions implements ILanguageActionsContributor {

	public IAction[] getEditorActions(UniversalEditor editor) {

		Set<Action> actions = new HashSet<Action>();
		// TODO: Request getter for UniversalEditor.fLanguage
		final Language language = editor.fLanguage;
		// TODO: Move extensionPointId to a sensible place
		final String extensionPointId = "org.meta_environment.eclipse.actions.EditorMenus";

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(extensionPointId);

		if (extensionPoint == null) {
			System.err.println("LanguageActions: Could not find extension point: "
					+ extensionPointId);
		} else {
			Set<IConfigurationElement> extensions = getExtensionsForLanguage(
					extensionPoint, language);
			if (extensions.size() != 0) {
				for (IConfigurationElement ext : extensions) {
					actions.addAll(createActionsForEditorMenu(ext, editor));
				}
			} else {
				System.err
						.println("LanguageActions: Could not find any extensions for extension point: "
								+ extensionPointId
								+ " for language "
								+ language.getName());
			}
		}

		// Convert Actions set to array manually (toArray does not seem to work).
		int length = actions.size();
		IAction[] actions_array = new IAction[length];
		int i = 0;
		for (Action a : actions) {
			actions_array[i++] = a;
		}

		return actions_array;
	}

	private Set<Action> createActionsForEditorMenu(
			IConfigurationElement editorMenu, final UniversalEditor editor) {
		Set<Action> actions = new HashSet<Action>();

		IConfigurationElement[] children = editorMenu.getChildren();

		for (IConfigurationElement child : children) {
			actions.addAll(createActionsForEditorMenuItem(child, "", editor));
		}

		return actions;
	}

	private Set<Action> createActionsForEditorMenuItem(
			IConfigurationElement editorMenuItem, String menuPath,
			final UniversalEditor editor) {

		Set<Action> actions = new HashSet<Action>();
		String label = editorMenuItem.getAttribute("name");

		if (!menuPath.equals("")) {
			label = menuPath + "->" + label;
		}

		if (editorMenuItem.getName().equals("EditorSubmenu")) {
			IConfigurationElement[] children = editorMenuItem.getChildren();

			for (IConfigurationElement child : children) {
				actions.addAll(createActionsForEditorMenuItem(child, label,
						editor));
			}
		}

		else if (editorMenuItem.getName().equals("EditorMenuAction")) {
			final String toolbus_action = editorMenuItem
					.getAttribute("toolbus_action");

			Action action = new Action(label) {
				public void run() {
					Activator.getInstance().getTheLanguageActionsTool()
							.PerformAction(toolbus_action, getFileName(editor));
				}
			};
			action.setToolTipText(editorMenuItem.getAttribute("description"));
			actions.add(action);
		}

		return actions;
	}

	private String getFileName(UniversalEditor editor) {
		IParseController controller = editor.getParseController();
		ISourceProject project = controller.getProject();
		IPath path = controller.getPath();
		String filename;

		if (project != null) {
			filename = project.getRawProject().getLocation().append(path)
					.makeAbsolute().toOSString();
		}

		else {
			filename = editor.getParseController().getPath().toOSString();
		}

		return filename;
	}

	public static Set<IConfigurationElement> getExtensionsForLanguage(
			IExtensionPoint extensionPoint, Language language) {

		IConfigurationElement[] elements = extensionPoint
				.getConfigurationElements();
		Set<IConfigurationElement> result = new HashSet<IConfigurationElement>();

		if (elements != null) {
			for (int n = 0; n < elements.length; n++) {
				IConfigurationElement element = elements[n];
				final String attrValue = element
						.getAttribute(Language.LANGUAGE_ID_ATTR);

				if (isSameLanguageString(attrValue, language.getName())) {
					result.add(element);
				}
			}
		}

		return result;
	}

	public static boolean isSameLanguageString(String l1, String l2) {
		return l1.toLowerCase().equals(l2.toLowerCase());
	}
}
