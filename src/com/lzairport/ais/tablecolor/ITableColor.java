package com.lzairport.ais.tablecolor;

import com.lzairport.ais.models.AisRGB;

/**
 * 表格颜色接口
 * @author ZhangYu
 * version 0.9a 27/06/14
 * @since JDK 1.6
 */

public interface ITableColor {

	/**
	 * 返回实体类的颜色对象 AisRGB
	 * @param object
	 * @return 颜色对象 AisRGB
	 */
	public abstract AisRGB getRgbObj(Object object);

}