package com.lzairport.ais.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 * 利用对象反射技术的工具类
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */



public class ObjectMethodUtil {
	
	
	/**
	 *   BeanUitls的自定义注册器注册标志
	 */
	private static boolean convertRegister = false; 
	
	
	
	
	/**
	 * 获取成员变量的Read方法的字符串
	 * @param fieldname 字段名
	 * @return Read方法的字符串
	 */
	private static String getReadMethodName(String fieldname){
		return "get" + fieldname.substring(0, 1).toUpperCase()
				+ fieldname.substring(1);
	}
	
	
	/**
	 * 获取成员变量的Set方法的字符串
	 * @param fieldname 字段名
	 * @returnSet方法的字符串 
	 */
	private static String getSetMethodName(String fieldname){
		return "set" + fieldname.substring(0, 1).toUpperCase()
				+ fieldname.substring(1);
	}
	/**
	 * 获取指定字段的get方法，追溯到父类
	 * @param clazz 实体类类型
	 * @param field 字段
	 * @return GET方法
	 */

	private static Method getReadModelMethod(Class<?> clazz,String field){
		Method m = null;
		try {
			m= clazz.getDeclaredMethod(getReadMethodName(field));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			if  (clazz.getSuperclass().getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
				m = getReadModelMethod(clazz.getSuperclass(), field);
			}else{
			e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * 获取指定字段的Set方法，追溯到父类
	 * @param clazz 实体类类型
	 * @param field 指定字段
	 * @return Set方法
	 */

	private static Method getSetModelMethod(Class<?> clazz,String field,Class<?> parmClass){
		Method m = null;
		try {

			m= clazz.getDeclaredMethod(getSetMethodName(field),parmClass);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			if  (clazz.getSuperclass().getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
				m = getSetModelMethod(clazz.getSuperclass(), field,parmClass);
			}else{
			e.printStackTrace();
			}
		}
		return m;
	}
	
	/**
	 * 获取数据对象所对应的字段的值
	 * @param obj 数据对象
	 * @param field 字段名
	 * @return 对应的字段的值
	 */
	public static Object getFieldObject(Object obj, String field) {
		Method m=null;

		Object returnobj = null;
			if (obj != null && field != null) {
				try {
					m = getReadModelMethod(obj.getClass(), field);
					returnobj = m.invoke(obj);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return returnobj;
	}
	
	/**
	 * 直接获取数据对象所对应的字段的值
	 * @param obj 数据对象
	 * @param field 字段名
	 * @return 对应的字段的值
	 */
	public static Object getDirectFieldObject(Object obj, String field) {
		Method m=null;

		Object returnobj = null;
			if (obj != null && field != null) {
				
				try {
					m= obj.getClass().getDeclaredMethod("get" +field);
					returnobj = m.invoke(obj);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return returnobj;
	}

	
	/**
	 * 设置数据对象所对应的字段的值
	 * @param obj 数据对象
	 * @param field 字段名
	 * @param parm 设置的数据
	 * @param parmClass 设置数据的类型
	 */
	public static void setFieldObject(Object obj, String field,Object parm,Class<?> parmClass) {
		Method m;
		if (obj != null && field != null) {
			try {
				if (parm.getClass() != parmClass){
					//如果设置数据的类型是字符串而字段对应的类型不为字符串
					if (parmClass == int.class){
						//如果字段对应类型是整型，转化为整型
						parm = Integer.parseInt(parm.toString());
					}
					
					if (parmClass == Date.class){
						//如果字段对应类型是日期，转换为日期类型
						parm =  DateTimeUtil.strToDate(parm.toString());
					}
					
					if(parmClass == double.class){
						parm = Double.parseDouble(parm.toString());
					}
					
					if(parmClass == boolean.class){
						//如果字段对应类型是布尔型，转换为布尔型
						parm = Boolean.valueOf(parm.toString());
					}
					
				}
				m = getSetModelMethod(obj.getClass(), field,parmClass);
				m.invoke(obj,parm);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				System.out.println(field+" "+parm+" "+parmClass+parm.getClass());
				//e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * 获取类对应字段的类型
	 * @param clazz 数据对象的类型
	 * @param field 字段名
	 * @return 对应字段的类型
	 */
	public static Class<?> getFieldType(Class<?> clazz, String field){
		Method method = null;
		method = getReadModelMethod(clazz, field);
		if (method != null){
			return method.getReturnType();
		}else {
			return null;
		}
	}
	

	/**
	 * 判断字段是否为成员变量
	 * @param obj 数据对象
	 * @param field 字段名
	 * @return 是或者否
	 */

	public static Boolean isField(Class<?> clazz, String field){

		if (clazz == null){

			return false;
		}else {
			try {
				clazz.getDeclaredMethod(getReadMethodName(field));
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				if  (clazz.getSuperclass().getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
					return isField(clazz.getSuperclass(),field);
				}else{
					return false;
				}
			} 
		}	
		return true;	
			
	}
	
/**
 * 返回实体类所有的属性包括父类	
 * @param clazz
 * @return 属性数组
 */
	public static Field[] getModelField(Class<?> clazz){
		List<Field> fields = new ArrayList<Field>(); 
		if (clazz.getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			if (clazz.getSuperclass() != null){
				//如果有父类，递归的继续查找
				fields.addAll(Arrays.asList(getModelField(clazz.getSuperclass()))); 
 			}
		}
		
		return fields.toArray(new Field[fields.size()]);
		
	}
	
	
	
	/**
	 * 获取对象字段的指定序号泛型的实际类型
	 * @param obj 对象
	 * @param fieldName  指定字段
	 * @return 泛型的实际类型
	 */
	public  static   Type getFieldGenericType(Object obj,String fieldName,Integer index){

		try {
			ParameterizedType type = null;
			type = (ParameterizedType)obj.getClass().
					getDeclaredField(fieldName).getGenericType();
			return  type.getActualTypeArguments()[index];
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 拷贝两个对象的相同名称的属性的方法
	 * @param dest 目标对象
	 * @param orig  原对象
	 * @throws Exception 出现赋值中的例外
	 */
	public static void copybean(Object dest,Object orig) throws Exception{
		
		if (!convertRegister){
			ConvertUtils.register(new DateConverter(null), java.util.Date.class);   
		}
		BeanUtils.copyProperties(dest, orig);
	}


	
}
