package com.lzairport.ais.models.statistics;

import com.lzairport.ais.utils.SYS_VARS.GrpDate;

/**
 * 
 * FileName      GroupField.java
 * @Description  分组字段，用于报表字段的行，进行GroupBy的统计，表达式的参数只允许一个字段
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年2月8日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年2月8日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */



public class GroupField extends ReportField {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	public static String EXPRESSTION ="Expresstion";
	
	public static String GRPDATE ="grpDate";
	
	/**
	 * 表达式
	 */
	private Object expresstion;
	
	
	/**
	 * 日期类型
	 */
	private GrpDate grpDate;
	

	/**
	 * @return the expresstion
	 */
	public Object getExpresstion() {
		return expresstion;
	}

	/**
	 * @param expresstion the expresstion to set
	 */
	public void setExpresstion(Object expresstion) {
		this.expresstion = expresstion;
	}

	/**
	 * @return the grpDate
	 */
	public GrpDate getGrpDate() {
		return grpDate;
	}

	/**
	 * @param grpDate the grpDate to set
	 */
	public void setGrpDate(GrpDate grpDate) {
		this.grpDate = grpDate;
	}

}
