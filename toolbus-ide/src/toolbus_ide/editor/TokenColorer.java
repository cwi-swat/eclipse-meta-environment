package toolbus_ide.editor;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import toolbus.parsercup.sym;
import toolbus_ide.editor.ParseController.SymbolHolder;

public class TokenColorer extends TokenColorerBase implements ITokenColorer {
	private final TextAttribute defaultAttribute;
	private final TextAttribute keywordAttribute;
	private final TextAttribute stringAttribute;
	private final TextAttribute numberAttribute;
	private final TextAttribute doubleAttribute;
	private final TextAttribute identifierAttribute;

	public TokenColorer() {
		super();

		Display display = Display.getDefault();

		defaultAttribute = new TextAttribute(Display.getDefault().getSystemColor(SWT.COLOR_BLACK), null, SWT.NONE);
		stringAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.BOLD);
		identifierAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		doubleAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.BOLD);
		numberAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.BOLD);
		keywordAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD);
	}

	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o instanceof SymbolHolder) {
			SymbolHolder sh = (SymbolHolder) o;
			switch (sh.symbol.sym) {
			case sym.IDENT:
				return identifierAttribute;
			case sym.STRING:
				return stringAttribute;
			case sym.INT:
				return numberAttribute;
			case sym.REAL:
				return doubleAttribute;
			case sym.NO_NOTE:
			case sym.READ:
			case sym.SHUTDOWN:
			case sym.SND_NOTE:
			case sym.REC_NOTE:
			case sym.TOOLBUS:
			case sym.DEFINE:
			case sym.REC_PERF_STATS:
			case sym.SND_TERMINATE:
			case sym.SND_ACK_EVENT:
			case sym.FI:
			case sym.PROCESS:
			case sym.FALSE:
			case sym.GET_PERF_STATS:
			case sym.THEN:
			case sym.TAU:
			case sym.IFDEF:
			case sym.CLASS:
			case sym.INCLUDE:
			case sym.PRINTF:
			case sym.TRUE:
			case sym.SND_KILL:
			case sym.HOST:
			case sym.IFNDEF:
			case sym.TOOL:
			case sym.REC_VALUE:
			case sym.EXECUTE:
			case sym.REC_EVENT:
			case sym.SND_DO:
			case sym.ELSE:
			case sym.DELTA:
			case sym.DISRUPT:
			case sym.UNDEFINED:
			case sym.REC_REQUEST:
			case sym.REC_DISCONNECT:
			case sym.ENDIF:
			case sym.UNSUBSCRIBE:
			case sym.SND_EVAL:
			case sym.IS:
			case sym.IN:
			case sym.SND_CANCEL:
			case sym.SND_MSG:
			case sym.REC_MSG:
			case sym.SND_RESPONSE:
			case sym.IF:
			case sym.CREATE:
			case sym.REC_CONNECT:
			case sym.ENDLET:
			case sym.SUBSCRIBE:
			case sym.LET:
			case sym.EQUALS:
				return keywordAttribute;
			default:
				return defaultAttribute;
			}
		}
		return defaultAttribute;
	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}
}
