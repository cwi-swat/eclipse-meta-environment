package toolbus_ide;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ErrorHandler{
	
	private ErrorHandler(){
		super();
	}

	public static void clearMarkers(IFile file) {
		try{
    		file.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
	    }catch(CoreException ce){
			// I don't care what happened, just ignore this.
		}
	}
	
	public static void addProblemMarker(IFile file, int charOffset, int line, int column, String message){
		try{
	    	IMarker m = file.createMarker(IMarker.PROBLEM);
	
			m.setAttribute(IMarker.TRANSIENT, true);
			m.setAttribute(IMarker.CHAR_START, charOffset);
			m.setAttribute(IMarker.CHAR_END, charOffset);
			m.setAttribute(IMarker.MESSAGE, message);
			m.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			m.setAttribute(IMarker.LOCATION, "line: " + line + ", column: " + column);
    	}catch(CoreException ce){
    		// I don't care what happened, just ignore this.
    	}
    }
	
	public static void addWarningMarker(IFile file, int charOffset, int line, int column, String message){
    	try{
	    	IMarker m = file.createMarker(IMarker.PROBLEM);
	
			m.setAttribute(IMarker.TRANSIENT, true);
			m.setAttribute(IMarker.CHAR_START, charOffset);
			m.setAttribute(IMarker.CHAR_END, charOffset);
			m.setAttribute(IMarker.MESSAGE, message);
			m.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
			m.setAttribute(IMarker.LOCATION, "line: " + line + ", column: " + column);
    	}catch(CoreException ce){
    		// I don't care what happened, just ignore this.
    	}
    }
}
