package com.lzairport.ais.tableviewer.celldata;

import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.DisPatchItemType;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.utils.SYS_VARS.Quarter;


/**
 * ICell的系统定义枚举类型的实现类 ，用来显示和设置枚举类型的数据，单例模式
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public class EnumCell implements ICell {
	
	private static StaticCell staticCell = StaticCell.getInstance();
	
	private static EnumCell instance = new EnumCell();
	
	private EnumCell(){
		
	}
	
	public static  EnumCell getInstance() {
		return instance;
	}
	
	/**
	 * 根据枚举类型返回所对应的表示字符--服务环节
	 * @param obj
	 * @return 表示字符串
	 */
	private Object getDisPatchItemTypeText(DisPatchItemType obj){
		return SYS_VARS.DisPatchItemTypeCn.get(obj.ordinal());
	}
	
	/**
	 * 根据对应的表示字符返回枚举类型--服务环节
	 * @param obj
	 * @return 表示字符串
	 */
	private Object getDisPatchItemTypedata(String parm){
		return DisPatchItemType.values()[SYS_VARS.DisPatchItemTypeCn.indexOf(parm)];
	}
	
	
	/**
	 * 根据枚举类型返回所对应的表示字符--航季
	 * @param obj
	 * @return 表示字符串
	 */
	private Object getQuarterText(Quarter obj){
		return SYS_VARS.QuarterCn.get(obj.ordinal());
	}

	/**
	 * 根据对应的表示字符返回枚举类型--服务环节
	 * @param obj
	 * @return 表示字符串
	 */
	private Object getQuarterdata(String parm){
		return Quarter.values()[SYS_VARS.QuarterCn.indexOf(parm)];
	}
	
	
	/**
	 * 根据枚举类型返回所对应的表示字符--进出
	 * @param obj
	 * @return 表示字符串
	 */
	private Object getOutInText(OutIn obj){
		return SYS_VARS.OutInCn.get(obj.ordinal());
	}
	
	/**
	 * 根据对应的表示字符返回枚举类型--服务环节
	 * @param obj
	 * @return 表示字符串
	 */
	private Object getOutIndata(String parm){
		return OutIn.values()[SYS_VARS.OutInCn.indexOf(parm)];
	}
	
	

	@Override
	public Object getData(Object element, HeaderItem field) {
		// TODO Auto-generated method stub
		
		Object enumObj = staticCell.getData(element, field);
		Object obj = null; 
		if (enumObj instanceof DisPatchItemType){
			obj =  getDisPatchItemTypeText((DisPatchItemType) enumObj);
		}else if (enumObj instanceof Quarter){
			obj = getQuarterText((Quarter) enumObj);
		}else if (enumObj instanceof OutIn){
			obj = getOutInText((OutIn) enumObj);
		}
		return obj;
	}
	
	

	@Override
	public void setData(Object element, HeaderItem field, Object parm,Class<?> parmClass) {
		// TODO Auto-generated method stub
		
		if (parmClass == DisPatchItemType.class){
			ObjectMethodUtil.setFieldObject(element, field.getEname(), 
					getDisPatchItemTypedata((String) parm), parmClass);
		}else if (parmClass == Quarter.class){
			ObjectMethodUtil.setFieldObject(element, field.getEname(), 
					getQuarterdata((String) parm), parmClass);
		}else if (parmClass == OutIn.class){
			ObjectMethodUtil.setFieldObject(element, field.getEname(), 
					getOutIndata((String) parm), parmClass);
		}
	}
	
	
	/**
	 * 返回枚举类型所对应的所有成员的中文显示
	 * @param element
	 * @param field
	 * @return 显示字符串数据
	 */
	public String[] enumCnText(Object element, HeaderItem field){
		
		Class<?> parmClass = ObjectMethodUtil.getFieldType(element.getClass(), field.getEname());
		String[] resultText = null;
		
		if (parmClass == DisPatchItemType.class){
			
			resultText = SYS_VARS.DisPatchItemTypeCn.toArray(new String[DisPatchItemType.values().length]);
			
		}else if (parmClass == Quarter.class){
			
		    resultText = SYS_VARS.QuarterCn.toArray(new String[Quarter.values().length]);
			
		}else if (parmClass == OutIn.class){
			
			resultText = SYS_VARS.OutInCn.toArray(new String[OutIn.values().length]);
		}
		return resultText;
	}

}
