package nl.cwi.sen.metastudio;

import java.io.IOException;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class PerspectiveFactory  implements IPerspectiveFactory, IPartListener {
	static IStatusLineManager statusLineMgr;
	IViewPart view;
	UserInterface ui;
	
	public PerspectiveFactory() throws IOException {
		super();

		// Hack to get the statuslinemanager, toolbarmanager
		IWorkbenchPart part = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.getActivePart();
		view = (IViewPart)part;
		statusLineMgr = view.getViewSite()
			.getActionBars()
			.getStatusLineManager();

		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener(this);

		addListeners();
	
		ui = new UserInterface(statusLineMgr);
		Thread t = new Thread(ui);
		t.start();
		//UserInterface ui = new UserInterface(statusLineMgr);
	}

	private void addListeners() {
		ISelectionChangedListener selectionListener = new SelectionListener();
		view.getViewSite().getSelectionProvider().addSelectionChangedListener(selectionListener);

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IResourceChangeListener listener = new ProjectListener();
		workspace.addResourceChangeListener(listener);
	}

	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);		
	}

	public void defineActions(IPageLayout layout) {
		// Add "new wizards"
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
		
		// Add "show views"
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		layout.addShowViewShortcut("nl.cwi.sen.metastudio.ModuleExplorer");
		layout.addShowViewShortcut("nl.cwi.sen.metastudio.ModuleInfoView");
		layout.addShowViewShortcut("nl.cwi.sen.metastudio.ModuleImportView");
		layout.addShowViewShortcut("nl.cwi.sen.metastudio.ModuleParentView");
	}
	
	public void defineLayout(IPageLayout layout) {
		// Editors are placed by default
		String editorArea = layout.getEditorArea();
		
		// Place navigator and outline to left of editor area.
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, (float)0.25, editorArea);
		left.addView(IPageLayout.ID_RES_NAV);
		left.addView(IPageLayout.ID_OUTLINE);
		
		// Place ModuleInfo, ModuleImport and ModuleParent to bottom of editor area.
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, (float)0.75, editorArea);
		bottom.addView("nl.cwi.sen.metastudio.views.statusview");
		bottom.addView("nl.cwi.sen.metastudio.ModuleInfoView");
		bottom.addView("nl.cwi.sen.metastudio.ModuleImportView");
		bottom.addView("nl.cwi.sen.metastudio.ModuleParentView");
		
		// Place ModuleExplorer to right of editor area.
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, (float)0.75, editorArea);
		right.addView("nl.cwi.sen.metastudio.ModuleExplorer");
	}

	public void partActivated(IWorkbenchPart part) {
	}

	public void partBroughtToTop(IWorkbenchPart part) {
	}

	public void partClosed(IWorkbenchPart part) {
		if (part instanceof IEditorPart) {
			System.out.println("Editor closed");
			ui.editorDisconnected((IEditorPart)part);
		}
	}

	public void partDeactivated(IWorkbenchPart part) {
	}

	public void partOpened(IWorkbenchPart part) {
	}
}