package org.meta_environment.eclipse.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.base.DefaultLanguageActionsContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

public class LanguageActions extends DefaultLanguageActionsContributor {

	private final String extensionPointId = "org.meta_environment.eclipse.actions.EditorMenus";

	public void contributeToEditorMenu(UniversalEditor editor, IMenuManager menu) {
		// TODO: Bugzilla: Request getter for UniversalEditor.fLanguage
		final Language language = editor.fLanguage;
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(extensionPointId);

		if (extensionPoint != null) {
			List<IConfigurationElement> extensions = getExtensionsForLanguage(
					extensionPoint, language);
			if (extensions.size() != 0) {
				for (IConfigurationElement ext : extensions) {
					createActionsForEditorMenu(ext, editor, menu);
				}
			}
		}
	}

	private List<Action> createActionsForEditorMenu(
			IConfigurationElement editorMenu, final UniversalEditor editor, IMenuManager menu) {
		List<Action> actions = new ArrayList<Action>();

		IConfigurationElement[] children = editorMenu.getChildren();

		for (IConfigurationElement child : children) {
			actions.addAll(createActionsForEditorMenuItem(child, editor, menu));
		}

		return actions;
	}

	private List<Action> createActionsForEditorMenuItem(
			IConfigurationElement editorMenuItem, 
			final UniversalEditor editor, IMenuManager menu) {

		List<Action> actions = new ArrayList<Action>();
		String name = editorMenuItem.getAttribute("name");

		if (editorMenuItem.getName().equals("EditorSubmenu")) {
			IConfigurationElement[] children = editorMenuItem.getChildren();
			IMenuManager subMenu = new MenuManager(name);
			
			for (IConfigurationElement child : children) {
				actions.addAll(createActionsForEditorMenuItem(child, editor, subMenu));
			}
			
			menu.add(subMenu);
		}

		else if (editorMenuItem.getName().equals("EditorMenuAction")) {
			addActionForEditorMenuAction(editorMenuItem, name, editor, menu);
		}

		return actions;
	}

	private void addActionForEditorMenuAction(
			IConfigurationElement editorMenuAction, String name,
			final UniversalEditor editor, IMenuManager menu) {
		final String toolbus_action = editorMenuAction
				.getAttribute("toolbus_action");
		final String language = editor.fLanguage.getName();

		Action action = new Action(name) {
			public void run() {
				LanguageActionsTool.getInstance()
						.PerformAction(toolbus_action, language, getFileName(editor));
			}
		};
		action.setToolTipText(editorMenuAction.getAttribute("description"));

		menu.add(action);
	}

	public static String getFileName(UniversalEditor editor) {
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

	public static List<IConfigurationElement> getExtensionsForLanguage(
			IExtensionPoint extensionPoint, Language language) {

		IConfigurationElement[] elements = extensionPoint
				.getConfigurationElements();
		List<IConfigurationElement> result = new ArrayList<IConfigurationElement>();

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
