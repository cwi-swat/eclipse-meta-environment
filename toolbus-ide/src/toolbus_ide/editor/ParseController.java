package toolbus_ide.editor;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.services.IAnnotationTypeInfo;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.jface.text.IRegion;

import toolbus.ToolBus;
import toolbus.parsercup.parser.SyntaxErrorException;

public class ParseController implements IParseController {
	private IMessageHandler handler;
	private IPath filePath;
	private ISourceProject project;
	private String absPath;

	public IAnnotationTypeInfo getAnnotationTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public Iterator getTokenIterator(IRegion region){
    	return new Iterator(){

			public boolean hasNext(){
				return false;
			}

			public Object next(){
				return null;
			}

			public void remove(){
			}
    		
    	};
    }

	public Object getCurrentAst() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISourcePositionLocator getNodeLocator() {
		// TODO Auto-generated method stub
		return null;
	}

	public IPath getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISourceProject getProject() {
		// TODO Auto-generated method stub
		return null;
	}

	public ILanguageSyntaxProperties getSyntaxProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public void initialize(IPath filePath, ISourceProject project, IMessageHandler handler) {
		this.handler = handler;
		this.filePath = filePath;
		this.project = project;
		
		// Try to make the path absolute
		IFile file = project.getRawProject().getFile(filePath);
		
		if(file.exists()){
			absPath = file.getLocation().toOSString();
		}else{
			absPath = filePath.toOSString();
		}
	}

	public Object parse(String input, boolean scanOnly, IProgressMonitor monitor) {
		ToolBus toolbus = new ToolBus(new String[] {"-S"+absPath, "-I."});
		try{
			toolbus.parsecup();
	    }catch(SyntaxErrorException see){ // Parser.
	    	handler.handleSimpleMessage(see.getMessage(), see.position, see.position, see.column, see.column, see.line, see.line);
		}catch(Error e){ // Scanner.
			e.printStackTrace();	
	    }catch(Exception ex){ // Something else.
	    	ex.printStackTrace();
	    }
		
		return null;
	}
}
