package nl.cwi.sen.metastudio.moduleview;

import nl.cwi.sen.metastudio.model.Directory;
import nl.cwi.sen.metastudio.model.Module;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

public class ModuleExplorerPart extends ViewPart  implements IMenuListener {
	private TreeViewer fViewer;
	private Menu fContextMenu;
	private ModuleExplorerContentProvider fContentProvider;
	private ModuleExplorerLabelProvider fLabelProvider;
	
	static private Directory root;
	
	public ModuleExplorerPart() {
	}

	public void createPartControl(Composite parent)  {
		fViewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		fViewer.setUseHashlookup(true);
		//fViewer.setComparer();
		
		setProviders();
		
		addListeners();
		//MetastudioPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this);
		
		//createMenus() for following lines
		//MenuManager menuMgr= new MenuManager("#PopupMenu"); //$NON-NLS-1$
		//menuMgr.setRemoveAllWhenShown(true);
		//menuMgr.addMenuListener(this);
		//fContextMenu= menuMgr.createContextMenu(fViewer.getTree());
		//fViewer.getTree().setMenu(fContextMenu);
		
		// Register viewer with site. This must be done before making the actions.
		IWorkbenchPartSite site= getSite();
		//site.registerContextMenu(menuMgr, fViewer);
		site.setSelectionProvider(fViewer);
		//site.getPage().addPartListener(fPartListener);
		//createActions();

		fViewer.setInput(getInput());
	}
	
	private Directory getInput() {
		if (!(root instanceof Directory)) {
			root = new Directory();
		}		
		return root;
	}
	
	private void setProviders() {
		fContentProvider = new ModuleExplorerContentProvider();
		fViewer.setContentProvider(fContentProvider);
		
		fLabelProvider = new ModuleExplorerLabelProvider();
		fViewer.setLabelProvider(fLabelProvider);
	}
	
	private void addListeners() {
		ModuleExplorerMouseListener mouseListener = new ModuleExplorerMouseListener();
		fViewer.getControl().addMouseListener(mouseListener);
	}
	
	public void setFocus()  {
	}

	public void menuAboutToShow(IMenuManager manager) {
	}

	static public void addModule(String moduleName) {
		Directory dir, dirChild;
		int i = 0;
		
		if (!(root instanceof Directory)) {
			root = new Directory();
		}
		
		dir = root;
		dirChild = root;
		String[] splitModuleName = moduleName.split("/");
		for (i = 0; i < splitModuleName.length - 1; i++) {
			dirChild = dir.getChild(splitModuleName[i]);
			if (dirChild == null) {
				dirChild = new Directory(splitModuleName[i]);
				dir.add(dirChild);
				dir = dirChild;
			}
		}
		if (dirChild.getChildModule(splitModuleName[i]) == null) {
			dirChild.add(new Module(moduleName, splitModuleName[i]));
		}
	}
}
