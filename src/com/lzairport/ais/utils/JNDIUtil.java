package com.lzairport.ais.utils;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 利用JNDI来查找jndi资源的工具类
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */

public class JNDIUtil {
	//JNDI查找字符定义
	
	/**
	 *  JMS消息发送者的JNDI查找字符串
	 */
	public static final String JNDI_JmsProducer ="ejb:/AisEJB//JmsProducer!com.lzairport.ais.jms.ILocalJmsProducer";
	/**
	 * Notice通知者的JNDI查找字符串
	 */
	public static final String JNDI_Notice="ejb:/AisEJB//Notice!com.lzairport.ais.models.listener.INotice";
	/**
	 *  ModelRelationDao实体关联类DAO的JNDI查找字符串
	 */
	public static final String JNDI_ModelRelationDao="ejb:/AisEJB//ModelRelationDao!com.lzairport.ais.dao.IModelRelationDao";
	
	
	/**
	 * JMS连接工厂
	 */
    public static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    /**
     *  订阅分发Telex 的JMS主题
     */
	public static final String Ais_App_Topics = "jms/ais/topics/app";
	
	
	private static final String INITIAL_EJB_CONTEXT_FACTORY="org.jboss.ejb.client.naming";

	
	
/**
 * 查找EJB的方法	
 * @param jndiStr JNDI查找字符串
 * @return 相应的EJB
 */
	@SuppressWarnings("unchecked")
	public static <T> T lookupEJB(String jndiStr){
		 
		T jndiObject = null;  
		Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES,  INITIAL_EJB_CONTEXT_FACTORY);
		 try {
			Context context = new InitialContext(properties);
			jndiObject = 	 (T) context.lookup(jndiStr);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jndiObject;
		
	}
	
	
	
/**
 * 查找Jndi的对象方法	
 * @param jndiStr JNDI查找字符串
 * @return 对应的Jndi对象
*/

	@SuppressWarnings("unchecked")
	public static <T> T lookupJndi(String jndiStr){
		T jndiObject = null;  
		Properties properties = new Properties();
		
		try {
			Context context = new InitialContext(properties);
			jndiObject = 	 (T) context.lookup(jndiStr);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jndiObject;
		
	}

}
