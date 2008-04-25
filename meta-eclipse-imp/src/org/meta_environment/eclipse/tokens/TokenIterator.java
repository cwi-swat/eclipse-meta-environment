/**
 * 
 */
package org.meta_environment.eclipse.tokens;

import java.util.Iterator;

import aterm.ATermList;
import errorapi.Factory;
import errorapi.types.Area;
import errorapi.types.AreaAreas;
import errorapi.types.Slice;

public class TokenIterator implements Iterator {
	private ATermList slices;
	private AreaAreas areas;
	private String category;
	private Factory eFactory;

	public TokenIterator(Factory factory, ATermList slices) {
		this.eFactory = factory;
		this.slices = slices;
		areas = eFactory.makeAreaAreas();
	}
	
	public boolean hasNext() {
		return !slices.isEmpty() || !areas.isEmpty();
	}

	public Object next() {
		if (areas.isEmpty() && !slices.isEmpty()) {
		  Slice slice = eFactory.SliceFromTerm(slices.getFirst());
		  slices = slices.getNext();
		  areas = slice.getAreas();
		  category = slice.getId();
		}
		
		if (!areas.isEmpty()) {
			Area area = areas.getHead();
			areas = areas.getTail();
			return new Token(category, area);
		}
		
		return null;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}