/*
 * Created on Jun 4, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio.moduleview;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nl.cwi.sen.metastudio.MetastudioPlugin;
import nl.cwi.sen.metastudio.model.Directory;
import nl.cwi.sen.metastudio.model.Module;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ModuleExplorerLabelProvider extends LabelProvider {
	private Map imageCache = new HashMap(11);
	
	public Image getImage(Object element) {
		ImageDescriptor descriptor = null;
		if (element instanceof Directory) {
			descriptor = MetastudioPlugin.getImageDescriptor("fldr_obj.gif");
		} else if (element instanceof Module) {
			descriptor = MetastudioPlugin.getImageDescriptor("file_obj.gif");
		} else {
			throw new RuntimeException();
		}

		//obtain the cached image corresponding to the descriptor
		Image image = (Image)imageCache.get(descriptor);
		if (image == null) {
			image = descriptor.createImage();
			imageCache.put(descriptor, image);
		}
		return image;
	}
	
	public String getText(Object element) {
		if (element instanceof Directory) {
			if(((Directory)element).getModulePath() == null) {
				return "";
			} else {
				return ((Directory)element).getModulePath();
			}
		} else if (element instanceof Module) {
			return ((Module)element).getModuleName();
		} else {
			throw new RuntimeException();
		}
	}
	
	public void dispose() {
		for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
			((Image) i.next()).dispose();
		}
		imageCache.clear();
	}
}
