package com.lzairport.ais.jms;

import javax.ejb.Remote;

/**
 * 内容改变时通知各客户端的接口
 * @author ZhangYu
 * @since JDK 1.6 
 * @version 0.9a 26/06/14 
 * 
* */

@Remote
public interface INotice {

	/**
	 * 内容改变通知
	 * @param eventCode 事件代码
	 * @param property 改变类型 如：新增、删除、更新
	 * @param entity 实体类
	*/
	public abstract void changeNotice(String eventCode,String property, Object entity);

	
	
}