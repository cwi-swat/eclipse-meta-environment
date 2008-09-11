package org.meta_environment.eclipse.viz.prefusedot;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import prefuse.render.AbstractShapeRenderer;
import prefuse.visual.VisualItem;

public class DotNodeRenderer extends AbstractShapeRenderer {
	private Rectangle2D box = new Rectangle2D.Double();

	private Ellipse2D ellipse = new Ellipse2D.Double();

	private Point2D intersection[] = new Point2D[2];

	public DotNodeRenderer() {
		super();
		intersection[0] = new Point2D.Float();
		intersection[1] = new Point2D.Float();
		System.err.println("Init DotNodeRenderer");
	}

	public Shape getRawShape(VisualItem item) {
		Rectangle2D bounds = ((Rectangle2D) item.get(VisualItem.BOUNDS))
				.getFrame();
		double x = item.getX();
		double y = item.getY();
		double w = bounds.getWidth();
		double h = bounds.getHeight();
		String shapeName = item.getString(DotAdapter.DOT_SHAPE);
		Shape result;

		if ("diamond".equals(shapeName)) {
            result = getDiamondShape(x, y, w, h);
        } else if ("box".equals(shapeName)) {
			result = getBoxShape(x, y, w, h);
		} else if ("circle".equals(shapeName)) {
			result = getCircleShape(x, y, w, h);
		} else if ("ellipse".equals(shapeName)) {
			result = getEllipseShape(x, y, w, h);
		} else {
			result = getBoxShape(x, y, w, h);
		}

		return result;
	}

	private Shape getBoxShape(double x, double y, double width, double height) {
		box.setFrame(x, y, width, height);
		return box;
	}

	private Shape getCircleShape(double x, double y, double width, double height) {
		double radius = Math.max(width, height);
		ellipse.setFrame(x, y, radius, radius);
		return ellipse;
	}

	private Shape getEllipseShape(double x, double y, double width,
			double height) {
		ellipse.setFrame(x, y, width, height);
		return ellipse;
	}
	
	public static Shape getDiamondShape(double x, double y, double width,
            double height) {
        int iw = (int) width;
        int ih = (int) height;
        int ix = (int) x + iw / 2;
        int iy = (int) y + ih / 2;
        
        /*int[] xs = new int[] { ix - (iw / ih) * (ih / 2), ix + iw / 2,
                ix + iw + (iw / ih) * (ih / 2), ix + iw / 2 };
        int[] ys = new int[] { iy + ih / 2, iy + ih + (ih / iw) * (iw / 2),
                iy + ih / 2, iy - (ih / iw) * (iw / 2) };*/

        int[] xs = new int[] { ix, ix + iw / 2, ix, ix - iw / 2 };
        int[] ys = new int[] { iy + ih / 2, iy, iy - ih / 2, iy };
        
        return new Polygon(xs, ys, 4);
    }

}