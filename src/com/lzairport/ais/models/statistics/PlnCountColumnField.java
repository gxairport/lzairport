package com.lzairport.ais.models.statistics;

import java.util.Date;

/**
 * 
 * FileName      PlnCountColumnField.java
 * @Description  计划飞行架次的列字段
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年2月27日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年2月27日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
public class PlnCountColumnField extends ColumnField {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	/**
	 * 行字段，第一个Group字段
	 */
	private GroupField rowField;
	
	/**
	 * 开始时间
	 */
	private Date startDate;
	
	/**
	 * 结束字段
	 */
	private Date endDate;
	/**
	 * @return the rowField
	 */
	public GroupField getRowField() {
		return rowField;
	}

	/**
	 * @param rowField the rowField to set
	 */
	public void setRowField(GroupField rowField) {
		this.rowField = rowField;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
}
