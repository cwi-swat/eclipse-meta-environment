package toolbus_ide.editor;

import java_cup.runtime.Symbol;

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

	public int getEndOffset(Object node){
		if(node instanceof SymbolHolder){
			SymbolHolder sh = (SymbolHolder) node;
			return sh.offset;
		}
		
		return 0;
	}

	public int getLength(Object node){
		if(node instanceof SymbolHolder){
			SymbolHolder sh = (SymbolHolder) node;
			Object value = sh.symbol.value;
			
			if(value == null) return 0;
			
			return value.toString().length();
		}
		
		return 0;
	}

	public IPath getPath(Object node){
		return null;
	}

	public int getStartOffset(Object node){
		return getEndOffset(node) - getLength(node);
	}
}
