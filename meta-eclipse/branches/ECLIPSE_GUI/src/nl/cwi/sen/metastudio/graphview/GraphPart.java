package nl.cwi.sen.metastudio.graphview;

import metastudio.graph.Graph;
import metastudio.graph.Node;
import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.ui.part.ViewPart;

import aterm.ATerm;

public class GraphPart extends ViewPart {
	private Graph graph;
	private Canvas canvas;
	private GraphLibrary graphLibrary;
	private Image image;

	private int ix = 0, iy = 0;

	private Color background;

	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);

		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				doPaint(event);
				resizeScrollBars();
			}
		});

		addListeners();

		background = new Color(null, 255, 255, 255);
		canvas.setBackground(background);
	}

	private void doPaint(PaintEvent event) {
		if (graph != null) {
			graphLibrary.paintEdges(graph);
			graphLibrary.paintNodes(graph);

			GC gc = event.gc;
			gc.drawImage(image, ix, iy);
		}
	}

	private void addListeners() {
		addHorizontalListener();
		addVerticalListener();
		addMouseListener();
		addMouseMoveListener();
	}

	private void addHorizontalListener() {
		ScrollBar horizontal = canvas.getHorizontalBar();
		horizontal.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (graph == null) {
					return;
				}

				ScrollBar scrollBar = (ScrollBar) event.widget;
				Rectangle canvasBounds = canvas.getClientArea();
				int width = graph.getBoundingBox().getSecond().getX(); //xscale
				int height = graph.getBoundingBox().getSecond().getY();
				if (width > canvasBounds.width) {
					// Only scroll if the image is bigger than the canvas.
					int x = -scrollBar.getSelection();
					if (x + width < canvasBounds.width) {
						// Don't scroll past the end of the image.
						x = canvasBounds.width - width;
					}
					canvas.scroll(x, iy, ix, iy, width, height, false);
					ix = x;
				}
			}
		});
	}

	private void addVerticalListener() {
		ScrollBar vertical = canvas.getVerticalBar();
		vertical.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (graph == null) {
					return;
				}

				ScrollBar scrollBar = (ScrollBar) event.widget;
				Rectangle canvasBounds = canvas.getClientArea();
				int width = graph.getBoundingBox().getSecond().getX();
				int height = graph.getBoundingBox().getSecond().getY();
				if (height > canvasBounds.height) {
					// Only scroll if the image is bigger than the canvas.
					int y = -scrollBar.getSelection();
					if (y + height < canvasBounds.height) {
						// Don't scroll past the end of the image.
						y = canvasBounds.height - height;
					}
					canvas.scroll(ix, y, ix, iy, width, height, false);
					iy = y;
				}
			}
		});
	}

	private void addMouseListener() {
		canvas.addMouseListener(new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) {
			}

			public void mouseDown(MouseEvent e) {
			}

			public void mouseUp(MouseEvent e) {
				if (graphLibrary != null) {
					Node node =
						graphLibrary.getNodeAt(graph, e.x - ix, e.y - iy);
					if (graphLibrary.nodeSelected(node) == true) {
						canvas.redraw();
					}
				}
			}
		});
	}

	private void addMouseMoveListener() {
		canvas.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {
				if (graphLibrary != null) {
					Node node =
						graphLibrary.getNodeAt(graph, e.x - ix, e.y - iy);
					if (graphLibrary.nodeHighlighted(node) == true) {
						canvas.redraw();
					}
				}
			}
		});
	}

	private void resizeScrollBars() {
		ScrollBar horizontal = canvas.getHorizontalBar();
		ScrollBar vertical = canvas.getVerticalBar();
		if (graph != null) {
			// Set the max and thumb for the image canvas scroll bars.
			Rectangle canvasBounds = canvas.getClientArea();
			int width = graph.getBoundingBox().getSecond().getX(); // * xscale
			if (width > canvasBounds.width) {
				// The image is wider than the canvas.
				horizontal.setVisible(true);
				horizontal.setMaximum(width);
				horizontal.setThumb(canvasBounds.width);
				horizontal.setPageIncrement(canvasBounds.width);
			} else {
				// The canvas is wider than the image.
				horizontal.setVisible(false);
				if (ix != 0) {
					// Make sure the image is completely visible.
					ix = 0;
					canvas.redraw();
				}
			}
			int height = graph.getBoundingBox().getSecond().getY(); // * yscale
			if (height > canvasBounds.height) {
				// The image is taller than the canvas.
				vertical.setVisible(true);
				vertical.setMaximum(height);
				vertical.setThumb(canvasBounds.height);
				vertical.setPageIncrement(canvasBounds.height);
			} else {
				// The canvas is taller than the image.
				vertical.setVisible(false);
				if (iy != 0) {
					// Make sure the image is completely visible.
					iy = 0;
					canvas.redraw();
				}
			}
		} else {
			horizontal.setVisible(false);
			vertical.setVisible(false);
		}
	}

	public void setGraph(ATerm graphTerm) {
		MetastudioConnection connection = UserInterface.getConnection();
		graph = connection.getMetaGraphFactory().GraphFromTerm(graphTerm);

		int width = graph.getBoundingBox().getSecond().getX();
		int height = graph.getBoundingBox().getSecond().getY();
		image = new Image(null, width, height);
		GC bufferedGC = new GC(image);
		graphLibrary = new GraphLibrary(bufferedGC);

		canvas.redraw();
	}

	public void setFocus() {
	}
}
