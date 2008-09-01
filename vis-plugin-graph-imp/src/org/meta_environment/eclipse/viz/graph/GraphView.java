package org.meta_environment.eclipse.viz.graph;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JRootPane;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import prefuse.data.Graph;

public class GraphView extends EditorPart {
    private java.awt.Container contentPane;
	private Panel fPanel;
	private JRootPane fRoot;
	private Frame fFrame;
	private Composite fComposite;
	private IValue relation;

    public GraphView() {
        super();
    }

    @Override
    public void createPartControl(Composite parent) {
        fComposite = new Composite(parent, SWT.NO_BACKGROUND
		                | SWT.EMBEDDED);
		fFrame = SWT_AWT.new_Frame(fComposite);
		fPanel = new Panel(new BorderLayout()) {
		                    private static final long serialVersionUID = 1L;
		        
		                    public void update(java.awt.Graphics g) {
		                        paint(g);
		                    }
		                };
		fFrame.add(fPanel);
        fRoot = new JRootPane();
		fPanel.add(fRoot);
        contentPane = fRoot.getContentPane();
        contentPane.setLayout(new BorderLayout());

        if (relation != null) {
			showRelation(relation);
		}
    }

    public void showRelation(IValue relation) {
        GraphBuilder graphBuilder = new GraphBuilder();
        Graph graph = graphBuilder.computeGraph(relation);
        Component tempDisplay;
        if (graph.getNodeCount() != 0) {
            GraphDisplayBuilder gvizBuilder = new GraphDisplayBuilder();
            tempDisplay = gvizBuilder.createDisplayFromGraph(graph);
        }
        else {
        	tempDisplay = new JLabel("This fact is empty.");
        }
        final Component display = tempDisplay;

        contentPane.removeAll();
        contentPane.add(display, BorderLayout.CENTER);
        contentPane.invalidate();
    }

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
        if (input instanceof GraphInput) {
        	GraphInput graphInput = ((GraphInput)input); 
        	relation = graphInput.getValue();
        	setPartName(graphInput.getName());
        	setContentDescription(relation.getBaseType().toString());
        }
	}

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub
    }

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
}
