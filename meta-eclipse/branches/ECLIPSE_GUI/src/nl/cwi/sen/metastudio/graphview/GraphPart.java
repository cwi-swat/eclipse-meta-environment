package nl.cwi.sen.metastudio.graphview;

import metastudio.graph.Graph;
import metastudio.graph.Node;
import nl.cwi.sen.metastudio.MetastudioConnection;
import nl.cwi.sen.metastudio.PopupMenu;
import nl.cwi.sen.metastudio.UserInterface;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class GraphPart extends ViewPart {
	private Node node;
	private Graph graph;
	private Canvas canvas;
	private GraphLibrary graphLibrary;
	private Image image;

	private int ix = 0, iy = 0;
	private int mouseX, mouseY;

	private Color background;

	private PopupMenu popupMenu = new PopupMenu();

	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);

		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				doPaint(event);
				resizeScrollBars();
			}
		});

		addListeners();
		createContextMenu(canvas);

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
				mouseX = e.x;
				mouseY = e.y;
			}

			public void mouseUp(MouseEvent e) {
				if (graphLibrary != null) {
					node =
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
					node =
						graphLibrary.getNodeAt(graph, e.x - ix, e.y - iy);
					if (graphLibrary.nodeHighlighted(node) == true) {
						canvas.redraw();
					}
				}
			}
		});
	}

	private void createContextMenu(Canvas canvas) { //create context menu
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				if (graph != null
					&& graphLibrary.getNodeAt(graph, mouseX, mouseY) != null) {
					fillContextMenu(mgr);
				}
			}
		});
		Menu menu = menuMgr.createContextMenu(canvas);
		canvas.setMenu(menu);
	}
	
	public void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		if (node != null) {
			final String moduleName = node.getLabel();
			MetastudioConnection connection = UserInterface.getConnection();

			popupMenu.invalidate();
			connection.getBridge().postEvent(
				connection.getPureFactory().make(
					"get-buttons(module-popup, <str>)",
					moduleName));

			// wait until actions are received
			while (popupMenu.getLoadedState() == false);

			ATermList actions = popupMenu.getMenu();

			while (!actions.isEmpty()) {
				ATerm action = actions.getFirst();
				ATermList actionList =
					(ATermList) ((ATermAppl) action).getArgument(0);
				ATermAppl actionNamePrefix =
					(ATermAppl) (actionList.getFirst());

				actions = actions.getNext();

				if (actionList.getLength() == 1) {
					manager.add(
						new GraphPartAction(
							actionNamePrefix.getName(),
							popupMenu.getActionType(),
							action,
							moduleName));
				} else {
					ATermList actionRunner = actions;
					IMenuManager nextLevel =
						new MenuManager(actionNamePrefix.getName());
					ATerm apifyMe =
						connection.getPureFactory().make(
							"menu(<term>)",
							actionList.getNext());
					ATermList subMenu =
						connection.getPureFactory().makeList(apifyMe);

					// collect a list of buttons that are in the same 'menuNamePrefix'
					for (;
						!actionRunner.isEmpty();
						actionRunner = actionRunner.getNext()) {
						ATerm cur = actionRunner.getFirst();
						ATermList curList =
							(ATermList) ((ATermAppl) cur).getArgument(0);
						ATerm menuNamePrefix = curList.getFirst();

						if (actionNamePrefix.isEqual(menuNamePrefix)) {
							apifyMe =
								connection.getPureFactory().make(
									"menu(<term>)",
									curList.getNext());
							subMenu = subMenu.insert(apifyMe);
							actions = actions.remove(cur);
						}
					}

					addMenuItems(
						nextLevel,
						popupMenu.getActionType(),
						popupMenu.getModuleName(),
						subMenu,
						connection.getPureFactory().makeList(actionNamePrefix),
						connection);
					manager.add(nextLevel);
				}
			}
		}
	}

	public void addMenuItems(
		IMenuManager menu,
		ATerm actionType,
		String moduleName,
		ATermList actions,
		ATermList prefixActionName,
		MetastudioConnection connection) {

		UserInterface ui = new UserInterface();
		actions = actions.reverse();

		while (!actions.isEmpty()) {
			ATerm action = actions.getFirst();
			ATermList actionList =
				(ATermList) ((ATermAppl) action).getArgument(0);
			ATermAppl buttonNamePrefix = (ATermAppl) (actionList.getFirst());

			actions = actions.getNext();

			if (actionList.getLength() == 1) {
				ATerm apifyMe =
					connection.getPureFactory().make(
						"menu(<term>)",
						prefixActionName.concat(actionList));
				menu.add(
					new GraphPartAction(
						buttonNamePrefix.getName(),
						popupMenu.getActionType(),
						action,
						moduleName));
			} else {
				ATermList actionRunner = actions;
				IMenuManager nextLevel =
					new MenuManager(buttonNamePrefix.getName());
				ATermList subMenu =
					connection.getPureFactory().makeList(
						(ATerm) actionList.getNext());

				// collect a list of buttons that are in the same 'menuNamePrefix'
				while (!actionRunner.isEmpty()) {
					ATerm cur = actionRunner.getFirst();
					ATermList curList =
						(ATermList) ((ATermAppl) cur).getArgument(0);
					ATerm menuNamePrefix = curList.getFirst();

					if (buttonNamePrefix.isEqual(menuNamePrefix)) {
						ATerm apifyMe =
							connection.getPureFactory().make(
								"menu(<term>)",
								curList.getNext());
						subMenu = subMenu.insert(apifyMe);
						actions = actions.remove(cur);
					}

					actionRunner = actionRunner.getNext();
				}

				addMenuItems(
					nextLevel,
					actionType,
					moduleName,
					subMenu,
					prefixActionName.insertAt(
						buttonNamePrefix,
						prefixActionName.getLength()),
					connection);
				menu.add(nextLevel);
			}
		}
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
