package nl.cwi.sen.metastudio.moduleview;

import java.util.Iterator;
import java.util.List;

import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.UserInterface;
import nl.cwi.sen.metastudio.model.ModuleInfo;
import nl.cwi.sen.metastudio.model.ModuleInfoElement;
import nl.cwi.sen.metastudio.model.Module;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class ModuleInfoPart extends ViewPart implements ISelectionListener {
	private Table table;
	private TableViewer tableViewer;
	static ModuleInfo info;

	public void dispose() {
		getViewSite().getPage().removeSelectionListener(this);
	}

	public void createPartControl(Composite parent) {
		createTable(parent);
		tableViewer = new TableViewer(table);
		
		createColumns();
		setProviders();
		getViewSite().getPage().addSelectionListener(this);

		info = new ModuleInfo();

		tableViewer.setInput(info);
	}

	private void createTable(Composite parent) {
		table = new Table(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
	}

	private void createColumns() {
		TableLayout tableLayout = new TableLayout();
		table.setLayout(tableLayout);
		table.setHeaderVisible(true);
	
		tableLayout.addColumnData(new ColumnWeightData(1, 50, true));
		tableLayout.addColumnData(new ColumnWeightData(1, 50, true));

		new TableColumn(table, SWT.NONE).setText("Property");
		new TableColumn(table, SWT.NONE).setText("Value");
	}
	
	private void setProviders() {
		tableViewer.setContentProvider(new ModuleInfoContentProvider());
		tableViewer.setLabelProvider(new ModuleInfoLabelProvider());
	}

	public void setFocus() {
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		MetastudioConnection factory = new MetastudioConnection();
		
		if (part instanceof ModuleExplorerPart) {
			if (selection instanceof IStructuredSelection) {
				Object first = ((IStructuredSelection)selection).getFirstElement();
				if (first instanceof Module) {
					info.clear();
					final String moduleName = ((Module)first).getModulePath();
					UserInterface ui = new UserInterface();
					ui.postEvent(factory.getFactory().make("get-module-info(<str>)", moduleName));
				}
			}
		}
	}
	
	public static void setModuleInfo(String moduleName, List info) {
//		if (module != null && moduleName.equals(module.getName())) {
			updateInfo(info);
//		}
	}

	private static void updateInfo(List infoList) {
		info.clear();

		Iterator iter = infoList.iterator();
		while (iter.hasNext()) {
			String[] pair = (String[])iter.next();
			ModuleInfoElement moduleInfoElement = new ModuleInfoElement(pair[0], pair[1]);
			info.add(moduleInfoElement);			
		}
	}
}
