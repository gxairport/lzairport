package com.lzairport.ais.tablecolor;

import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.models.telex.TelexBO;


/**
 * 返回TelexBo对象的表格颜色对象AisRGB
  *@author ZhangYu
 * version 0.9a 27/06/14
 * @since JDK 1.6
 */

public class TelexBkColor implements ITableColor {

	
	@Override
	public AisRGB getRgbObj(Object object) {
		// TODO Auto-generated method stub
		//根据TelexBo的处理结果来返回背景表格颜色
		return ((TelexBO)object).getAnalyticyType();
	}
}
