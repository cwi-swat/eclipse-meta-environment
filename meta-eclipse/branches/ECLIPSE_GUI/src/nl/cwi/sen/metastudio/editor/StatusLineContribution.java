/*
 * Created on May 23, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.editor;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StatusLineContribution extends ControlContribution {
	private Composite composite;
	private Label label;

	protected StatusLineContribution() {
		super("StatusLine");
	}

	protected Control createControl(Composite parent) {
		// If the composite is good just return it.
		if (composite != null && !composite.isDisposed())
			return composite;
		
		// Create composite for border.
		composite = new Composite(parent, SWT.BORDER);
		composite.setData(this);

		// Create label inside composite.	
		label = new Label(composite, SWT.NONE);
		label.setSize(80, 15);
	
		//updateState();
		return composite;
	}

}
