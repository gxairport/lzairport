package com.lzairport.ais.service;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * FileName      DataFetchResponseInfo.java
 * @Description  用来存储返回的Response数据的类，包含有记录集合和记录数量
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015年10月6日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015年10月6日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

public class DataFetchResponseInfo implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private int totalRows;
	
	private List<?> matchingObjects;

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return the matchingObjects
	 */
	public List<?> getMatchingObjects() {
		return matchingObjects;
	}

	/**
	 * @param matchingObjects the matchingObjects to set
	 */
	public void setMatchingObjects(List<?> matchingObjects) {
		this.matchingObjects = matchingObjects;
	}		
	
	

}
