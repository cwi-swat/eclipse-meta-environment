package nl.cwi.sen.metastudio;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import metastudio.graph.Graph;
import metastudio.graph.MetaGraphFactory;
import nl.cwi.sen.metastudio.adt.editordata.EditorDataFactory;
import nl.cwi.sen.metastudio.adt.texteditor.ActionList;
import nl.cwi.sen.metastudio.adt.texteditor.TextEditorFactory;
import nl.cwi.sen.metastudio.bridge.UserEnvironmentBridge;
import nl.cwi.sen.metastudio.bridge.UserEnvironmentTif;
import nl.cwi.sen.metastudio.editor.MetaEditor;
import nl.cwi.sen.metastudio.moduleview.ModuleExplorerPart;
import nl.cwi.sen.metastudio.moduleview.ModuleInfoPart;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class UserInterface implements UserEnvironmentTif, Runnable {
	// TODO: move factories to run()
	private EditorDataFactory editorDataFactory;
	private TextEditorFactory textEditorFactory;
	private MetaGraphFactory metaGraphFactory;
	private static MetastudioConnection connection;

	private static Thread t;
	private static PopupMenu popupMenu;
	private static EditorRegistry editorRegistry;

	private Graph graph;

	private ATerm ACTION_MENUBAR;
	private ATerm ACTION_TOOLBAR;
	private ATerm ACTION_MODULE_POPUP;
	private ATerm ACTION_NEW_MODULE_POPUP;

	public UserInterface() {
	}

	public void run() {
		PureFactory factory = new PureFactory();
		createDataFactories(factory);

		UserEnvironmentBridge bridge = new UserEnvironmentBridge(factory, this);
		createConnection(factory, bridge);

		initializeBridge(bridge);
		t = new Thread(bridge);
		t.start();

		initialize();
	}

	private void initializeBridge(UserEnvironmentBridge bridge) {
		String[] args = new String[6];
		args[0] = "-TB_HOST_NAME";
		args[1] = "localhost";
		args[2] = "-TB_PORT";
		args[3] = "9000";
		args[4] = "-TB_TOOL_NAME";
		args[5] = "user-environment";

		try {
			bridge.init(args);
		} catch (UnknownHostException e) {
		}
		try {
			bridge.connect();
		} catch (IOException e) {
		}
	}

	private void createConnection(
		PureFactory factory,
		UserEnvironmentBridge bridge) {
		connection = new MetastudioConnection(bridge, factory);
		connection.setEditorDataFactory(editorDataFactory);
		connection.setTextEditorFactory(textEditorFactory);
		connection.setMetaGraphFactory(metaGraphFactory);
	}

	private void createDataFactories(PureFactory factory) {
		editorDataFactory = new EditorDataFactory(factory);
		textEditorFactory = new TextEditorFactory(factory);
		metaGraphFactory = new MetaGraphFactory(factory);
	}

	public static MetastudioConnection getConnection() {
		return connection;
	}

	public static EditorRegistry getEditorRegistry() {
		return editorRegistry;
	}

	private void initialize() {
		popupMenu = new PopupMenu();
		editorRegistry = new EditorRegistry();

		initializeATermPatterns();
	}

	private void initializeATermPatterns() {
		ACTION_MENUBAR = connection.getPureFactory().parse("studio-menubar");
		ACTION_TOOLBAR = connection.getPureFactory().parse("studio-toolbar");
		ACTION_MODULE_POPUP = connection.getPureFactory().parse("module-popup");
		ACTION_NEW_MODULE_POPUP =
			connection.getPureFactory().parse("new-module-popup");
	}

	public void initializeUi(String caption) {
	}

	public void buttonsFound(
		ATerm actionType,
		String moduleName,
		ATerm actions) {
		if (actionType.equals(ACTION_MENUBAR)) {
			//addMenu(buttonType, moduleName, (ATermList) buttons);
		} else if (actionType.equals(ACTION_TOOLBAR)) {
			//addToolBarActions((ATermList) buttons);
		} else {
			popupMenu.setMenu(actionType, moduleName, (ATermList) actions);
		}
	}

	public void addStatus(ATerm t0, String status) {
		final String _message = status;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				IStatusLineManager slm = PerspectiveFactory.getStatusLineManager();
				slm.setMessage(_message);
			}
		});
	}

	public void addStatusf(ATerm t0, String s1, ATerm t2) {
		final String _message = formatString(s1, (ATermList) t2);
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				PerspectiveFactory.getStatusLineManager().setMessage(_message);
			}
		});
	}

	public void endStatus(ATerm t0) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				PerspectiveFactory.getStatusLineManager().setMessage("");
			}
		});
	}

	public void error(String s0) {
		System.out.println("UserInterface.java: Error not implemented yet!");
	}

	public void errorf(String s0, ATerm t1) {
		System.out.println("UserInterface.java: Errorf not implemented yet!");
	}

	public void message(String s0) {
		System.out.println("UserInterface.java: Message not implemented yet!");
	}

	public void messagef(String s0, ATerm t1) {
		System.out.println("UserInterface.java: Messagef not implemented yet!");
	}

	public void displayMessage(ATerm t0, String message) {
		final String _message = message;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				PerspectiveFactory.getStatusLineManager().setMessage(_message);
			}
		});
	}

	public void warning(String s0) {
		System.out.println("UserInterface.java: Warning not implemented yet!");
	}

	public void warningf(String s0, ATerm t1) {
		System.out.println("UserInterface.java: Warningf not implemented yet!");
	}

	public void clearHistory() {
		System.out.println(
			"UserInterface.java: Clear history not implemented yet!");
	}

	private void setModules(ATermList importList) {
		// TODO moduleManager.clearModules();
		final ATermList _importList = importList;

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ATermList importList = _importList;
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

	public void deleteModules(ATerm mods) {
		final ATermList _modules = (ATermList) mods;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ATermList modules = _modules;
				for (; !modules.isEmpty(); modules = modules.getNext()) {
					String moduleName =
						((ATermAppl) modules.getFirst()).getAFun().getName();
					ModuleExplorerPart.removeModule(moduleName);
				}
			}
		});
	}

	public void moduleInfo(final String module, ATerm info) {
		ATermList pairs = (ATermList) info;
		List entries = new LinkedList();

		extractModuleInfoFromATerm(pairs, entries);

		final List finalEntries = entries;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ModuleInfoPart.setModuleInfo(module, finalEntries);
			}
		});
	}

	private void extractModuleInfoFromATerm(ATermList pairs, List entries) {
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
			String[] entry = { name, value };
			entries.add(entry);

			pairs = pairs.getNext();
		}
	}

	public void updateList(String s0, String s1) {
		System.out.println(
			"UserInterface.java: Update list not implemented yet!");
	}

	public void newGraph(ATerm importRelations) {
		setModules((ATermList) importRelations);
		Graph graph =
			Graph.fromImportList(
				connection.getMetaGraphFactory(),
				(ATermList) importRelations);
		connection.getBridge().postEvent(
			connection.getPureFactory().make(
				"layout-graph(<str>,<term>)",
				"import",
				graph.toTerm()));
	}

	public void displayGraph(String graphId, ATerm graphTerm) {
		graph = connection.getMetaGraphFactory().GraphFromTerm(graphTerm);
		graph.orderNodes();
		if (graphId == "parsetree") {
			connection.getBridge().postEvent(
				connection.getPureFactory().make(
					"layout-graph(<str>,<term>)",
					"parsetree",
					graph.toTerm()));
		}
	}

	public void graphLayouted(String graphId, ATerm graphTerm) {
		final String _graphId = graphId;
		final ATerm _graphTerm = graphTerm;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (_graphId == "import") {
					PerspectiveFactory.getGraphImportPart().setGraph(
						_graphTerm);
				} else {
					PerspectiveFactory.getGraphTreePart().setGraph(_graphTerm);
				}
			}
		});
	}

	public ATerm showQuestionDialog(final String question) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// Hack to get a shell for the messagebox
				Shell shell = ModuleExplorerPart.getShell();

				ATerm answer =
					connection.getPureFactory().make(
						"snd-value(answer(cancel))");
				if (shell != null) {
					MessageBox messageBox = createMessageBox(question);

					int choice = messageBox.open();
					if (choice == SWT.YES) {
						answer =
							connection.getPureFactory().make(
								"snd-value(answer(yes))");
					} else if (choice == SWT.NO) {
						answer =
							connection.getPureFactory().make(
								"snd-value(answer(no))");
					}
				}
				try {
					connection.getBridge().sendTerm(answer);
				} catch (IOException e) {
				}
			}

			private MessageBox createMessageBox(final String question) {
				MessageBox messageBox =
					new MessageBox(
						PlatformUI
							.getWorkbench()
							.getActiveWorkbenchWindow()
							.getShell(),
						SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
				messageBox.setMessage(question);
				return messageBox;
			}
		});

		// keep compiler happy...
		return null;
	}

	// Not used in Eclipse GUI
	public ATerm showFileDialog(String s0, String s1, String s2) {
		return null;
	}

	public void editFile(
		final ATerm editorId,
		String editor,
		final String fileName) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI
						.getWorkbench()
						.getActiveWorkbenchWindow()
						.getActivePage();

				IPath path = new Path(fileName);
				IFile file =
					MetastudioPlugin
						.getWorkspace()
						.getRoot()
						.getFileForLocation(
						path);

				// file not found: try again after adding absolute path of current project
				if (file == null) {
					String fullPath =
						MetastudioPlugin
							.getWorkspace()
							.getRoot()
							.getLocation()
							.toString();
					path =
						new Path(
							"/ufs/kooiker/runtime-workspace/Pico/" + fileName);
					file =
						MetastudioPlugin
							.getWorkspace()
							.getRoot()
							.getFileForLocation(
							path);
				}

				IEditorPart part = null;
				try {
					part = page.openEditor(file);
				} catch (Exception e) {
				}

				if (editorRegistry.getEditorPartByeditorId(editorId) == null
					&& part != null) {
					editorRegistry.addEditor(editorId, fileName, part);
				}
			}
		});
	}

	public void editorDisconnected(IEditorPart part) {
		ATerm editorId = editorRegistry.geteditorIdByEditorPart(part);
		connection.getBridge().postEvent(
			connection.getPureFactory().make(
				"editor-disconnected(<term>)",
				editorId));

		editorRegistry.removeEditor((IEditorPart) part);
	}

	public void killEditor(ATerm editorId) {
		System.out.println(
			"UserInterface.java: Kill editor not implemented yet!");
	}

	public void setActions(final ATerm editorId, final ATerm actionList) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MetaEditor part =
					(MetaEditor) editorRegistry.getEditorPartByeditorId(
						editorId);
				ActionList _actionList =
					textEditorFactory.ActionListFromTerm(actionList);
				part.setActions(editorId, _actionList);
			}
		});
	}

	public void editorToFront(final ATerm editorId) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI
						.getWorkbench()
						.getActiveWorkbenchWindow()
						.getActivePage();

				String fileName =
					editorRegistry.getFileNameByeditorId(editorId);
				IPath path = new Path(fileName);
				IFile file =
					MetastudioPlugin
						.getWorkspace()
						.getRoot()
						.getFileForLocation(
						path);

				try {
					page.openEditor(file);
				} catch (Exception e) {
				}
			}
		});
	}

	public void getContents(final ATerm editorId, final ATerm focus) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MetaEditor part =
					(MetaEditor) editorRegistry.getEditorPartByeditorId(
						editorId);
				part.getContents(
					editorId,
					editorDataFactory.FocusFromTerm(focus));
			}
		});
	}

	public void rereadContents(ATerm t0) {
		System.out.println(
			"UserInterface.java: Reread contents not implemented yet!");
	}

	public void setCursorAtLocation(final ATerm editorId, final int location) {
		System.out.println(
			"UserInterface.java: Set cursor @ location not implemented yet!");
	}

	public void setCursorAtFocus(final ATerm editorId, final ATerm focus) {
		System.out.println(
			"UserInterface.java: Set cursor @ focus not implemented yet!");
	}

	public void setFocus(final ATerm editorId, final ATerm focus) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MetaEditor part =
					(MetaEditor) editorRegistry.getEditorPartByeditorId(
						editorId);
				part.setFocus(editorDataFactory.FocusFromTerm(focus));
			}
		});
	}

	public void clearFocus(final ATerm editorId) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MetaEditor part =
					(MetaEditor) editorRegistry.getEditorPartByeditorId(
						editorId);
				part.clearFocus();
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

	public void recAckEvent(ATerm t0) {
	}

	public void recTerminate(ATerm t0) {
	}
}
