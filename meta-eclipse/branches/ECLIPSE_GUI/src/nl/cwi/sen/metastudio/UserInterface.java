package nl.cwi.sen.metastudio;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.widgets.Display;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermFactory;
import aterm.ATermList;
import nl.cwi.sen.metastudio.bridge.UserEnvironmentBridge;
import nl.cwi.sen.metastudio.bridge.UserEnvironmentTif;
import nl.cwi.sen.metastudio.moduleview.ModuleExplorerPart;
import nl.cwi.sen.metastudio.moduleview.ModuleInfoPart;

public class UserInterface implements UserEnvironmentTif, Runnable {
	private static IStatusLineManager statusLineMgr;
	
	public static ATermFactory factory;
	public static UserEnvironmentBridge bridge;
	private static Thread t;
	private static PopupMenu popupMenu;

	private ATerm ACTION_MENUBAR;
	private ATerm ACTION_TOOLBAR;
	private ATerm ACTION_MODULE_POPUP;
	private ATerm ACTION_NEW_MODULE_POPUP;

	public UserInterface() {
	}
	
	public UserInterface(IStatusLineManager statusLineManager) {
		statusLineMgr = statusLineManager;
	}
	
	private void initialize() {
		popupMenu = new PopupMenu();

		initializeATermPatterns();
	}

	public void run() {
		factory = new aterm.pure.PureFactory();
		bridge = new UserEnvironmentBridge(factory, this);
		
		String[] args = new String[6];
		args[0]="-TB_HOST_NAME";
		args[1]="localhost";
		args[2]="-TB_PORT";
		args[3]="9000";
		args[4]="-TB_TOOL_NAME";
		args[5] ="user-environment";
		
		try {
			bridge.init(args);
		} catch (UnknownHostException e) {
		}
		try {
			bridge.connect();
		} catch (IOException e) {
		}
		
		t = new Thread(bridge);
		t.start();

		initialize();
	}

	private void initializeATermPatterns() {
		ACTION_MENUBAR = factory.parse("studio-menubar");
		ACTION_TOOLBAR = factory.parse("studio-toolbar");
		ACTION_MODULE_POPUP = factory.parse("module-popup");
		ACTION_NEW_MODULE_POPUP = factory.parse("new-module-popup");
	}

	public void addStatus(ATerm t0, String s1) {
	}

	public void moduleInfo(String module, ATerm info) {
		ATermList pairs = (ATermList) info;
		List entries = new LinkedList();

		while (!pairs.isEmpty()) {
			ATermList pair = (ATermList) pairs.getFirst();

			String name = pair.getFirst().toString();

			String value;
			ATerm valueTerm = pair.getNext().getFirst();
			if (valueTerm.getType() == ATerm.APPL) {
				value = ((ATermAppl) valueTerm).getName();
			} else {
				value = valueTerm.toString();
			}
			String[] entry = {name, value};
			entries.add(entry);

			pairs = pairs.getNext();
		}

		final String finalModule = module;
		final List finalEntries = entries;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ModuleInfoPart.setModuleInfo(finalModule, finalEntries);
			}
		});
	}

	public void addStatusf(ATerm t0, String s1, ATerm t2) {
		final String message = formatString(s1, (ATermList)t2);
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				statusLineMgr.setMessage(message);
			}
		});
	}

	public void errorf(String s0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void graphLayouted(String s0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void initializeUi(String s0) {
//		final String str0 = s0;
//		Display.getDefault().asyncExec(new Runnable() {
//			public void run() {
//				ModuleExplorerPart.addModule(str0);
//			}
//		});
	}

	public void clearHistory() {
		// TODO Auto-generated method stub
	}

	public void deleteModules(ATerm t0) {
		// TODO Auto-generated method stub
	}

	public void error(String s0) {
		// TODO Auto-generated method stub
	}

	public void displayGraph(String s0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void newGraph(ATerm t0) {
		// TODO Auto-generated method stub
		setModules((ATermList)t0);		
	}

	public void endStatus(ATerm t0) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				statusLineMgr.setMessage("");
			}
		});
	}

	public ATerm showQuestionDialog(String s0) {
		return null;
	}

	public ATerm showFileDialog(String s0, String s1, String s2) {
		return null;
	}

	public void recAckEvent(ATerm t0) {
	}

	public void recTerminate(ATerm t0) {
		// TODO Auto-generated method stub
		
	}

	public void postEvent(ATerm term) {
		bridge.postEvent(term);
	}

	private void setModules(ATermList importList) {
	  // TODO moduleManager.clearModules();
		final ATermList iL = importList;

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ATermList importList = iL;
				while (!importList.isEmpty()) {
					ATermList importPair = (ATermList) importList.getFirst();
					importList = importList.getNext();
					ATermAppl moduleTerm = (ATermAppl) importPair.getFirst();
					String name = moduleTerm.getName();
					ModuleExplorerPart.addModule(name);
				}
			}
		});
	}
	
	String formatString(String format, ATermList args) {
		int index;
		String prefix = "";
		String postfix = format;
		while ((index = postfix.indexOf("%")) != -1) {
			prefix += postfix.substring(0, index);
			switch (postfix.charAt(index + 1)) {
				case 't' :
				case 'd' :
					prefix += args.getFirst().toString();
					break;
				case 's' :
					prefix += ((ATermAppl) args.getFirst()).getName();
					break;
				default :
					prefix += "%" + postfix.charAt(index + 1);
			}
			postfix = postfix.substring(index + 2);
			args = args.getNext();
		}
		return prefix + postfix;
	}

	public void getContents(ATerm t0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void setCursorAtLocation(ATerm t0, int i1) {
		// TODO Auto-generated method stub
	}

	public void setFocus(ATerm t0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void editFile(ATerm t0, String s1, String s2) {
		// TODO Auto-generated method stub
	}

	public void messagef(String s0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void killEditor(ATerm t0) {
		// TODO Auto-generated method stub
	}

	public void displayMessage(ATerm t0, String s1) {
		// TODO Auto-generated method stub
	}

	public void updateList(String s0, String s1) {
		// TODO Auto-generated method stub
	}

	public void warningf(String s0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void editorToFront(ATerm t0) {
		// TODO Auto-generated method stub
	}

	public void setCursorAtFocus(ATerm t0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void message(String s0) {
		// TODO Auto-generated method stub
	}

	public void setActions(ATerm t0, ATerm t1) {
		// TODO Auto-generated method stub
	}

	public void warning(String s0) {
		// TODO Auto-generated method stub
	}

	public void buttonsFound(ATerm actionType, String moduleName, ATerm actions) {
		if (actionType.equals(ACTION_MENUBAR)) {
			//addMenu(buttonType, moduleName, (ATermList) buttons);
		} else if (actionType.equals(ACTION_TOOLBAR)) {
			//addToolBarActions((ATermList) buttons);
		} else {
			popupMenu.setMenu(actionType, moduleName, (ATermList)actions);
		}
	}

	public void clearFocus(ATerm t0) {
		// TODO Auto-generated method stub
	}

	public void rereadContents(ATerm t0) {
		// TODO Auto-generated method stub
	}
}
