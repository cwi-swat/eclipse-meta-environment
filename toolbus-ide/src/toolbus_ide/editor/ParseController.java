package toolbus_ide.editor;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java_cup.runtime.Symbol;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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
import toolbus.exceptions.ToolBusException;
import toolbus.exceptions.ToolBusExecutionException;
import toolbus.parsercup.Lexer;
import toolbus.parsercup.PositionInformation;
import toolbus.parsercup.SyntaxErrorException;
import toolbus.parsercup.parser;
import toolbus.parsercup.sym;
import toolbus.parsercup.parser.UndeclaredVariableException;

public class ParseController implements IParseController, IResourceChangeListener{
	private volatile IMessageHandler handler;
	private volatile String absPath;

	private volatile Lexer lexer;
	private static String[] includePath = buildIncludePath();
	

	public ParseController() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	
	public IAnnotationTypeInfo getAnnotationTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public static class SymbolHolder {
		public final Symbol symbol;
		public final int startOffset;
		public final int endOffset;

		public SymbolHolder(Symbol symbol, int startOffset, int endOffset) {
			this.symbol = symbol;
			this.startOffset = startOffset;
			this.endOffset = endOffset;
		}
	}

	public Iterator<?> getTokenIterator(IRegion region) {
		class TokenIterator implements Iterator<SymbolHolder> {
			private int currentOffset;
			private Symbol nextSymbol;

			public TokenIterator() {
				super();
				prepareNext();
			}

			public void prepareNext() {
				try {
					nextSymbol = lexer.next_token();
					currentOffset = lexer.getPosition();
				} catch (IOException ioex) {
					// Ignore this, since it can't happen.
				} catch (Error e) {
					// This doesn't matter. it'll just generate error symbols.
				}
			}

			public boolean hasNext() {
				return !(nextSymbol.sym == sym.EOF || nextSymbol.sym == sym.error);
			}

			public SymbolHolder next() {
				if (!hasNext())
					return null;

				int offset = currentOffset;
				Symbol symbol = nextSymbol;

				prepareNext();

				return new SymbolHolder(symbol, offset, currentOffset);
			}

			public void remove() {
				throw new UnsupportedOperationException(
						"Removing is not supported by this iterator.");
			}
		}

		return new TokenIterator();
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

	public void initialize(IPath filePath, ISourceProject project,
			IMessageHandler handler) {
		this.handler = handler;

		// Try to make the path absolute
		IFile file = project.getRawProject().getFile(filePath);

		if (file.exists()) {
			absPath = file.getLocation().toOSString();
		} else {
			absPath = filePath.toOSString();
		}
	}

	private static String[] buildIncludePath() {
		final List<String> includes = new ArrayList<String>();

		try {
			ResourcesPlugin.getWorkspace().getRoot().accept(
					new IResourceVisitor() {

						public boolean visit(IResource resource) {
							if (resource instanceof IFolder) {
								IFolder folder = (IFolder) resource;
								IPath path = folder.getLocation();
								File file = path.toFile();
								includes.add("-I" + file.getAbsolutePath());
							}
							else if (resource instanceof IProject) {
								IProject project = (IProject) resource;
								IPath path = project.getLocation();
								File file = path.toFile();
								includes.add("-I" + file.getAbsolutePath());
							}
							return true;
						}

					});
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return includes.toArray(new String[includes.size()]);
	}

	public Object parse(String input, boolean scanOnly, IProgressMonitor monitor) {
		lexer = new Lexer(new StringReader(input));

		ToolBus toolbus = new ToolBus(includePath);
		try{
			parser parser_obj = new parser(toolbus, absPath, new StringReader(input));
			parser_obj.parseIncludes(false);
			parser_obj.parse();
			
			return toolbus;
		}catch(SyntaxErrorException see){ // Parser.
			// TODO assuming the input is the whole file, this fix is correct.
			// needs to be fixed in IMP
			int pos = (see.position >= input.length()) ? input.length() - 1 : see.position;
			handler.handleSimpleMessage(see.getMessage(), pos, pos, see.column, see.column, see.line, see.line);
		}catch(UndeclaredVariableException uvex){ // Parser.
			int pos = (uvex.position >= input.length()) ? input.length() - 1 : uvex.position;
			handler.handleSimpleMessage(uvex.getMessage(), pos, pos, uvex.column, uvex.column, uvex.line, uvex.line);
		}catch(ToolBusExecutionException e){
			PositionInformation p = e.getPositionInformation();
			handler.handleSimpleMessage(e.getMessage(), p.getOffset(), p.getOffset(), 0,0,1,1);
		}catch(ToolBusException e){
			handler.handleSimpleMessage(e.getMessage(), 0, 0, 0, 0, 1, 1);
			e.printStackTrace();
		}catch(Error e){ // Scanner.
			handler.handleSimpleMessage(e.getMessage(), 0, 0, 0, 0, 1, 1);
		}catch(Exception ex){ // Something else.
			handler.handleSimpleMessage(ex.getMessage(), 0, 0, 0, 0, 1, 1);
		}

		return null;
	}

	/**
	 * updates the include path as soon as somebody changes folders
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		IResource resource = event.getResource();
		
		if (resource instanceof IProject ||
				resource instanceof IFolder) {
			includePath = buildIncludePath();
		}
	}
}
