package nl.cwi.sen.metastudio.graphview;

import metastudio.graph.AttributeList;
import metastudio.graph.EdgeList;
import metastudio.graph.Graph;
import metastudio.graph.MetaGraphFactory;
import metastudio.graph.NodeList;
import nl.cwi.sen.metastudio.MetastudioConnection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class GraphImportPart extends ViewPart {
	private Graph graph;
	
	public void createPartControl(Composite parent) {
		Canvas canvas = new Canvas(parent, SWT.NONE);
		//				SWT.CENTER
		//					| SWT.BORDER
		//					| SWT.V_SCROLL
		//					| SWT.H_SCROLL
		//					| SWT.NO_REDRAW_RESIZE
		//					| SWT.NO_BACKGROUND);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		canvas.setLayoutData(gridData);

		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				doPaint(event);
			}
		});

		MetastudioConnection connection = new MetastudioConnection();
		MetaGraphFactory metaGraphFactory = connection.getMetaGraphFactory();

		NodeList nodes = metaGraphFactory.makeNodeList_Empty();
		EdgeList edges = metaGraphFactory.makeEdgeList_Empty();
		AttributeList attrs = metaGraphFactory.makeAttributeList_Empty();

		graph = metaGraphFactory.makeGraph_Default(nodes, edges, attrs);
	}

	private void doPaint(PaintEvent event) {
		Color red = new Color(null, 255, 0, 0);
		Color grey = new Color(null, 0, 128, 128);
		GC gc = event.gc;
		gc.setForeground(red);
		gc.setBackground(grey);
		Rectangle clientArea = gc.getClipping();

		gc.drawLine(0, 0, clientArea.width, clientArea.height);
		gc.drawLine(0, 50, 50, 0);
	}	
	
	public void setFocus() {
	}
}
