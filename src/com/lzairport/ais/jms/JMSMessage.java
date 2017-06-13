package com.lzairport.ais.jms;



import javax.annotation.PreDestroy;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.springframework.stereotype.Component;

import com.lzairport.ais.contentchange.ContentChangeProvider;
import com.lzairport.ais.utils.JNDIUtil;
import com.lzairport.ais.utils.XMLUtil;

/**
 * Jms消息消费者
 * 客户端用于接收Jms数据库变更信息，产生相应的事件通知各视图更新数据
 * @author ZhangYu
 * @version 0.9a 26/06/14
 * @since JDK 1.6
 *
 */


@Component
public class JMSMessage  extends ContentChangeProvider implements MessageListener {
	
	/**
	 *  jms连接
	 */
    private Connection connection;
    /**
     *  jms连接工厂
     */
    private ConnectionFactory connectionFactory;
    /**
     *  消息队列topic
     */
    private Topic topic;
    
	
    /**
     * 建立jms连接，开始消费jms消息
     */
    
	public void init (){
			//根据连接工厂找到消息队列topic
		    connectionFactory =  JNDIUtil.lookupJndi(JNDIUtil.DEFAULT_CONNECTION_FACTORY);
			topic =  JNDIUtil.lookupJndi(JNDIUtil.Ais_App_Topics);
			try {
				connection = connectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				//建立消息消费者并使连接启动
				MessageConsumer consumer = session.createConsumer(topic);
				consumer.setMessageListener(this);
				connection.start();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	/**
	 * 程序结束关闭jms连接
	 */
	
	@PreDestroy
	public void destroy() {
		try {
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  接收到jms进行处理消息
	 */

	@Override
	public void onMessage(Message msg) {
		try {
			//解析Xml数据变为实体对象entity及产生变化的操作类型property
			
			String xml =  ((TextMessage)msg).getText();
			String property = XMLUtil.documentGetProperty(xml);
			Object entityInfo = XMLUtil.documentGetObject(xml);
			//通知注册在自身的监听者 实体对象发生变化，做相应的改变
			contentChangeInvoke(property,entityInfo);

		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}

}
