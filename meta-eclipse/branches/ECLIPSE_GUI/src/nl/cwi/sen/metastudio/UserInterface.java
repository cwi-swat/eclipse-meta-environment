/*
 * Created on Jun 16, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
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

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class UserInterface implements UserEnvironmentTif, Runnable {
	private static IStatusLineManager statusLineMgr;
	
	public static ATermFactory factory;
	public static UserEnvironmentBridge bridge;
	private static Thread t;

	public UserInterface() {
	}
	
	public UserInterface(IStatusLineManager statusLineMgr) {
		this.statusLineMgr = statusLineMgr;
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

	public void initializeUi(String s0, String s1, String s2, String s3, String s4) {
		final String str0 = s0;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ModuleExplorerPart.addModule(str0);
			}
		});
	}

	public void buttonsFound(String s0, String s1, ATerm t2) {
		// TODO Auto-generated method stub
		
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

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserInterfaceTif#updateList(aterm.ATerm)
	 */
	public void updateList(ATerm t0) {
		// TODO Auto-generated method stub
		
	}

	public void endStatus(ATerm t0) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				statusLineMgr.setMessage("");
			}
		});
	}

	public ATerm deconsFilename(String filename, String extension) {
		if (filename.endsWith(extension)) {
		  filename = filename.substring(0, filename.length() - extension.length());
		}
		else {
		extension = "";
		}
    
		String path = filename;
		int lastIndex = path.lastIndexOf('/');

		if (lastIndex >= 0) {
		  path = path.substring(0, lastIndex+1);
		  filename = filename.substring(lastIndex+1, filename.length());
		}
      
		return factory.make("snd-value(file-name(<str>,<str>,<str>))", path, filename, extension);
	}

	public ATerm showQuestionDialog(String s0) {
		return null;
	}

	public ATerm showFileDialog(String s0, String s1, String s2) {
		return null;
	}

	public void recAckEvent(ATerm t0) {
		System.out.println("UI: Ack received");
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

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#getContents(aterm.ATerm, aterm.ATerm)
	 */
	public void getContents(ATerm t0, ATerm t1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#setCursorAtLocation(aterm.ATerm, int)
	 */
	public void setCursorAtLocation(ATerm t0, int i1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#setFocus(aterm.ATerm, aterm.ATerm)
	 */
	public void setFocus(ATerm t0, ATerm t1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#editFile(aterm.ATerm, java.lang.String, java.lang.String)
	 */
	public void editFile(ATerm t0, String s1, String s2) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#messagef(java.lang.String, aterm.ATerm)
	 */
	public void messagef(String s0, ATerm t1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#killEditor(aterm.ATerm)
	 */
	public void killEditor(ATerm t0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#displayMessage(aterm.ATerm, java.lang.String)
	 */
	public void displayMessage(ATerm t0, String s1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#updateList(java.lang.String, java.lang.String)
	 */
	public void updateList(String s0, String s1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#initializeUi(java.lang.String)
	 */
	public void initializeUi(String s0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#warningf(java.lang.String, aterm.ATerm)
	 */
	public void warningf(String s0, ATerm t1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#editorToFront(aterm.ATerm)
	 */
	public void editorToFront(ATerm t0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#setCursorAtFocus(aterm.ATerm, aterm.ATerm)
	 */
	public void setCursorAtFocus(ATerm t0, ATerm t1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#message(java.lang.String)
	 */
	public void message(String s0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#setActions(aterm.ATerm, aterm.ATerm)
	 */
	public void setActions(ATerm t0, ATerm t1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#warning(java.lang.String)
	 */
	public void warning(String s0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#buttonsFound(aterm.ATerm, java.lang.String, aterm.ATerm)
	 */
	public void buttonsFound(ATerm t0, String s1, ATerm t2) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#clearFocus(aterm.ATerm)
	 */
	public void clearFocus(ATerm t0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see nl.cwi.sen.metastudio.bridge.UserEnvironmentTif#rereadContents(aterm.ATerm)
	 */
	public void rereadContents(ATerm t0) {
		// TODO Auto-generated method stub
		
	}
}
