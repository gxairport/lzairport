package com.lzairport.ais.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 显示分组的实体类
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Entity
public class ViewGroup extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID="id";
	
	public static String NAME="name";
	
	public static String MODELCLASSNAME="modelClassName";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String modelClassName;
	
	
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		this.id = (Integer) id;
	}	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelClassName() {
		return modelClassName;
	}

	public void setModelClassName(String modelClassName) {
		this.modelClassName = modelClassName;
	}

	

}
