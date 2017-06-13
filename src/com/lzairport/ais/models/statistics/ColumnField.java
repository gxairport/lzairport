package com.lzairport.ais.models.statistics;


/**
 * 
 * FileName      ColumnField.java
 * @Description  列字段的抽象类，所有实际的列字段都继承它
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年2月25日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年2月25日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
public abstract class ColumnField extends ReportField {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	public static String EXPRESSTION = "Expresstion";
	/**
	 * 聚合表达式，只能是+,-或者单个
	 */
	protected Object[] expresstion;
	
	/**
	 * @return the expresstion
	 */
	public Object[] getExpresstion() {
		return expresstion;
	}

	/**
	 * @param expresstion the expresstion to set
	 */
	public void setExpresstion(Object[] expresstion) {
		this.expresstion = expresstion;
	}


}