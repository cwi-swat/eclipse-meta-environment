/*
 * Created on May 23, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.editor;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.texteditor.BasicTextEditorActionContributor;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MetaEditorActionContributor extends BasicTextEditorActionContributor {
	StatusLineContribution statusLineContribution;
	
	public void contributeToStatusLine(IStatusLineManager statusLineManager) {
		super.contributeToStatusLine(statusLineManager);
		
		statusLineManager.add(statusLineContribution);
	}
}
