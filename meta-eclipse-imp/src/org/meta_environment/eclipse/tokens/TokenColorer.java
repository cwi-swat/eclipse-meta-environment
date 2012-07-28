package org.meta_environment.eclipse.tokens;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class TokenColorer implements ITokenColorer {
	private TextAttribute normal;

	private Map<String,TextAttribute> map = new HashMap<String,TextAttribute>();

	public TokenColorer() {
		super();
		normal = new TextAttribute(Display.getDefault().getSystemColor(
				SWT.COLOR_BLACK), null, SWT.NONE);
		
	    map.put("MetaKeyword", new TextAttribute(new Color(Display.getDefault(), 123, 0,
				82), null, SWT.BOLD));
	    map.put("MetaVariable", new TextAttribute(new Color(Display.getDefault(), 0, 0,
				255), null, SWT.ITALIC));
	    map.put("MetaAmbiguity",  new TextAttribute(new Color(Display.getDefault(), 186,
				29, 29), null, SWT.BOLD));
		map.put("Todo",new TextAttribute(
				new Color(Display.getDefault(), 123, 157, 198), null, SWT.BOLD));
		map.put("Comment",new TextAttribute(new Color(Display.getDefault(), 82, 141,
				115), null, SWT.ITALIC));
		map.put("Constant",new TextAttribute(new Color(Display.getDefault(), 139, 0,
				139), null, SWT.NONE));
		map.put("Variable",new TextAttribute(new Color(Display.getDefault(), 144, 238,
				144), null, SWT.NONE));
		map.put("Identifier",new TextAttribute(new Color(Display.getDefault(), 255, 69,
				0), null, SWT.NONE));
		map.put("Type",new TextAttribute(new Color(Display.getDefault(), 255, 127, 36),
				null, SWT.NONE));
	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}

	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o instanceof Token) {
			Token t = (Token) o;
			TextAttribute attr = map.get(t.getCategory());
			return attr != null ? attr : normal;
		}

		return normal;
	}
}
