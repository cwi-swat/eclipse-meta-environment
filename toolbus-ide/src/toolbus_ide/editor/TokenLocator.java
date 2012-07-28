package toolbus_ide.editor;

import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.language.ILanguageService;
import org.eclipse.imp.parser.ISourcePositionLocator;

import toolbus_ide.editor.ParseController.SymbolHolder;

public class TokenLocator implements ISourcePositionLocator, ILanguageService{

	public Object findNode(Object ast, int offset){
		return null;
	}

	public Object findNode(Object ast, int startOffset, int endOffset){
		return null;
	}

	public int getStartOffset(Object node){
		if(node instanceof SymbolHolder){
			SymbolHolder sh = (SymbolHolder) node;
			return sh.startOffset;
		}
		return 0;
	}

	public int getEndOffset(Object node){
		if(node instanceof SymbolHolder){
			SymbolHolder sh = (SymbolHolder) node;
			return sh.endOffset - 1;
		}
		return 0;
	}

	public int getLength(Object node){
		return (getEndOffset(node) - getStartOffset(node));
	}

	public IPath getPath(Object node){
		return null;
	}
}
