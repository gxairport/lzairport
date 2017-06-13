/**
 * 
 */
package com.lzairport.ais.models.statistics;

import java.util.List;

/**
 * FileName      ChartCondistion.java
 * @Description  图表的限制条件
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年4月14日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年4月14日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


public class LimitCondistion {
	
	
	private String name;
	
	private String index;
	
	private List<String> values;

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
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}
	
	

}
