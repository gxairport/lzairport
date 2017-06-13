package com.lzairport.ais.models.aodb;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 航班配载的抽象类,动态，计划，历史都继承它
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@MappedSuperclass
public abstract class FlightLoad extends DefaultEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID="id";
	public static String DEPAIRPORT="ld_DepAirport";
	public static String ARRAIRPORT="ld_ArrAirport";
	public static String ADULT="adult";
	public static String CHD="chd";
	public static String INF="inf";
	public static String GOODS="goods";
	public static String MAIL="mail";
	public static String LUGGAGE="luggage";
	public static String FLIGHT="flight";
	public static String ROUTEHD="routeHD";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;			
	
	@ManyToOne
	@JoinColumn(name="depAirport")
	private Airport ld_DepAirport;
	
	@ManyToOne
	@JoinColumn(name="arrAirport")
	private Airport ld_ArrAirport;
	
	private int adult;
	
	private int chd;
	
	private int inf;
	
	private int goods;
	
	private int mail;
	
	private int luggage;
	
	private String routeHD;

	
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
	 * @return the ld_DepAirport
	 */
	public Airport getLd_DepAirport() {
		return ld_DepAirport;
	}

	/**
	 * @param ld_DepAirport the ld_DepAirport to set
	 */
	public void setLd_DepAirport(Airport ld_DepAirport) {
		this.ld_DepAirport = ld_DepAirport;
	}

	/**
	 * @return the ld_ArrAirport
	 */
	public Airport getLd_ArrAirport() {
		return ld_ArrAirport;
	}

	/**
	 * @param ld_ArrAirport the ld_ArrAirport to set
	 */
	public void setLd_ArrAirport(Airport ld_ArrAirport) {
		this.ld_ArrAirport = ld_ArrAirport;
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

	/**
	 * @return the routeHD
	 */
	public String getRouteHD() {
		return routeHD;
	}

	/**
	 * @param routeHD the routeHD to set
	 */
	public void setRouteHD(String routeHD) {
		this.routeHD = routeHD;
	}



	
	
}
