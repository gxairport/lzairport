package com.lzairport.ais.models.aodb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.lzairport.ais.models.DefaultEntity;

/**
 * 区域属性实体类<p>
 * 如：国内，国际，区域
 * @author ZhangYu
 * @version 0.9a 19/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class AreaAttribute extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	public static String ID = "id";
	public static String CODE="code";
	public static String CNSHORTNAME = "cnShortName";
	public static String ENSHORTNAME = "enShortName";	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	@Id
	@Column(length=3)
	private String code;
	
	
	@Column(length=16)
	private String enShortName;
	
	
	@Column(length=16)
	private String cnShortName;
	

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		this.id =(Integer) id;
	}

	/**
	 * @return the enShortName
	 */
	public String getEnShortName() {
		return enShortName;
	}

	/**
	 * @param enShortName the enShortName to set
	 */
	public void setEnShortName(String enShortName) {
		this.enShortName = enShortName;
	}


	/**
	 * @return the cnShortName
	 */
	public String getCnShortName() {
		return cnShortName;
	}

	/**
	 * @param cnShortName the cnShortName to set
	 */
	public void setCnShortName(String cnShortName) {
		this.cnShortName = cnShortName;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.cnShortName;
	}

	/**
	 * 
	 */
	@Override
	public Object getIdValue() {
		// TODO Auto-generated method stub
		return this.getCode();
	}

	
	

}
