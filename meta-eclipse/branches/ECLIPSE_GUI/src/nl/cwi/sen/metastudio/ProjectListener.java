/*
 * Created on Jun 16, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package nl.cwi.sen.metastudio;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;

import aterm.ATerm;
import aterm.ATermList;

/**
 * @author kooiker
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ProjectListener implements IResourceChangeListener {
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
			IResourceDelta delta = event.getDelta();
		 	//get deltas for the projects
		 	IResourceDelta[] projectDeltas = delta.getAffectedChildren();
		 	for (int i = 0; i < projectDeltas.length; i++) {
		  		int kind = projectDeltas[i].getKind();
		  		int flags = projectDeltas[i].getFlags();
		  		if (kind == IResourceDelta.CHANGED && ((flags & IResourceDelta.OPEN) != 0)) {
		   			IProject project = (IProject)projectDeltas[i].getResource();
		   			if (project.isOpen()) {
		   				IResource[] members = null;
		   				try {
		   					members = project.members();
		   				} catch (CoreException e) {
		   					System.out.println(e.getMessage());
		   				}
		   				
		   				MetastudioConnection connection = new MetastudioConnection();
						ATermList fileList = connection.getPureFactory().makeList();
		   				for (int j = 0; j < members.length; j++) {
		   					if (members[j].getFileExtension().equals("sdf") == true) {
								String fileName = (members[j].getLocation().removeFileExtension().lastSegment()).toString();
								ATerm file = (ATerm)connection.getPureFactory().make("<str>", fileName);
								fileList = fileList.insert(file);
		   					}
		   				}
						//ATermList actionEvent = (ATermList)UserInterface.factory.make("[\"Open Project\"]");
						connection.getBridge().postEvent(connection.getPureFactory().make("eclipse-open-modules(<list>,sdf)", fileList));
		  			}
		 		}
		 	}
		}
	}
}
