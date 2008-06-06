package toolbus_ide.editor;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class TscriptTokenColorer extends TokenColorerBase implements ITokenColorer {

	TextAttribute commentAttribute, keywordAttribute, stringAttribute,
			numberAttribute, doubleAttribute, identifierAttribute;

	public TextAttribute getColoring(IParseController controller, Object o) {
		/* IToken token = (IToken) o;
				if (token.getKind() == TK_EOF_TOKEN)
			return null;

		switch (token.getKind()) {
		// START_HERE
		case TK_IDENTIFIER:
			return identifierAttribute;
		case TK_NUMBER:
			return numberAttribute;
		case TK_DoubleLiteral:
			return doubleAttribute;
			//          case TK_StringLiteral:
			//               return stringAttribute;
		default:
			if (((TscriptParseController) controller)
					.isKeyword(token.getKind()))
				return keywordAttribute;
			return super.getColoring(controller, token);
		}
*/
		return keywordAttribute;
	}

	public TscriptTokenColorer() {
		super();
		// TODO:  Define text attributes for the various
		// token types that will have their text colored
		Display display = Display.getDefault();
		commentAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.ITALIC);
		stringAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.BOLD);
		identifierAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		doubleAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.BOLD);
		numberAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.BOLD);
		keywordAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD);
	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}

}
