package nl.cwi.sen.metastudio.graphview;

import metastudio.graph.Edge;
import metastudio.graph.EdgeList;
import metastudio.graph.Graph;
import metastudio.graph.Node;
import metastudio.graph.NodeList;
import metastudio.graph.Point;
import metastudio.graph.Polygon;
import metastudio.graph.Shape;
import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.UserInterface;

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

import aterm.ATerm;

public class GraphTreePart extends ViewPart {
	private static Graph graph;
	private static Canvas canvas;

	private Color nodeBG;
	private Color nodeFG;
	private Color nodeBorder;

	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.NONE);
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

		setupColors();
	}

	private void setupColors() {
		nodeBG = new Color(null, 200, 200, 200);
		nodeFG = new Color(null, 0, 0, 0);
		nodeBorder = new Color(null, 0, 0, 0);

		//		nodeBGHovered = Preferences.getColor(PREF_NODE_HOVERED_BG);
		//		nodeFGHovered = Preferences.getColor(PREF_NODE_HOVERED_FG);
		//		nodeBorderHovered = Preferences.getColor(PREF_NODE_HOVERED_BORDER);
		//
		//		nodeBGSelected = Preferences.getColor(PREF_NODE_SELECTED_BG);
		//		nodeFGSelected = Preferences.getColor(PREF_NODE_SELECTED_FG);
		//		nodeBorderSelected = Preferences.getColor(PREF_NODE_SELECTED_BORDER);
	}

	private void doPaint(PaintEvent event) {
		Color foreground = new Color(null, 0, 0, 0);
		Color background = new Color(null, 0, 0, 0);
		GC gc = event.gc;
		gc.setForeground(foreground);
		gc.setBackground(background);
		Rectangle clientArea = gc.getClipping();
//		MetastudioConnection connection = UserInterface.getConnection();
//		try {
//			ATerm graphTerm =
//				connection.getPureFactory().readFromFile(
//					"/ufs/kooiker/graph.trm");
//			graph = connection.getMetaGraphFactory().GraphFromTerm(graphTerm);
//		} catch (Exception e) {
//		}
		if (graph != null) {
			paintEdges(gc);
			paintNodes(gc);
		}
	}

	private void paintEdges(GC gc) {
		EdgeList edges = graph.getEdges();

		while (!edges.isEmpty()) {
			Edge edge = edges.getHead();
			edges = edges.getTail();

			paintEdge(gc, edge);
		}
	}

	private void paintEdge(GC gc, Edge edge) {
		if (!edge.isPositioned()) {
			return;
		}
		Polygon poly = edge.getPolygon();

		Point to = poly.getHead();
		Point from = to;

		// TODO: Undo brute initialize of arraylength 16
		int[][] points = new int[16][2];
		int n = 0;

		while (poly.hasTail() && !poly.getTail().isEmpty()) {
			from = to;
			points[n][0] = from.getX();
			points[n][1] = from.getY();
			n++;
			poly = poly.getTail();
			to = poly.getHead();
		}

		points[n][0] = to.getX();
		points[n][1] = to.getY();

		bspline(gc, points, n);

		//				Graphics2D g2d = (Graphics2D) g;
		//				if (edge.connectedTo(hoveredNode)) {
		//					g2d.setColor(nodeBorderHovered);
		//				} else if (edge.connectedTo(selectedNode)) {
		//					g2d.setColor(nodeBorderSelected);
		//				} else {
		//					g2d.setColor(nodeBorder);
		//				}
		//
		//				g2d.draw(gp);
		arrowHead(gc, from, to);
	}

	private void arrowHead(GC gc, Point from, Point to) {
		int x1 = from.getX();
		int y1 = from.getY();
		int x2 = to.getX();
		int y2 = to.getY();

		// calculate points for arrowhead
		double angle = Math.atan2(y2 - y1, x2 - x1) + Math.PI;
		double ARROWHEAD_SHARPNESS = 15;
		double theta = Math.toRadians(ARROWHEAD_SHARPNESS);
		double ARROWHEAD_LENGTH = 15;
		double size = ARROWHEAD_LENGTH;

		int x3 = (int) (x2 + Math.cos(angle - theta) * size);
		int y3 = (int) (y2 + Math.sin(angle - theta) * size);

		int x4 = (int) (x2 + Math.cos(angle + theta) * size);
		int y4 = (int) (y2 + Math.sin(angle + theta) * size);

		int[] xys = new int[] { x2, y2, x3, y3, x4, y4, x2, y2 };

		gc.fillPolygon(xys);
		gc.drawPolygon(xys);
	}

	private void paintNodes(GC gc) {
		NodeList nodes = graph.getNodes();
		while (!nodes.isEmpty()) {
			Node node = nodes.getHead();
			nodes = nodes.getTail();

			paintNode(gc, node);
		}
	}

	private void paintNode(GC gc, Node node) {
		if (!node.isPositioned()) {
			return;
		}

		int x = node.getX();
		int y = node.getY();
		int w = node.getWidth();
		int h = node.getHeight();

		x -= w / 2;
		y -= h / 2;

		//		if (!g.hitClip(x, y, w, h)) {
		//			return;
		//		}
		//
		Color node_bg, node_fg, node_border;

		//		if (selectedNode != null && selectedNode.getId().equals(node.getId())) {
		//			node_bg = nodeBGSelected;
		//			node_fg = nodeFGSelected;
		//			node_border = nodeBorderSelected;
		//		} else if (hoveredNode != null && hoveredNode.getId().equals(node.getId())) {
		//			node_bg = nodeBGHovered;
		//			node_fg = nodeFGHovered;
		//			node_border = nodeBorderHovered;
		//		} else {
		node_bg = nodeBG;
		node_fg = nodeFG;
		node_border = nodeBorder;
		//		}

		Shape shape = Graph.getNodeShape(node);

		if (shape.isBox()) {
			paintBox(gc, x, y, w, h, node_bg, node_border);
		} else if (shape.isEllipse()) {
			paintEllipse(gc, x, y, w, h, node_bg, node_border);
		} else if (shape.isDiamond()) {
			paintDiamond(gc, x, y, w, h, node_bg, node_border);
		} else {
			// default case, we draw a rectangle
			paintBox(gc, x, y, w, h, node_bg, node_border);
		}

		String name = node.getLabel();
		//		int tw = metrics.stringWidth(name);
		//		int th = metrics.getAscent();

		gc.setForeground(node_fg);
		gc.drawString(name, x, y);
		//		gc.drawString(name, x + (w - tw) / 2, y + (h + th) / 2 - 1);
	}

	private void paintDiamond(
		GC gc,
		int x,
		int y,
		int w,
		int h,
		Color node_bg,
		Color node_border) {
		gc.setBackground(node_bg);
		int[] xys =
			new int[] {
				x,
				y + h / 2,
				x + w / 2,
				y,
				x + w,
				y + h / 2,
				x + w / 2,
				y + h };

		gc.fillPolygon(xys);
		gc.setForeground(node_border);
		gc.drawPolygon(xys);
	}

	private void paintEllipse(
		GC gc,
		int x,
		int y,
		int w,
		int h,
		Color node_bg,
		Color node_border) {
		gc.setBackground(node_bg);
		gc.fillOval(x, y, w, h);
		gc.setForeground(node_border);
		gc.drawOval(x, y, w, h);
	}

	private void paintBox(
		GC gc,
		int x,
		int y,
		int w,
		int h,
		Color node_bg,
		Color node_border) {
		gc.setBackground(node_bg);
		gc.fillRectangle(x, y, w, h);
		gc.setForeground(node_border);
		gc.drawRectangle(x, y, w, h);
	}

	private void bspline(GC gc, int[][] P, int n) {
		float m = 500;
		float xA, yA, xB, yB, xC, yC, xD, yD;
		float a0, a1, a2, a3, b0, b1, b2, b3;
		float x = 0, y = 0, x0, y0;
		boolean first = true;

		if (n < 5) {
			gc.drawLine(P[0][0], P[0][1], P[n][0], P[n][1]);
		} else {
			for (int i = 1; i < n - 2; i++) {
				xA = P[i - 1][0];
				xB = P[i][0];
				xC = P[i + 1][0];
				xD = P[i + 2][0];
				yA = P[i - 1][1];
				yB = P[i][1];
				yC = P[i + 1][1];
				yD = P[i + 2][1];
				a3 = (-xA + 3 * (xB - xC) + xD) / 6;
				b3 = (-yA + 3 * (yB - yC) + yD) / 6;
				a2 = (xA - 2 * xB + xC) / 2;
				b2 = (yA - 2 * yB + yC) / 2;
				a1 = (xC - xA) / 2;
				b1 = (yC - yA) / 2;
				a0 = (xA + 4 * xB + xC) / 6;
				b0 = (yA + 4 * yB + yC) / 6;
				for (int j = 0; j <= m; j++) {
					x0 = x;
					y0 = y;
					float t = (float) j / (float) m;
					x = (int) (((a3 * t + a2) * t + a1) * t + a0);
					y = (int) (((b3 * t + b2) * t + b1) * t + b0);
					if (first) {
						first = false;
						x0 = P[0][0];
						y0 = P[0][1];
						gc.drawLine((int) x0, (int) y0, (int) x, (int) y);
						x0 = x;
						y0 = y;
					} else {
						gc.drawLine((int) x0, (int) y0, (int) x, (int) y);
					}
				}
			}
			gc.drawLine((int) x, (int) y, P[n][0], P[n][1]);
		}
	}

	static public void setGraph(ATerm graphTerm) {
		MetastudioConnection connection = UserInterface.getConnection();
		graph = connection.getMetaGraphFactory().GraphFromTerm(graphTerm);
		canvas.redraw();
	}

	public void setFocus() {
	}
}
