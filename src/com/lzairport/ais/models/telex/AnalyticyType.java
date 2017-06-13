package com.lzairport.ais.models.telex;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.models.telex.AnalyticyType;

/**
 * 解析报文类型的实体类
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */


@Entity
public class AnalyticyType extends AisRGB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID="id";
	
	public static String NAME="name";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public Object getId() {
		return this.id;
	}
	@Override
	public void setId(Object id) {
		this.id = (Integer) id;
	}

}
