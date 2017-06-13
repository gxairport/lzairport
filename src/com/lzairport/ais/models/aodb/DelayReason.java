package com.lzairport.ais.models.aodb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.lzairport.ais.models.DefaultEntity;


/**
 * 延误原因的实体类
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class DelayReason extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	public static String ID = "id";
	public static String ENDESCRIPTION = "enDescription";
	public static String CNSHORTNAME = "cnShortName";
	public static String CNDESCRIPTION = "cnDescription";
	public static String ENSHORTNAME = "enShortName";			
	public static String DELAYCATEGORY = "delayCategory";
	public static String ATMCODE = "atmCode";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		
	@ManyToOne(optional=false)
	@JoinColumn(name="category")
	private DelayCategory delayCategory;
	
	
	@Column(length=16)
	private String enShortName;
	
	private String enDescription;
	
	@Column(length=64)
	private String cnShortName;
	
	private String cnDescription;
	
	@Column(length=4)
	private String atmCode;
	
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
	 * @return the category
	 */
	public DelayCategory getDelayCategory() {
		return delayCategory;
	}

	/**
	 * @param category the category to set
	 */
	public void setDelayCategory(DelayCategory delayCategory) {
		this.delayCategory = delayCategory;
	}

	/**
	 * @return the atmCode
	 */
	public String getAtmCode() {
		return atmCode;
	}

	/**
	 * @param atmCode the atmCode to set
	 */
	public void setAtmCode(String atmCode) {
		this.atmCode = atmCode;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return cnShortName;
	}

	
	
	
}
