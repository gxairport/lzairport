package com.lzairport.ais.models.statistics;

import com.lzairport.ais.utils.SYS_VARS.SummaryType;

/**
 * 
 * FileName      DisplayField.java
 * @Description  用于报表显示的字段
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年3月4日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年3月4日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
public class DisplayField {
	
	/**
	 * 数据Key值
	 */
	private String  dataIndex;
	
	
	/**
	 * 数据的标题
	 */
	private String  text;
	
	
	/**
	 * 合计的类型
	 */
	private SummaryType summaryType;

	/**
	 * @return the dataIndex
	 */
	public String getDataIndex() {
		return dataIndex;
	}

	/**
	 * @param dataIndex the dataIndex to set
	 */
	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the summaryType
	 */
	public SummaryType getSummaryType() {
		return summaryType;
	}

	/**
	 * @param summaryType the summaryType to set
	 */
	public void setSummaryType(SummaryType summaryType) {
		this.summaryType = summaryType;
	}
	
	

}
