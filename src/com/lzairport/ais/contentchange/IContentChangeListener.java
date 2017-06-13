package com.lzairport.ais.contentchange;

import java.beans.PropertyChangeEvent;
import java.util.EventListener;

/**
 * 监听器接口
 * 用于注册在Provider上以接收Provider内容改变
 * @author ZhangYu
 * @since JDK1.6
 * @version 09.a 24/06/2014
 */

public interface IContentChangeListener extends EventListener {
	
	
	/**
	 * Provider内容改变后会调用此方法，用于处理内容变化的逻辑
	 * @param evt  内容变化事件
	 */
	void contentChange(PropertyChangeEvent evt)  throws Exception;

}
