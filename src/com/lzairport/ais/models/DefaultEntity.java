package com.lzairport.ais.models;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.lzairport.ais.jms.DefaultEntityListeners;

/**
 * 实体类的需要继承的父类
 * 主要注册 Ejb实体类监听器，如果数据发生改变做相应的通知
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@MappedSuperclass
@EntityListeners(DefaultEntityListeners.class)
public abstract class DefaultEntity {

	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID="id";
	
	/**
	 * @return the id
	 */
	public abstract Object getId();

	/**
	 * @param id the id to set
	 */
	public abstract void setId(Object id); 
	
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	public Object getIdValue(){
		return this.getId();
	}

	
	/**
	 * 重写的equals方法
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (DefaultEntity.class.isAssignableFrom(obj.getClass())&&!this.getIdValue().equals(0)){
			//如果obj和Entity类型一致或者是其子类，比较两个Id
			//如果ID 为0 说明该数据没有进入数据库，无法用ID进行比较
			return (this.getIdValue() .equals(((DefaultEntity)obj).getIdValue()));
		}
		//否则用父类的方法进行比较，一般是比较内存地址
		return super.equals(obj);
	}

	
	
	

}
