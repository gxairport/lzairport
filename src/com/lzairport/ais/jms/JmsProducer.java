package com.lzairport.ais.jms;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

/**
 * JMS生产者，负责把转换的XML的消息转发给各个子系统或者客户端
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 26/06/14
 */
@Stateless
public class JmsProducer implements IRemoteJmsProducer{

	
	@Resource(name="java:jboss/exported/jms/RemoteConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(name="java:jboss/exported/jms/ais/topics/app")
	private Topic ais_app_topics;
	
	private Connection connection;
	
	private Session session;
	
	private MessageProducer producer;
	
	private TextMessage message ;
	
	/**
	 * 初始化connection、session、producer、message 在对象构造函数后执行
	 */
	@PostConstruct
	public void init(){
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(ais_app_topics);
			message = session.createTextMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
			
	/**
	 * 关闭连接，在对象销毁之前执行
	 */
	@PreDestroy
	public void destroy(){
		try {
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void noticeContentChange(String xmlMessage) {
		try {
			message.setText(xmlMessage);
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	

}
