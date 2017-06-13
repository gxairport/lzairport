package com.lzairport.ais.tablecolor;

import org.eclipse.swt.graphics.RGB;

import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 返回表格背景颜色和字体颜色的建造工厂
 * @author ZhangYu
 * version 0.9a 27/06/14
 * @since JDK 1.6
 */


public class TableColorFactory {
	

	/**
	 * 根据传入的对象实际类型返回一个AisRGB对象
	 * @param object 传入的实体对象
	 * @return 实体对象所对应的颜色AisRGB
	 */
	
	private  static  AisRGB getAisGRB(Object object){
		AisRGB  aisRGB = null;
		//根据实体对象查找AisRGb的序号
		int index = SYS_VARS.RGBInClassName.indexOf(object.getClass().getName());
		if (index != -1){
			try {
				//根据序号查找对应的ITableColor类名，并创建对象	
				 aisRGB = ((ITableColor)Class.forName(SYS_VARS.RGBOutClassName.get(index)).newInstance()).getRgbObj(object);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		return aisRGB;
	}
	
	/**
	 * 返回表格背景颜色
	 * @param object 传入的实体对象
	 * @return 实体对象所对应的背景颜色AisRGB
	 */
	public static RGB getTableBKColor(Object object){

		AisRGB rgb = getAisGRB(object);
		if (rgb != null) {
			return rgb.getBkRGB();
		}
		return null;
	}
	
	
	/**
	 * 返回表格字体颜色
	  * @param object 传入的实体对象
	 * @return 实体对象所对应的字体颜色AisRGB
	 */
	public static RGB getTableFRColor(Object object){

		AisRGB rgb = getAisGRB(object);
		if (rgb != null) {
			return rgb.getFrRGB();
		}
		return null;
	}

}
