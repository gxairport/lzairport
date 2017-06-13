package com.lzairport.ais.tableviewer.base;

import java.io.Serializable;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.service.IService;

/**
 * 表格的数据服务类，用于设置表格的Input输入设置
 * @author ZhangYu
 * version 0.9a 28/07/14
 * @since JDK 1.6
 * 
 */

public class TableViewerService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 输入查询的条件
	 */
	private QueryConditions conditions;
	
	/**
	 * 实际的数据库的Service层
	 * 
	 */

	private IService<Integer,? extends Object> service;
	
	/**
	 * @return the service
	 */

	public IService<Integer,? extends Object> getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */

	public void setService(IService<Integer, ? extends Object> service) {
		this.service = service;
	}

	/**
	 * @return the conditions
	 */
	public QueryConditions getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(QueryConditions conditions) {
		this.conditions = conditions;
	}


	
	
	
}
