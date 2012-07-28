package org.meta_environment.eclipse.visualization;

import nl.cwi.sen1.relationstores.Factory;
import nl.cwi.sen1.relationstores.types.RStore;
import nl.cwi.sen1.relationstores.types.RTuple;
import nl.cwi.sen1.relationstores.types.RTupleRtuples;

import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.ui.graph.Editor;
import org.eclipse.imp.pdb.ui.graph.ValueEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.meta_environment.eclipse.Activator;
import org.meta_environment.eclipse.facts.FactsTool;


import toolbus.adapter.eclipse.EclipseTool;
import aterm.ATerm;

public class VisualizationTool extends EclipseTool {
	private static final String TOOL_NAME = "visualization-tool";
	
	private static Factory factory;
	
	private static class InstanceKeeper {
		private static VisualizationTool sInstance = new VisualizationTool();
		static {
			sInstance.connect();
			factory = Factory.getInstance(getFactory());
		}
	}

	public static VisualizationTool getInstance() {
		return InstanceKeeper.sInstance;
	}

	private VisualizationTool() {
		super(TOOL_NAME);
	}
	
	public void viewGraph(final String path, final ATerm aterm) {
		FactsTool factsTool = FactsTool.getInstance(); 
		RStore store = factory.RStoreFromTerm(aterm);
		
		System.out.printf("path: %s, rstore: %s\n", path, store.toString());
		
		RTupleRtuples tuples = store.getRtuples();

		if (!tuples.isEmpty()) {
			RTuple tuple = tuples.getHead();
			IValue value = factsTool.convertValue(tuple.getValue(), tuple.getRtype());

			try {
				IWorkbench wb = PlatformUI.getWorkbench();
				IWorkbenchWindow win = wb.getActiveWorkbenchWindow();

				if (win != null) {
					IWorkbenchPage page = win.getActivePage();

					if (page != null) {

						page.openEditor(new ValueEditorInput(value), Editor.EditorId);

					}
				}
			} catch (PartInitException e) {
				Activator.getInstance().logException("Can not view graph", e);
			}
		}		
	}	
}
