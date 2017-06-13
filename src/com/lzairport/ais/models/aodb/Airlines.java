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
 * 航空公司实体类
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class Airlines extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	
	public static  String ID="id";
	public static  String CNFULLNAME = "cnFullname";
	public static  String CNSHORTNAME =  "cnShortName";
	public static  String CORPTWOCHARCODE = "corpTwoCharCode";
	public static  String ATTRIBUTE = "attribute";
	public static  String INFOPAGE = "infoPage";
	public static  String ENFULLNAME = "enFullname";
	public static  String ENSHORTNAME = "enShortName";
	public static  String CORPTHREECHARCODE = "corpThreeCharCode";
	public static  String REVADDRESS = "revAddress";
	
	
	
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Id
	@Column(length=2)
	private String corpTwoCharCode;
	
	@Column(length=3)
	private String corpThreeCharCode;
	
	private String icon;
	
	
	@Column(length=16)
	private String enShortName;
	
	
	@Column(length=64)
	private String enFullname;
	
	@Column(length=16)
	private String cnShortName;
	
	
	@Column(length=64)
	private String cnFullname;
	
	@Column(length=120)
	private String infoPage;
	
	@ManyToOne
	@JoinColumn(name="attribute")
	private AreaAttribute attribute;
	
	
	private String revAddress;
	
	
	
	
	


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
	 * @return the corpTwoCharCode
	 */
	public String getCorpTwoCharCode() {
		return corpTwoCharCode;
	}

	/**
	 * @param corpTwoCharCode the corpTwoCharCode to set
	 */
	public void setCorpTwoCharCode(String corpTwoCharCode) {
		this.corpTwoCharCode = corpTwoCharCode;
	}

	/**
	 * @return the corpThreeCharCode
	 */
	public String getCorpThreeCharCode() {
		return corpThreeCharCode;
	}

	/**
	 * @param corpThreeCharCode the corpThreeCharCode to set
	 */
	public void setCorpThreeCharCode(String corpThreeCharCode) {
		this.corpThreeCharCode = corpThreeCharCode;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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
	 * @return the enFullname
	 */
	public String getEnFullname() {
		return enFullname;
	}

	/**
	 * @param enFullname the enFullname to set
	 */
	public void setEnFullname(String enFullname) {
		this.enFullname = enFullname;
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
	 * @return the cnFullname
	 */
	public String getCnFullname() {
		return cnFullname;
	}

	/**
	 * @param cnFullname the cnFullname to set
	 */
	public void setCnFullname(String cnFullname) {
		this.cnFullname = cnFullname;
	}

	/**
	 * @return the infoPage
	 */
	public String getInfoPage() {
		return infoPage;
	}

	/**
	 * @param infoPage the infoPage to set
	 */
	public void setInfoPage(String infoPage) {
		this.infoPage = infoPage;
	}

	/**
	 * @return the attribute
	 */
	public AreaAttribute getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(AreaAttribute attribute) {
		this.attribute = attribute;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.cnShortName;
	}

	/**
	 * @return the revAddress
	 */
	public String getRevAddress() {
		return revAddress;
	}

	/**
	 * @param revAddress the revAddress to set
	 */
	public void setRevAddress(String revAddress) {
		this.revAddress = revAddress;
	}


	@Override
	public Object getIdValue() {
		
		return this.getCorpTwoCharCode();
	}





	
	
}
