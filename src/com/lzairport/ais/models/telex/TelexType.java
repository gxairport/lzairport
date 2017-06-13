package com.lzairport.ais.models.telex;

import java.io.Serializable;

import javax.persistence.*;

import com.lzairport.ais.models.AisRGB;

/**
 * 电报类型的实体类，比如PLN,FPL,ARR等
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK1.6
 *
 */
@Entity
public class TelexType  extends AisRGB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID="id";
	
	public static String KEYWORD="keyWord";
	
	public static String NAME="name";
	
	public static String ANALYTICYCLASSNAME="analyticyClassName";
	
	public static String ALLOWTYPE="allowType";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String keyWord;
	
	private String analyticyClassName;
	 
	
	@ManyToOne
	private AllowType allowType;
	/**
	 * @return the allowType
	 */
	public AllowType getAllowType() {
		return allowType;
	}
	/**
	 * @param allowType the allowType to set
	 */
	public void setAllowType(AllowType allowType) {
		this.allowType = allowType;
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
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}
	/**
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * @return the analyticyClassName
	 */
	public String getAnalyticyClassName() {
		return analyticyClassName;
	}
	/**
	 * @param analyticyClassName the analyticyClassName to set
	 */
	public void setAnalyticyClassName(String analyticyClassName) {
		this.analyticyClassName = analyticyClassName;
	}
	
	@Override
	public String toString() {
		return name;
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
