/**
 * 
 */
package org.meta_environment.eclipse.tokens;

import errorapi.types.Area;

public class Token {
	private String category;
	private Area area;
	
	public Token(String category, Area area) {
		this.category = category;
		this.area = area;
	}
	
	public String getCategory() {
		return category;
	}
	
	public Area getArea() {
		return area;
	}
}