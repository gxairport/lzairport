package com.lzairport.ais.jms;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.utils.EventCodeUtil;
import com.lzairport.ais.utils.XMLUtil;

/**
 *	内容改变时通知各客户端的接口的实现类 
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 26/06/14
 */

@Stateless
public class Notice  implements INotice{
	

	@EJB
	private IRemoteJmsProducer jmsProducer;



	@Override
	public void changeNotice(String eventCode,String property, Object obj) {
		String message = null;
		
		//将发生变化的实体类的Id,类名写入记录的类中
		ChangeEntityInfo entityInfo = new ChangeEntityInfo();
		entityInfo.setClazz(obj.getClass());
		entityInfo.setId(((DefaultEntity)obj).getId());
		
		// 生成Xml消息以供jms消费者传递消息
		Document document = DocumentHelper.createDocument();
		//根节点
		Element  rootElement = document.addElement(EventCodeUtil.Root);
		//事件代码节点  如实体类
		rootElement.addElement(EventCodeUtil.Eventproperty).setText(eventCode);
		Element  eventElement = rootElement.addElement(eventCode);
		//事件类型节点 如新增 删除
		eventElement.addElement(EventCodeUtil.Eventproperty).setText(property);
		//发生变化的实体数据节点，将实体类转化为Xml格式
		eventElement.addElement(EventCodeUtil.EventContent).add(XMLUtil.objectToElement(entityInfo));
		
		message = rootElement.asXML();
		jmsProducer.noticeContentChange(message);
		
		
	}

}
