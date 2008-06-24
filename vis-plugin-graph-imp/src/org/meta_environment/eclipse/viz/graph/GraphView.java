package org.meta_environment.eclipse.viz.graph;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JRootPane;

import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import prefuse.Display;
import prefuse.data.Graph;

public class GraphView extends ViewPart {
    private java.awt.Container contentPane;
	private Panel fPanel;
	private JRootPane fRoot;
	private Frame fFrame;
	private Composite fComposite;

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
    }

    public void showRelation(IValue relation) {
        GraphBuilder graphBuilder = new GraphBuilder();
        Graph graph = graphBuilder.computeGraph(relation);
        GraphDisplayBuilder gvizBuilder = new GraphDisplayBuilder();
        final Display display = gvizBuilder.createDisplayFromGraph(graph);

        contentPane.removeAll();
        contentPane.add(display, BorderLayout.CENTER);
        contentPane.invalidate();
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub
    }
}
