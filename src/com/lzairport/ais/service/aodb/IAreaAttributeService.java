package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.service.IService;


/**
 * 区域属性实体类的Service接口
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IAreaAttributeService extends IService<Integer, AreaAttribute> {
	
	/**
	 * 
	 * @Description: 获取国内性质
	 * @return 国内性质
	 */
	public AreaAttribute getDomAttribute();
	
	/**
	 * 
	 * @Description: 获取国际性质
	 * @return 国际性质
	 */
	public AreaAttribute getIntAttribute();
	
	/**
	 * 
	 * @Description: 获取地区性质
	 * @return 地区性质
	 */
	public AreaAttribute getRegAttribute();

}
