package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.lzairport.ais.models.IntIdEntity;


/**
 * 
 * FileName      Route.java
 * @Description  航线实体类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年4月5日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年4月5日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class RouteHX extends IntIdEntity {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

	public static  String ID        = "id";
	public static  String CODE      = "code";
	public static  String CNNAME    = "cnName";
	public static  String TRANSFER  = "transfer";
	public static  String ATTRIBUTE = "attribute";
	
	
	
	private String code;
	
	private String cnName;
	
	/**
	 *  是否是中转航班
	 */
	private boolean transfer;

	
	/**
	 * 属性
	 */
	@ManyToOne
	private AreaAttribute attribute; 


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
	 * @return the cnName
	 */
	public String getCnName() {
		return cnName;
	}

	/**
	 * @param cnName the cnName to set
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	public AreaAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(AreaAttribute attribute) {
		this.attribute = attribute;
	}
	
	
	
}
