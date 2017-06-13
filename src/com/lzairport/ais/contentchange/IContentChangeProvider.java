package com.lzairport.ais.contentchange;


/**
 * 内容改变通知接口
 * 用于通知注册在自身的Listener内容改变
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 */


public interface IContentChangeProvider {
	
	/**
	 * 注册Listener的方法，将Listener加入通知列表里
	 * @param listener IPropertyChangeListener的接口
	 */
	public abstract void addContentChangeListener(
			IContentChangeListener listener);

	/**
	 * 注销Listener方法,将Listener从通知列表里删除
	 * @param listener
	 */
	public abstract void removeContentChangeListener(
			IContentChangeListener listener);

	/**
	 * Provider内容变化后调用方法
	 * 一般调用通知列表里的Listener的contentChange方法，用于通知各Listener内容发生变更
	 * @param property 消息类型（字符串型）
	 * @param obj 变化的对象
	 */
	public abstract void contentChangeInvoke(String property, Object newValue) ;


}
