

package com.lzairport.ais.dao.impl;

import java.io.Serializable;
import java.util.List;
import com.lzairport.ais.models.statistics.AggregationColumnField;
import com.lzairport.ais.models.statistics.GroupField;


/**
 * 
 * 查询条件类
 * @author ZhangYu
 * version 0.9a 28/07/14
 * @since JDK 1.6
 * 
 */

public class QueryConditions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 条件表达式
	 */
	private Object[] expresstion;
	
	/**
	 *  排序字段
	 */
	private AisOrder[] orders;
	
	
	/**
	 *  分页第一个记录标号
	 */
	private int first;
	
	/**
	 * 分页最大标号
	 */
	private int max;

	/**
	 *  连接一对多的标识符
	 */
	private String fetchOneToMany;
	
	/**
	 *  连接多对一的标识符
	 */
	private String fetchManyToOne;
	
	
	/**
	 *  分组字段
	 */
	private List<GroupField> groupFields;
	
	/**
	 *  聚合字段
	 */
	private List<AggregationColumnField> aggregationFields;
	
	
	
	
	

	
	
	
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

	




	/**
	 * @return the orders
	 */
	public AisOrder[] getOrders() {
		return orders;
	}




	/**
	 * @param orders the orders to set
	 */
	public void setOrders(AisOrder[] orders) {
		this.orders = orders;
	}




	/**
	 * @return the first
	 */
	public int getFirst() {
		return first;
	}




	/**
	 * @param first the first to set
	 */
	public void setFirst(int first) {
		this.first = first;
	}




	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}




	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}




	/**
	 * @return the fetchOneToMany
	 */
	public String getFetchOneToMany() {
		return fetchOneToMany;
	}




	/**
	 * @param fetchOneToMany the fetchOneToMany to set
	 */
	public void setFetchOneToMany(String fetchOneToMany) {
		this.fetchOneToMany = fetchOneToMany;
	}




	/**
	 * @return the fetchManyToOne
	 */
	public String getFetchManyToOne() {
		return fetchManyToOne;
	}




	/**
	 * @param fetchManyToOne the fetchManyToOne to set
	 */
	public void setFetchManyToOne(String fetchManyToOne) {
		this.fetchManyToOne = fetchManyToOne;
	}




	/**
	 * @return the groupFields
	 */
	public List<GroupField> getGroupFields() {
		return groupFields;
	}




	/**
	 * @param groupFields the groupFields to set
	 */
	public void setGroupFields(List<GroupField> groupFields) {
		this.groupFields = groupFields;
	}




	/**
	 * @return the aggregationFields
	 */
	public List<AggregationColumnField> getAggregationFields() {
		return aggregationFields;
	}




	/**
	 * @param aggregationFields the aggregationFields to set
	 */
	public void setAggregationFields(List<AggregationColumnField> aggregationFields) {
		this.aggregationFields = aggregationFields;
	}

	
}
