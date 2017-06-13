package com.lzairport.ais.models.aodb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.lzairport.ais.models.DefaultEntity;


/**
 * 承运人实体类
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */
@Entity
public class Carrier extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	public static String  CNSHORTNAME = "cnShortName";
	public static String  CODE = "code";
	public static String  ID = "id";
	public static String  CNFULLNAME = "cnFullname";
	public static String  FAXNUMBER = "faxNumber";
	public static String  ADDRESS = "address";
	public static String  EMAIL = "email";
	public static String  AIRLINES = "airlines";
	public static String  ENFULLNAME = "enFullname";
	public static String  TELEPHONE = "telephone";
	public static String  ENSHORTNAME = "enShortName";	
	public static String  SETTLEMENTCODE = "settlementCode"; 
	public static  String PAYCODE  = "payCode";
	public static  String GOODSCHECK = "goodsCheck";
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Id
	@Column(length=3)
	private String code;

	/**
	 *  结算代码，结算收入用
	 */

	@Column(length=3)
	private String settlementCode;  
	
	/**
	 *  付款代码，结算收入用
	 */
	@Column(length=5)
	private String payCode;
	
	
	@ManyToOne
	@JoinColumn(name="airlines")
	private Airlines airlines;
	
	@Column(length=16)
	private String enShortName;
	
	
	@Column(length=64)
	private String enFullname;
	
	@Column(length=16)
	private String cnShortName;
	
	
	@Column(length=64)
	private String cnFullname;
	
	@Column(length=16)
	private String telephone;
	
	@Column(length=16)
	private String faxNumber;
	
	private String address;

	@Column(length=32)
	private String email;
	
	/**
	 * 货物判断标志，结算收入用
	 */
	private boolean goodsCheck;
	
	@Transient
	public String airlinesName;

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
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the settlementCode
	 */
	public String getSettlementCode() {
		return settlementCode;
	}

	/**
	 * @param settlementCode the settlementCode to set
	 */
	public void setSettlementCode(String settlementCode) {
		this.settlementCode = settlementCode;
	}

	/**
	 * @return the payCode
	 */
	public String getPayCode() {
		return payCode;
	}

	/**
	 * @param payCode the payCode to set
	 */
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	/**
	 * @return the goodsCheck
	 */
	public boolean isGoodsCheck() {
		return goodsCheck;
	}

	/**
	 * @param goodsCheck the goodsCheck to set
	 */
	public void setGoodsCheck(boolean goodsCheck) {
		this.goodsCheck = goodsCheck;
	}

	/**
	 * @return the airlinesName
	 */
	public String getAirlinesName() {
		if (this.getAirlines() != null){
			return this.getAirlines().getCnShortName();
		}else{
			return null;
		}
	}

	/**
	 * @param airlinesName the airlinesName to set
	 */
	public void setAirlinesName(String airlinesName) {
		this.airlinesName = airlinesName;
	}


	@Override
	public Object getIdValue() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.cnShortName;
	}
	
	
	
}
