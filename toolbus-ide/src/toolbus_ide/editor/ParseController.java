package toolbus_ide.editor;

import java.io.IOException;
import java.io.StringReader;
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
import toolbus.parsercup.Lexer;
import toolbus.parsercup.parser.SyntaxErrorException;
import toolbus.parsercup.parser.UndeclaredVariableException;

public class ParseController implements IParseController {
	private volatile IMessageHandler handler;
	private volatile ISourceProject project;
	private volatile String absPath;
	
	private volatile Lexer lexer;

	public IAnnotationTypeInfo getAnnotationTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public Iterator getTokenIterator(IRegion region){
    	return new Iterator(){
    		private Object nextToken = null;

			public boolean hasNext(){
				if(nextToken == null){
					try{
						nextToken = lexer.next_token();
					}catch(IOException ioex){
						// Ignore this, since it can't happen.
					}
				}
				
				return !nextToken.toString().equals("#0"); // TODO: Fix this.
			}

			public Object next(){
				if(nextToken == null){
					try{
						nextToken = lexer.next_token();
					}catch(IOException ioex){
						// Ignore this, since it can't happen.
					}
				}
				
				Object token = nextToken;
				nextToken = null;
				
				return token;
			}

			public void remove(){
				throw new UnsupportedOperationException("Removing is not supported by this iterator.");
			}
    	};
    }

	public Object getCurrentAst() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISourcePositionLocator getNodeLocator() {
		return new TokenLocator();
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
		lexer = new Lexer(new StringReader(input));
		
		ToolBus toolbus = new ToolBus(new String[] {"-S"+absPath, "-I."});
		try{
			toolbus.parsecup();
	    }catch(SyntaxErrorException see){ // Parser.
	    	handler.handleSimpleMessage(see.getMessage(), see.position, see.position, see.column, see.column, see.line, see.line);
		}catch(UndeclaredVariableException uvex){ // Parser.
			handler.handleSimpleMessage(uvex.getMessage(), uvex.position, uvex.position, uvex.column, uvex.column, uvex.line, uvex.line);
		}catch(Error e){ // Scanner.
			e.printStackTrace();	
	    }catch(Exception ex){ // Something else.
	    	ex.printStackTrace();
	    }
		
		return null;
	}
}
