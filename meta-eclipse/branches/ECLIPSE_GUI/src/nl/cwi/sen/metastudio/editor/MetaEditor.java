package nl.cwi.sen.metastudio.editor;

import java.awt.PopupMenu;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.PerspectiveFactory;
import nl.cwi.sen.metastudio.UserInterface;
import nl.cwi.sen.metastudio.adt.editordata.Area;
import nl.cwi.sen.metastudio.adt.editordata.Focus;
import nl.cwi.sen.metastudio.adt.texteditor.ActionList;
import nl.cwi.sen.metastudio.adt.texteditor.Menu;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.editors.text.TextEditor;

import aterm.ATerm;

public class MetaEditor extends TextEditor {
	private ColorManager colorManager;
	private String moduleName;
	private IFile file;

	private PopupMenu popupMenu = new PopupMenu();

	public MetaEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(
			new MetaEditorConfiguration(this, colorManager));
		setDocumentProvider(new MetaEditorDocumentProvider());
	}

	public void createPartControl(Composite parent) {
		MetastudioConnection connection = UserInterface.getConnection();
		super.createPartControl(parent);

		String fileExtension = "", buttonName = "";

		IEditorInput editorInput = getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			file = ((IFileEditorInput) getEditorInput()).getFile();
			moduleName = file.getName().toString();
			fileExtension = file.getFileExtension();
			moduleName =
				moduleName.substring(
					0,
					moduleName.length() - file.getFileExtension().length() - 1);
		}
	}

	public void dispose() {
		colorManager.dispose();
		//super.dispose();
	}

	public void setActions(ATerm editorId, ActionList actionList) {
		String menuLabel = "";
		IMenuManager parentMenu =
			getEditorSite().getActionBars().getMenuManager();
		IMenuManager newMenu = new MenuManager();

		while (actionList.isEmpty() == false) {
			Menu menu = actionList.getHead();
			actionList = actionList.getTail();

			if (menuLabel.equals(menu.getMain()) == false) {
				menuLabel = menu.getMain();
				newMenu = new MenuManager(menuLabel);
				// insertBefore, prependToGroup or insertAfter?
				parentMenu.insertBefore(
					IWorkbenchActionConstants.MB_ADDITIONS,
					newMenu);
				parentMenu.update(true);
			}
			newMenu.add(new MetaEditorAction(editorId, menu));
			newMenu.update(true);
		}
	}

	public void getContents(ATerm editorId, Focus focus) {
		Area area = focus.getArea();
		int start = area.getStart() - 1;
		int length = area.getLength();
		selectAndReveal(start, length);
		String select = ((ITextSelection) doGetSelection()).getText();

		MetastudioConnection connection = UserInterface.getConnection();
		connection.getBridge().postEvent(
			connection.getPureFactory().make(
				"contents(<term>,<str>)",
				editorId,
				select));
	}

	public void setFocus(Focus focus) {
		Area area = focus.getArea();
		int start = area.getStart() - 1;
		int length = area.getLength();
		selectAndReveal(start, length);
		PerspectiveFactory.getStatusLineManager().setMessage(
			"Focus symbol: " + focus.getSort());
	}

	public void clearFocus() {
		selectAndReveal(0, 0);
	}
}