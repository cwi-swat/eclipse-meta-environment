/*
 * Created on Jun 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.moduleview;


import nl.cwi.sen.metastudio.model.ModuleInfoElement;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ModuleInfoLabelProvider extends LabelProvider implements ITableLabelProvider {
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		String columnText = null;
		if (element instanceof ModuleInfoElement) {
			ModuleInfoElement moduleElement = (ModuleInfoElement)element;
			switch(columnIndex) {
				case 0:
					columnText = moduleElement.getProperty();
					break;
				case 1:
					columnText = moduleElement.getValue();
					break;
			}
		}
		return columnText;
	}
}
