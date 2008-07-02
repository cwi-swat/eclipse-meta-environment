package toolbus_ide;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.runtime.PluginBase;

import toolbus.ToolBus;
import toolbus.exceptions.ToolBusException;
import toolbus.exceptions.ToolBusExecutionException;
import toolbus.parsercup.PositionInformation;
import toolbus.parsercup.SyntaxErrorException;
import toolbus.parsercup.parser;
import toolbus.parsercup.parser.UndeclaredVariableException;
import toolbus_ide.editor.ParseController;
import aterm.ATerm;

public class Builder extends BuilderBase {
    public static final String BUILDER_ID = Activator.kPluginID + ".builder";

    public static final String LANGUAGE_NAME = Activator.kLanguageName;

    public static final Language LANGUAGE = LanguageRegistry.findLanguage(LANGUAGE_NAME);

    public Builder() {
    	super();
	}
    
    protected String getErrorMarkerID(){
        return IMarker.PROBLEM;
    }

    protected String getWarningMarkerID(){
        return IMarker.PROBLEM;
    }

    protected String getInfoMarkerID(){
        return IMarker.PROBLEM;
    }

    protected boolean isSourceFile(IFile file){
        IPath path = file.getRawLocation();
        if (path == null) return false;

        String pathString = path.toString();
        if(pathString.indexOf("/bin/") != -1) return false;

        return LANGUAGE.hasExtension(path.getFileExtension());
    }

    protected boolean isNonRootSourceFile(IFile resource){
        return false;
    }

    protected void collectDependencies(IFile file){
    }

    protected boolean isOutputFolder(IResource resource){
        return resource.getFullPath().lastSegment().equals("bin");
    }
    
    protected void compile(final IFile file, IProgressMonitor monitor){
    	// Clear the old markers for this file.
    	ErrorHandler.clearMarkers(file);
	    
    	String filename = file.getLocation().toOSString();
    	String[] includePath = ParseController.buildIncludePath();
    	ToolBus toolbus = new ToolBus(includePath);
    	
		try{
			parser parser_obj = new parser(new HashSet<String>(), new ArrayList<ATerm>(), filename, new FileReader(filename), toolbus);
			parser_obj.parse();
		}catch(SyntaxErrorException see){ // Parser.
			ErrorHandler.addMarker(file, see.position, see.line, see.column, "Syntax error");
		}catch(UndeclaredVariableException uvex){ // Parser.
			ErrorHandler.addMarker(file, uvex.position, uvex.line, uvex.column, "Undeclared variable");
		}catch(ToolBusExecutionException e){
			PositionInformation p = e.getPositionInformation();
			ErrorHandler.addMarker(file, p.getOffset(), 0, 0, e.getMessage());
		}catch(ToolBusException e){
			ErrorHandler.addMarker(file, 0, 0, 0, e.getMessage());
		}catch(Error e){ // Scanner.
			ErrorHandler.addMarker(file, 0, 0, 0, e.getMessage());
		}catch(Exception ex){ // Something else.
			ErrorHandler.addMarker(file, 0, 0, 0, ex.getMessage());
		}
    }
    
	protected PluginBase getPlugin(){
		return Activator.getInstance();
	}
}
