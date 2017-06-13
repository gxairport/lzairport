package com.lzairport.ais.models.aodb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.lzairport.ais.models.DefaultEntity;


/**
 * 付费服务类型的实体类
 * @author ZhangYu
 * @version 0.9a 28/09/14
 * @since JDK 1.6
 *
 */

@Entity
public class PayPrice extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	public static String  NAME = "name";
	
	public static String  PRICE="Price";
	public static String  CODE = "code";
	public static String ID ="id";
	public static String  AIRLINES="airlines";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		
	
	@Column(length=3)
	private String code;
	
	@Column(length=8)
	private String name;
	
	@ManyToOne
	private Airlines airlines;
	
	
	private int Price;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

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

	/**
	 * @return the price
	 */
	public int getPrice() {
		return Price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		Price = price;
	}

	/**
	 * @return the airlines
	 */
	public Airlines getAirlines() {
		return airlines;
	}

	/**
	 * @param airlines the airlines to set
	 */
	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

	
	
}
