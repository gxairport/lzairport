package com.lzairport.ais.models.aodb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 飞机任务的实体类
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class FlightTask extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */		
	public static String  ID ="id";
	public static String  ENDESCRIPTION = "enDescription";
	public static String  CNSHORTNAME = "cnShortName";
	public static String  CODE = "code";
	public static String  CNDESCRIPTION = "cnDescription";
	public static String  ENSHORTNAME = "enShortName";
	public static String  KIND ="kind";
	public static String  GENUS="genus";
	
	@Column(name="id")
	private int id;
	
	
	@Id
	@Column(length=3)
	private String code;

	@Column(length=8)
	private String genus;

	@Column(length=8)
	private String kind;
	
	@Column(length=16)
	private String enShortName;
	
	private String enDescription;
	
	@Column(length=16)
	private String cnShortName;
	
	private String cnDescription;

	
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
	 * @return the enDescription
	 */
	public String getEnDescription() {
		return enDescription;
	}

	/**
	 * @param enDescription the enDescription to set
	 */
	public void setEnDescription(String enDescription) {
		this.enDescription = enDescription;
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
	 * @return the cnDescription
	 */
	public String getCnDescription() {
		return cnDescription;
	}

	/**
	 * @param cnDescription the cnDescription to set
	 */
	public void setCnDescription(String cnDescription) {
		this.cnDescription = cnDescription;
	}

	/**
	 * @return the genus
	 */
	public String getGenus() {
		return genus;
	}

	/**
	 * @param genus the genus to set
	 */
	public void setGenus(String genus) {
		this.genus = genus;
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}


	@Override
	public Object getIdValue() {
		// TODO Auto-generated method stub
		return this.getCode();
	}


	
}
