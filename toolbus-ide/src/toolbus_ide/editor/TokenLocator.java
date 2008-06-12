package toolbus_ide.editor;

import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.language.ILanguageService;
import org.eclipse.imp.parser.ISourcePositionLocator;

public class TokenLocator implements ISourcePositionLocator, ILanguageService {

	public Object findNode(Object ast, int offset) {
		return null;
	}

	public Object findNode(Object ast, int startOffset, int endOffset) {
		return null;
	}

	public int getEndOffset(Object node) {
		/*if (node instanceof Symbol) {
			Symbol s = (Symbol) node;
			return s.getOffset() + s.getLength() - 1;
		}*/
		
		return 0;
	}

	public int getLength(Object node) {
		/*if (node instanceof Symbol) {
			Symbol s = (Symbol) node;
			return s.getLength();
		}*/
		
		return 0;
	}

	public IPath getPath(Object node) {
		return null;
	}

	public int getStartOffset(Object node) {
		/*if (node instanceof Symbol) {
			Symbol s = (Symbol) node;
			return a.getOffset();
		}*/
		
		return 0;
	}
}
