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

public class TokenColorer extends TokenColorerBase implements ITokenColorer{
	private final TextAttribute defaultAttribute;
	private final TextAttribute commentAttribute;
	private final TextAttribute keywordAttribute;
	private final TextAttribute stringAttribute;
	private final TextAttribute numberAttribute;
	private final TextAttribute doubleAttribute;
	private final TextAttribute identifierAttribute;
	
	public TokenColorer(){
		super();
		
		Display display = Display.getDefault();

		defaultAttribute = new TextAttribute(Display.getDefault().getSystemColor(SWT.COLOR_BLACK), null, SWT.NONE);
		
		commentAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.ITALIC);
		stringAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.BOLD);
		identifierAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		doubleAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.BOLD);
		numberAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.BOLD);
		keywordAttribute = new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD);
		
	}
	
	public TextAttribute getColoring(IParseController controller, Object o){
		if(o instanceof SymbolHolder){
			SymbolHolder sh = (SymbolHolder) o;
			switch(sh.symbol.sym){
				case sym.IDENT:
					return identifierAttribute;
				case sym.STRING:
					return stringAttribute;
				case sym.INT:
					return numberAttribute;
				case sym.REAL:
					return doubleAttribute;
				case sym.PROCESS:
				case sym.IS:
				case sym.IF:
				case sym.THEN:
				case sym.ELSE:
				case sym.FI:
				case sym.LET:
				case sym.IN:
				case sym.ENDLET:
				case sym.TAU:
				case sym.DELTA:
					return keywordAttribute;
				default:
					return defaultAttribute;
			}
		}
		return defaultAttribute;
	}
	
	public IRegion calculateDamageExtent(IRegion seed){
		return seed;
	}
}
