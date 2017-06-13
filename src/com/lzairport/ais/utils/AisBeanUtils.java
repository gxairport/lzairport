package com.lzairport.ais.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;

/**
 * 
 * FileName      AisBeanUtils.java
 * @Description  自定义的类属性拷贝工具
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015年10月2日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015年10月2日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

public class AisBeanUtils extends BeanUtils {

	private AisBeanUtils(){
		
	}
	
	static {
		 // 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空  
        ConvertUtils.register(new SqlDateConverter(), java.util.Date.class);  
        // 注册util.date的转换器，即允许BeanUtils.copyProperties时的源目标的util类型的值允许为空  
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);   
	}
	
	public static void copyProperties(Object target, Object source)  
	        throws InvocationTargetException, IllegalAccessException {  
	  
	        BeanUtils.copyProperties(target, source);  
	}  
	

}
