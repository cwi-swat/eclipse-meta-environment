package nl.cwi.sen.metastudio.moduleview;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class ModuleExplorerMouseListener implements MouseListener {
	public void mouseDoubleClick(MouseEvent mouseEvent) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

//		System.out.println("eventdata: " + mouseEvent.toString());	
		
//		IFile file = MetastudioPlugin.getWorkspace().getRoot().getFile()
//
//		try {
//			//page.openEditor(file);
//		} catch (Exception e) {
//		}
	}

	public void mouseDown(MouseEvent mouseEvent) {
	}

	public void mouseUp(MouseEvent mouseEvent) {
	}
}
