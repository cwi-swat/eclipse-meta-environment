package nl.cwi.sen.metastudio.editor;

import java.io.IOException;

import nl.cwi.sen.metastudio.UserInterface;
import nl.cwi.sen.metastudio.bridge.TextEditorBridge;
import nl.cwi.sen.metastudio.bridge.TextEditorTif;

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
import aterm.ATermAppl;
import aterm.ATermFactory;
import aterm.ATermList;

public class XMLEditor extends TextEditor implements TextEditorTif {
	private ColorManager colorManager;
	private String moduleName;
	private IFile file;
	protected String editorId;
	
	protected ATermFactory factory;
	protected TextEditorBridge bridge;
	
	public XMLEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(this, colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		String fileExtension = "", buttonName = "";
		
		IEditorInput editorInput = getEditorInput();
		if (editorInput instanceof IFileEditorInput) {
			file = ((IFileEditorInput)getEditorInput()).getFile();
			moduleName = file.getName().toString(); 
			fileExtension = file.getFileExtension();
			moduleName = moduleName.substring(0, moduleName.length() - file.getFileExtension().length() - 1);
		}

		editorId = moduleName + file.getFileExtension();

		ATermList buttonList = (ATermList)UserInterface.factory.make("[\"Edit Syntax\"]");
		UserInterface.bridge.postEvent(UserInterface.factory.make("button-selected(<str>,<str>,<list>)", "module-popup", moduleName, buttonList));

		factory = new aterm.pure.PureFactory();
		bridge = new TextEditorBridge(factory, this);

		String[] args = new String[6];
		args[0]="-TB_HOST_NAME";
		args[1]="localhost";
		args[2]="-TB_PORT";
		args[3]="8999";
		args[4]="-TB_TOOL_NAME";
		args[5] ="text-editor";
		
		try {
			bridge.init(args);
			bridge.connect();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		Thread t = new Thread(bridge);
		t.start();
	}
	
	public void dispose() {
		colorManager.dispose();
		bridge.disconnect();
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

	public void moveEditorToFront(String s0) {
	}

	public ATerm tbGetFocusText(String s0, int i1, int i2) {
		final int int1 = i1 - 1;
		final int int2 = i2;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				selectAndReveal(int1, int2);
				String select = ((ITextSelection)doGetSelection()).getText();
				try {
					bridge.sendTerm(factory.make("snd-value(focus-text(<str>,<str>))", editorId, select));
				} catch(IOException e) {
				}
			}
		});
		return null;
	}

	public ATerm editFile(String s0) {
		// TODO When implementing this in a perspective we need to generate a unique file reference
		return factory.make("snd-value(file-id(<str>))", editorId);
	}

	public void recAckEvent(ATerm t0) {
		System.out.println("TE: Ack received");
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see MetaEditor.Bridge.TextEditorTif#tbAddMenus(aterm.ATerm)
	 */
	public void tbAddMenus(ATerm t0) {
		final ATerm term0 = t0;
		final XMLEditor editor = this;
		Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					String strListArg0, strListArg1, strMenu = "";
					IMenuManager parentMenu = getEditorSite().getActionBars().getMenuManager();
					IMenuManager newMenu = new MenuManager();
					for (int i = 0; i < term0.getChildCount(); i++) {
						strListArg0 = ((ATermAppl)term0.getChildAt(i).getChildAt(0)).getName();
						if (strMenu.equals(strListArg0) == false) {
							strMenu = strListArg0;
							newMenu = new MenuManager(strListArg0);
							// TODO insertBefore, prependToGroup or insertAfter? 
							parentMenu.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS, newMenu);
							parentMenu.update(true);
						}
						strListArg1 = ((ATermAppl)term0.getChildAt(i).getChildAt(1)).getName();
						newMenu.add(new AddMetaEditorAction(editor, strListArg0, strListArg1));
						newMenu.update(true);
					}
				}
			});
	}

	public void reloadFile(String s0) {
	}

	public void recTerminate(ATerm t0) {
	}
}
