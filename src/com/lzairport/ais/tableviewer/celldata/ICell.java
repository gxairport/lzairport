package com.lzairport.ais.tableviewer.celldata;

import com.lzairport.ais.tableviewer.header.HeaderItem;


/**
 *  取得表格数据的接口
 * @author ZhangYu
 * version 0.9a 08/11/14
 * @since JDK 1.6
 */

public interface ICell {
	
	/**
	 * 返回数据对象字段的值，如有子字段的话返回子字段的对象
	 * @param element 数据对象
	 * @param HeaderItem 对应的表头对象
	 * @return 数据值
	 */
	public Object getData(Object element,HeaderItem field);
	
	public void setData(Object element,HeaderItem field,Object data,Class<?> parmClass);
	
}
