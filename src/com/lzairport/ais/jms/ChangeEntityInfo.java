package com.lzairport.ais.jms;

/**
 * 记录改变实体类ID和类名的对象，用于JMS消息中通知各视图更新
 * @author ZhangYu
 * @version 0.9a 05/26/15
 * @since JDK 1.6
 *
 */



public class ChangeEntityInfo   {
	
	private Object id;
	
	private Class<?> clazz;

	/**
	 * @return the id
	 */
	public Object getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Object id) {
		this.id = id;
	}

	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	
	

}
