package nl.cwi.sen.metastudio;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class StatusView extends ViewPart {
	private Label label;
	
	public StatusView() {
		super();
	}

	public void init(IViewSite site) throws PartInitException {
		super.init(site);
	}

	public void createPartControl(Composite parent)  {
		// Create viewer
		label = new Label(parent, 0);		
	}

	public void setFocus()  {
	}
	
	public void setText(String status) {
		label.setText(status);
	}
}
