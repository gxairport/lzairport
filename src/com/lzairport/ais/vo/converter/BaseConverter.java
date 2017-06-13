/**
 * 
 */
package com.lzairport.ais.vo.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import com.lzairport.ais.utils.AisBeanUtils;
import com.lzairport.ais.utils.SYS_VARS.ConverterFlag;

/**
 * FileName      BaseConverter.java
 * @Description  Vo对象和Po对象转换的基类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015年10月2日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015年10月2日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
public abstract class BaseConverter<E, V>  implements IConverter<E,V> {
	
	protected Class<V> voClazz;
	
	protected Class<E> entityClazz;
	
	
	
	
	@SuppressWarnings("unchecked")
	public BaseConverter() {
		ParameterizedType  type  = (ParameterizedType)this.getClass().getGenericSuperclass();
		entityClazz = (Class<E>) type.getActualTypeArguments()[0];
		voClazz     = (Class<V>) type.getActualTypeArguments()[1];
	}

	@Override
	public V getVOject(E entity) throws Exception{
		
		V vo = voClazz.newInstance();
		copyProperties(vo, entity, ConverterFlag.PO2VO);
		return vo;
		
	}
	
	@Override
	public E getEntity(V vo) throws Exception{
		E   entity = findEntity(vo);
		if (entity == null){
			entity = entityClazz.newInstance();
		}
		copyProperties(vo, entity, ConverterFlag.VO2PO);
		return entity;
	}

	
	
	
	 /** 
     * 值对象与域对象之间属性复制 
     * @param dto    值对象 
     * @param domain 域对象 
     * @param flag   复制方向 
	 * @throws Exception 
     */  
    protected void copyProperties(V dto, E domain, ConverterFlag flag) throws Exception {  
    	
        switch (flag) {  
  
            case PO2VO:  
                copySameProperties(dto, domain);  
                copyDiffPropertiesFromPO2VO(dto, domain);  
                break;  
  
            case VO2PO:  
                copySameProperties(domain, dto);  
                copyDiffPropertiesFromVO2PO(domain, dto);  
                break;  
  
            default:  
                break;  
        }  
  
    }  
  
    /** 
     * 同名属性复制 
     *  
     * @param target 目标对象 
     * @param source 来源对象 
     * @throws Exception 
     * @throws InvocationTargetException 
     */  
    protected void copySameProperties(Object target, Object source) throws  Exception {  
  
         AisBeanUtils.copyProperties(target, source);  
    }  
  
    protected abstract E findEntity(V vo);
    
    /** 
     * VO非同名属性复制到PO属性 
     * @param target  域对象 
     * @param source  值对象 
     * @throws Exception 
     */  
    public abstract void copyDiffPropertiesFromVO2PO(E target, V source) throws Exception;  
  
    /** 
     * PO非同名属性复制到VO属性 
     * @param target  值对象 
     * @param source  域对象 
     * @throws Exception 
     */  
    public abstract void copyDiffPropertiesFromPO2VO(V target, E source) throws Exception;  
  	
	

}
