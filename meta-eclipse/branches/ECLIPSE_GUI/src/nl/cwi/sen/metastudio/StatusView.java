package nl.cwi.sen.metastudio;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

/**
 * TODO: Provide description for "StatusView".
 * @see ViewPart
 */
public class StatusView extends ViewPart {
	private Label label;
	
	/**
	 * TODO: Implement the "StatusView" constructor.
	 */
	public StatusView() {
		super();
	}

	public void init(IViewSite site) throws PartInitException {
		super.init(site);
	}

	/**
	 * TODO: Implement "createPartControl".
	 * @see ViewPart#createPartControl
	 */
	public void createPartControl(Composite parent)  {
		// Create viewer
		label = new Label(parent, 0);		
	}

	/**
	 * TODO: Implement "setFocus".
	 * @see ViewPart#setFocus
	 */
	public void setFocus()  {
	}
	
	public void setText(String status) {
		label.setText(status);
	}
}
