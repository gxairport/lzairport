package com.lzairport.ais.service.telex;

import javax.ejb.Remote;

import com.lzairport.ais.models.telex.AnalyticyType;
import com.lzairport.ais.service.IService;


/**
 * 电报解析状态Service接口，用以返回定义好的电报解析状态
 * @author ZhangYu
 * @version 0.9a 13/12/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IAnalyticyTypeService extends IService<Integer,AnalyticyType> {
	
	
	/**
	 * 
	 * @return 电报未解析状态
	 */
	public AnalyticyType getUnAnalyticedTelexType();
	
	/**
	 * 
	 * @return 电报解析完成状态
	 */
	public AnalyticyType getAnalyticedTelexType();
	
	/**
	 * 
	 * @return 电报解析出错状态
	 */
	public AnalyticyType getErrAnalyticyType();
	
	
}
