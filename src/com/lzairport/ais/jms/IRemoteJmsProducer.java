package com.lzairport.ais.jms;

import javax.ejb.Remote;

/**
 * 消息生产者接口
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 26/06/14
 * 
 */

@Remote
public interface IRemoteJmsProducer {
	
	/**
	 * 发送jms消息
	 * @param xmlMesaage  XML格式的消息
	 */
	public void noticeContentChange(String xmlMesaage);
}