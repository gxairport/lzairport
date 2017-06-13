package com.lzairport.ais.utils;



/**
 * 事件代码定义表
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */


public class EventCodeUtil {
	//用于XML节点的字符串
	/**
	 *   XML根节点 
	 */
	public final static String Root="Event";
	
	/**
	 * XML事件属性节点
	 */
	public final static String Eventproperty="Property";
	
	/**
	 * XML内容节点
	 */
	public final static String EventContent="Content";
	
	/**
	 * XML实体类变化事件节点
	 */
	public final static String EntityChangeEvent="EntityChangeEvent";
	
	/**
	 * 系统事件节点
	 */
	public final static String EventSystem="AisSystemEvent";
	
	
	//串口事件定义
	/**
	 * 串口进事件
	 */
	public final static String ComInEvent="ComIn";
	/**
	 * 串口出事件
	 */
	public final static String ComOutEvent="ComOut";
	
	
	//实体变化事件定义
	/**
	 *  实体类新增事件
	 */
	public final static String ModelsAdd="ModelAdd";
	/**
	 *  实体类删除事件
	 */
	public final static String ModelsRemove="ModelRemove";
	/**
	 *  实体类更新事件
	 */
	public final static String ModelsUpdate="ModelUpdate";
	/**
	 *  实体类刷新事件
	 */
	public final static String ModelsRefresh="ModelRefresh";

	
	
	//Eterm事件定义
	/**
	 * Eterm客户端数据事件
	 */
	public final static String EventClientDataIn = "ClientDataIn";
	/**
	 * Eterm服务器端事件
	 */
	public final static String EventEtermDataIn = "EtermDataIn";

	/**
	 * 报文产生事件
	 */
	public final static String EventTelexSave ="TelexSave";
	
	
	
	
	/**
	 * 昨日系统运营结束，系统运营转换到当天事件
	 */
	public final static String EventAisDayEnd="AisDayEnd";
	
	
}
