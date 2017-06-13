package com.lzairport.ais.models.aodb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.lzairport.ais.models.DefaultEntity;


/**
 * 航班付费旅客抽象类
 * @author ZhangYu
 * @version 0.9a 02/09/14
 * @since JDK 1.6
 *
 */


@MappedSuperclass
public abstract class PayingPassenger extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	public static String  TICKETNO= "ticketNo";
	public static String  NAME = "name";
	public static String  PAYPRICE="payPrice";
	public static String  FLIGHT = "flight";
	public static String  CARDNO="cardNo";
	public static String ID ="id";
	public static String PRICE="price";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;			
	
	@Column(length=32)
	private String name;
	
	@Column(length=32)
	private String ticketNo;
	
	@Column(length=32)
	private String cardNo;
	
	@ManyToOne
	private PayPrice payPrice;
	
	
	private int price;
	
	
	/**
	 * 设置关联航班
	 * @return 关联航班实体
	 */
	public abstract Flight getFlight();

	/**
	 * 设置关联航班
	 * @param flight 关联航班实体
	 */
	public  abstract void  setFlight(Flight flight); 

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		if (id != null){
			this.id = (Integer) id;
		}
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
	 * @return the payPrice
	 */
	public PayPrice getPayPrice() {
		return payPrice;
	}

	/**
	 * @param payPrice the payPrice to set
	 */
	public void setPayPrice(PayPrice payPrice) {
		this.payPrice = payPrice;
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

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	
	
}
