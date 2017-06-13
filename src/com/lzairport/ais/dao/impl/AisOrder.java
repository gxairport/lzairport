package com.lzairport.ais.dao.impl;

import java.io.Serializable;

/**
 * 
 * ≈≈–Ú¿‡
 * @author ZhangYu
 * version 0.9a 12/11/14
 * @since JDK 1.6
 * 
 */

public class AisOrder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String sortMode;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sortMode
	 */
	public String getSortMode() {
		return sortMode;
	}

	/**
	 * @param sortMode the sortMode to set
	 */
	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}
	
	

}
