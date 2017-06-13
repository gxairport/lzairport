package com.lzairport.ais.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML的工具类
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */
public class XMLUtil {
	
	private static XStream xStream = new XStream(new DomDriver());
	
	private static Document document;
	

	
	/**
	 * 查找Element的节点
	 * @param xml  XML文本
	 * @param keyText 关键字符串 
	 * @return keyText所对应的Element
	 */
	private static Element findElement(String xml,String keyText){
		try {
			document = DocumentHelper.parseText(xml);
			Element rootElement = document.getRootElement();
			String eventText = rootElement.element(EventCodeUtil.Eventproperty).getText();
			Element element = rootElement.element(eventText);
			element = element.element(keyText);
			return element;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	
	/**
	 * 对象转换为XML的Element
	 * @param object 输入对象
	 * @return XML的Element
	 */
	public static Element objectToElement(Object object){
		try {
			
			document = DocumentHelper.parseText(xStream.toXML(object));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document.getRootElement();
		
	}

	/**
	 * 将XML文本转换为对象
	 * @param xml XML文本
	 * @return 对象
	 */
	public static Object documentGetObject (String xml){
		//取得内容节点，然后转换为对象
		Element element = findElement(xml, EventCodeUtil.EventContent);
		element = (Element) element.elementIterator().next();
		return xStream.fromXML(element.asXML());
			 
	}
	
	/**
	 * 获取XML文本的属性节点的字符串
	 * @param xml XML文本
	 * @return 属性节点的字符串
	 */
	public static String documentGetProperty(String xml){
		Element element = findElement(xml, EventCodeUtil.Eventproperty);
		return element.getText();
		
	}

	
}
