package com.lzairport.ais.models.statistics;



/**
 * 
 * FileName      CumulativeColumnField.java
 * @Description  用于累计计算的列字段
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年2月27日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年2月27日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
public class CumulativeColumnField extends ColumnField {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	/**
	 * 需要对什么数据进行累计
	 */
	private AggregationColumnField aggregationColumnField;
	
	
	/**
	 * 限定的年份
	 */
	private String year;
	
	/**
	 * 是否需要进行比较
	 */
	private boolean compare;

	/**
	 * 进行年或者是月的比较的标识
	 */
	private String flag;

	/**
	 * @return the aggregationColumnField
	 */
	public AggregationColumnField getAggregationColumnField() {
		return aggregationColumnField;
	}

	/**
	 * @param aggregationColumnField the aggregationColumnField to set
	 */
	public void setAggregationColumnField(AggregationColumnField aggregationColumnField) {
		this.aggregationColumnField = aggregationColumnField;
	}

	/**
	 * @return the compare
	 */
	public Boolean getCompare() {
		return compare;
	}

	/**
	 * @param compare the compare to set
	 */
	public void setCompare(Boolean compare) {
		this.compare = compare;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
	

}
