package nl.cwi.sen.metastudio.editor;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.datastructures.ActionList;
import nl.cwi.sen.metastudio.datastructures.Menu;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.editors.text.TextEditor;

import aterm.ATerm;

public class MetaEditor extends TextEditor {
	private ColorManager colorManager;
	private String moduleName;
	private IFile file;
	
	public MetaEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new MetaEditorConfiguration(this, colorManager));
		setDocumentProvider(new MetaEditorDocumentProvider());
	}
	
	public void createPartControl(Composite parent) {
		MetastudioConnection connection = new MetastudioConnection();
		super.createPartControl(parent);
		
		String fileExtension = "", buttonName = "";
		
		IEditorInput editorInput = getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			file = ((IFileEditorInput)getEditorInput()).getFile();
			moduleName = file.getName().toString(); 
			fileExtension = file.getFileExtension();
			moduleName = moduleName.substring(0, moduleName.length() - file.getFileExtension().length() - 1);
		}
	}
	
	public void dispose() {
		colorManager.dispose();
		//super.dispose();
	}
	
	public void tbSetCharPos(String s0, int i1) {
		final int int1 = i1 - 1;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				selectAndReveal(int1,int1);
			}
		});
	}

	public void tbSetMsg(String s0) {
		final String str0 = s0;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				IStatusLineManager s = getEditorSite().getActionBars().getStatusLineManager();
				s.setMessage(str0);
			}
		});
	}

	public void tbUnsetFocus(String s0) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				selectAndReveal(0,0);
			}
		});
	}

	public void tbSetFocus(String s0, String s1, int i2, int i3) {
		final int int2 = i2 - 1;
		final int int3 = i3;
		final String str1 = s1;
		Display.getDefault().asyncExec(new Runnable () {
			public void run() {
				IStatusLineManager s = getEditorSite().getActionBars().getStatusLineManager();
				s.setMessage(str1);
				selectAndReveal(int2,int3);
			}
		});
	}

	public ATerm tbGetFocusText(String s0, int i1, int i2) {
		final int int1 = i1 - 1;
		final int int2 = i2;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				selectAndReveal(int1, int2);
				String select = ((ITextSelection)doGetSelection()).getText();
//				try {
//					bridge.sendTerm(factory.make("snd-value(focus-text(<str>,<str>))", editorId, select));
//				} catch(IOException e) {
//				}
			}
		});
		return null;
	}

	public void addActions(ATerm editorId, ActionList actionList) {
		String menuLabel = "";
		IMenuManager parentMenu = getEditorSite().getActionBars().getMenuManager();
		IMenuManager newMenu = new MenuManager();
		
		while (actionList.isEmpty() == false) {
			Menu menu = actionList.getHead();
			actionList = actionList.getTail();			
			
			if (menuLabel.equals(menu.getMain()) == false) {
				menuLabel = menu.getMain();
				newMenu = new MenuManager(menuLabel);
				// TODO insertBefore, prependToGroup or insertAfter? 
				parentMenu.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS, newMenu);
				parentMenu.update(true);
			}
			newMenu.add(new MetaEditorAction(editorId, menu));
			newMenu.update(true);
		}
	}
}