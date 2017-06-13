package com.lzairport.ais.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 实体关联的实体类
 * 用于相关实体对象变化后关联实体的刷新
 * @author ZhangYu
 * @version 0.9a 27/06/14
 * @since JDK1.6
 *
 */

@Entity
public class ModelRelation extends DefaultEntity implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID ="id";
	
	public static String MODELNAME = "modelName";
	
	public static String RELATIONMODELNAME = "relationModelName";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		
	
	private String modelName;
	
	private String relationModelName;

	
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

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the relationModelName
	 */
	public String getRelationModelName() {
		return relationModelName;
	}

	/**
	 * @param relationModelName the relationModelName to set
	 */
	public void setRelationModelName(String relationModelName) {
		this.relationModelName = relationModelName;
	}

}
