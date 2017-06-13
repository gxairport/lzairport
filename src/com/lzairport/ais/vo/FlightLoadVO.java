package com.lzairport.ais.vo;

import java.io.Serializable;

/**
 * 
 * FileName      FlightLoadDTO.java
 * @Description  航班载量实体的值对象
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015年10月2日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015年10月2日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

public class FlightLoadVO implements Serializable{
	
	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

	private String  depAP_Id;
	
	private String arrAP_Id;
	
	private int adult;
	
	private int chd;
	
	private int inf;
	
	private int goods;
	
	private int mail;
	
	private int luggage;

	/**
	 * @return the depAP_Id
	 */
	public String getDepAP_Id() {
		return depAP_Id;
	}

	/**
	 * @param depAP_Id the depAP_Id to set
	 */
	public void setDepAP_Id(String depAP_Id) {
		this.depAP_Id = depAP_Id;
	}

	/**
	 * @return the arrAP_Id
	 */
	public String getArrAP_Id() {
		return arrAP_Id;
	}

	/**
	 * @param arrAP_Id the arrAP_Id to set
	 */
	public void setArrAP_Id(String arrAP_Id) {
		this.arrAP_Id = arrAP_Id;
	}

	/**
	 * @return the adult
	 */
	public int getAdult() {
		return adult;
	}

	/**
	 * @param adult the adult to set
	 */
	public void setAdult(int adult) {
		this.adult = adult;
	}

	/**
	 * @return the chd
	 */
	public int getChd() {
		return chd;
	}

	/**
	 * @param chd the chd to set
	 */
	public void setChd(int chd) {
		this.chd = chd;
	}

	/**
	 * @return the inf
	 */
	public int getInf() {
		return inf;
	}

	/**
	 * @param inf the inf to set
	 */
	public void setInf(int inf) {
		this.inf = inf;
	}

	/**
	 * @return the goods
	 */
	public int getGoods() {
		return goods;
	}

	/**
	 * @param goods the goods to set
	 */
	public void setGoods(int goods) {
		this.goods = goods;
	}

	/**
	 * @return the mail
	 */
	public int getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(int mail) {
		this.mail = mail;
	}

	/**
	 * @return the luggage
	 */
	public int getLuggage() {
		return luggage;
	}

	/**
	 * @param luggage the luggage to set
	 */
	public void setLuggage(int luggage) {
		this.luggage = luggage;
	}

	

}
