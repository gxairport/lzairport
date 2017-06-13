package com.lzairport.ais.models.statistics;

import com.lzairport.ais.utils.SYS_VARS.AggregationFieldType;

/**
 * 
 * FileName      AggregationColumnField.java
 * @Description  聚合列字段，用来定义需要对数据分组的字段，实际上去查询数据的字段
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年2月27日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年2月27日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

public class AggregationColumnField extends ColumnField {


	private static final long serialVersionUID = 1L;
	
	
	
	public static String AGGREGATIONTYPE ="aggregationType";
	
	public static String COMPARE ="compare"; 
	
	public static String WHENCONDITION = "whenCondition";
	
	/**
	 * 聚合的类型
	 */
	private AggregationFieldType aggregationType;
	
	
	
	/**
	 * 比较标志，选定日期年+Compare的值
	 */
	private boolean compare;
	
	
	/**
	 * 聚合条件
	 */
	private Object[] whenCondition;
	

	/**
	 * @return the aggregationType
	 */
	public AggregationFieldType getAggregationType() {
		return aggregationType;
	}

	/**
	 * @param aggregationType the aggregationType to set
	 */
	public void setAggregationType(AggregationFieldType aggregationType) {
		this.aggregationType = aggregationType;
	}



	/**
	 * @return the compare
	 */
	public boolean getCompare() {
		return compare;
	}

	/**
	 * @param compare the compare to set
	 */
	public void setCompare(boolean compare) {
		this.compare = compare;
	}

	/**
	 * @return the whenCondition
	 */
	public Object[] getWhenCondition() {
		return whenCondition;
	}

	/**
	 * @param whenCondition the whenCondition to set
	 */
	public void setWhenCondition(Object[] whenCondition) {
		this.whenCondition = whenCondition;
	}



	

}
