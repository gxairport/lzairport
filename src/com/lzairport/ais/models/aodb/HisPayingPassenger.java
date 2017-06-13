package com.lzairport.ais.models.aodb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 历史航班付费旅客实体类
 * @author ZhangYu
 * @version 0.9a 02/09/14
 * @since JDK 1.6
 *
 */
@Entity
public class HisPayingPassenger extends PayingPassenger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String  EXECDATE = "execDate";
	public static String  TYPENAME = "typeName";
	public static String  AIRLINES = "airlines";
	public static String  CARRIER  = "carrier";
	public static String  FLIGHTNO = "flightNO";
	public static String DEPAIRPORT= "depAirport";
	public static String ARRAIRPORT = "arrAirport";	
	public static String  CATEGORY = "category";
	public static String  CRAFTNO  = "craftno";
	public static String  FCLASS ="头等舱旅客服务费";
	public static String  CCLASS ="公务舱旅客服务费";
	
	@ManyToOne
	@JoinColumn(name="hisFlight_id")
	private HisFlight flight;
	
	private  String flightNO;
	
	private Date execDate;
	
	/**
	 * 项目类型名称
	 */
	@Column(length=8)
	private String typeName;
	
	@Column(length=8)
	private String category;

	@Column(length=8)
	private String craftno; 
	
	@Column(length=3)
	private String depAirport;

	@Column(length=3)
	private String arrAirport;
	
	@ManyToOne
	private Airlines airlines;
	
	@ManyToOne
	private Carrier carrier;

	

	
	
	@Override
	public Flight getFlight() {
		// TODO Auto-generated method stub
		return this.flight;
	}

	@Override
	public void setFlight(Flight flight) {
		// TODO Auto-generated method stub
		if (flight instanceof HisFlight) {
			this.flight = (HisFlight) flight;
		}
	}

	/**
	 * @return the execDate
	 */
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
	 * @return the craftno
	 */
	public String getCraftno() {
		return craftno;
	}

	/**
	 * @param craftno the craftno to set
	 */
	public void setCraftno(String craftno) {
		this.craftno = craftno;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
	 * @return the carrier
	 */
	public Carrier getCarrier() {
		return carrier;
	}

	/**
	 * @param carrier the carrier to set
	 */
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
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
	 * @return the depAirport
	 */
	public String getDepAirport() {
		return depAirport;
	}

	/**
	 * @param depAirport the depAirport to set
	 */
	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}

	/**
	 * @return the arrAirport
	 */
	public String getArrAirport() {
		return arrAirport;
	}

	/**
	 * @param arrAirport the arrAirport to set
	 */
	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}


	
	
}
