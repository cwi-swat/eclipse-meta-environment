package org.meta_environment.eclipse.modules;

import aterm.ATerm;

public interface AttributeSetListener {
	public void attributeSet(String id, ATerm namespace, ATerm key, ATerm oldValue, ATerm newValue);
}
