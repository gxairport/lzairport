package com.lzairport.ais.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * FileName      PayingPassengerVO.java
 * @Description  TODO 收费用户的值对象
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年2月19日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017年2月19日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


public class PayingPassengerVO implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private int id;

	private String name;
	
	private String ticketNo;
	
	private String cardNo;
	
	private int Price;
	
	private String typeName;
	
	private  String flightNO;
	
	private Date execDate;
	
	private String airlinesName;
	
	private String carrierName;
	


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the ticketNo
	 */
	public String getTicketNo() {
		return ticketNo;
	}

	/**
	 * @param ticketNo the ticketNo to set
	 */
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	/**
	 * @return the flightNO
	 */
	public String getFlightNO() {
		return flightNO;
	}

	/**
	 * @param flightNO the flightNO to set
	 */
	public void setFlightNO(String flightNO) {
		this.flightNO = flightNO;
	}

	/**
	 * @return the execDate
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
	public Date getExecDate() {
		return execDate;
	}

	/**
	 * @param execDate the execDate to set
	 */
	public void setExecDate(Date execDate) {
		this.execDate = execDate;
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the airlinesName
	 */
	public String getAirlinesName() {
		return airlinesName;
	}

	/**
	 * @param airlinesName the airlinesName to set
	 */
	public void setAirlinesName(String airlinesName) {
		this.airlinesName = airlinesName;
	}

	/**
	 * @return the carrierName
	 */
	public String getCarrierName() {
		return carrierName;
	}

	/**
	 * @param carrierName the carrierName to set
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	
	
	

}
