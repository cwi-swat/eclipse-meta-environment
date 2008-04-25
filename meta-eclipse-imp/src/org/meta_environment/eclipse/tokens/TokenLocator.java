package org.meta_environment.eclipse.tokens;

import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.language.ILanguageService;
import org.eclipse.imp.parser.ISourcePositionLocator;

import errorapi.types.Area;

public class TokenLocator implements ISourcePositionLocator, ILanguageService {

	public Object findNode(Object ast, int offset) {
		return null;
	}

	public Object findNode(Object ast, int startOffset, int endOffset) {
		return null;
	}

	public int getEndOffset(Object node) {
		if (node instanceof Token) {
			Area a = ((Token) node).getArea();
			return a.getOffset() + a.getLength() - 1;
		}
		
		return 0;
	}

	public int getLength(Object node) {
		if (node instanceof Token) {
			Area a = ((Token) node).getArea();
			return a.getLength();
		}
		
		return 0;
	}

	public IPath getPath(Object node) {
		return null;
	}

	public int getStartOffset(Object node) {
		if (node instanceof Token) {
			Area a = ((Token) node).getArea();
			return a.getOffset();
		}
		
		return 0;
	}
}
