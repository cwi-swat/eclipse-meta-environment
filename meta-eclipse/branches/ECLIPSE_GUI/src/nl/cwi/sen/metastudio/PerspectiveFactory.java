package nl.cwi.sen.metastudio;

import java.io.IOException;

import nl.cwi.sen.metastudio.graphview.GraphPart;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class PerspectiveFactory implements IPerspectiveFactory, IPartListener {
	UserInterface ui;

	private static IWorkbenchPage _page;
	private static IViewPart _resourceNavigator;

	public PerspectiveFactory() throws IOException {
		super();

		PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.addPartListener(this);
		addListeners();

		ui = new UserInterface();
		Thread t = new Thread(ui);
		t.start();
	}

	private void addListeners() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
	}

	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);

		definePartHandlers();
	}

	private void definePartHandlers() {
		_page =
			PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage();

		_resourceNavigator = _page.findView(IPageLayout.ID_RES_NAV);
	}

	private void defineActions(IPageLayout layout) {
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
		layout.addShowViewShortcut("nl.cwi.sen.metastudio.GraphImportView");
		layout.addShowViewShortcut("nl.cwi.sen.metastudio.GraphTreeView");
	}

	private void defineLayout(IPageLayout layout) {
		// Editors are placed by default
		String editorArea = layout.getEditorArea();

		// Place navigator and outline to left of editor area.
		IFolderLayout left =
			layout.createFolder(
				"left",
				IPageLayout.LEFT,
				(float) 0.25,
				editorArea);
		left.addView(IPageLayout.ID_RES_NAV);
		left.addView(IPageLayout.ID_OUTLINE);

		// Place ModuleInfo, ModuleImport and ModuleParent to bottom of editor area.
		IFolderLayout bottom =
			layout.createFolder(
				"bottom",
				IPageLayout.BOTTOM,
				(float) 0.75,
				editorArea);
		bottom.addView("nl.cwi.sen.metastudio.views.statusview");
		bottom.addView("nl.cwi.sen.metastudio.ModuleInfoView");
		bottom.addView("nl.cwi.sen.metastudio.ModuleImportView");
		bottom.addView("nl.cwi.sen.metastudio.ModuleParentView");

		// Place ModuleExplorer to right of editor area.
		IFolderLayout right =
			layout.createFolder(
				"right",
				IPageLayout.RIGHT,
				(float) 0.75,
				editorArea);
		right.addView("nl.cwi.sen.metastudio.ModuleExplorer");

		IFolderLayout top =
			layout.createFolder("top", IPageLayout.TOP, (float) 1, editorArea);
		top.addView("nl.cwi.sen.metastudio.GraphImportView");
		top.addView("nl.cwi.sen.metastudio.GraphTreeView");
	}

	public static IViewPart getResourceNavigatorPart() {
		return _resourceNavigator;
	}

	public static GraphPart getGraphImportPart() {
		IViewPart part =
			_page.findView("nl.cwi.sen.metastudio.GraphImportView");
		return (GraphPart) part;
	}

	public static GraphPart getGraphTreePart() {
		IViewPart part = _page.findView("nl.cwi.sen.metastudio.GraphTreeView");
		return (GraphPart) part;
	}

	public static IStatusLineManager getStatusLineManager() {
		IStatusLineManager statusLineManager;
		IWorkbenchPart part =
			PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.getActivePart();
		if (part instanceof IEditorPart) {
			IEditorPart editorPart = (IEditorPart) part;
			statusLineManager =
				editorPart
					.getEditorSite()
					.getActionBars()
					.getStatusLineManager();
		} else {
			IViewPart view = (IViewPart) part;
			statusLineManager =
				view.getViewSite().getActionBars().getStatusLineManager();
		}
		return statusLineManager;
	}

	public void partActivated(IWorkbenchPart part) {
	}

	public void partBroughtToTop(IWorkbenchPart part) {
	}

	public void partClosed(IWorkbenchPart part) {
		if (part instanceof IEditorPart) {
			ui.editorDisconnected((IEditorPart) part);
		}
	}

	public void partDeactivated(IWorkbenchPart part) {
	}

	public void partOpened(IWorkbenchPart part) {
	}
}