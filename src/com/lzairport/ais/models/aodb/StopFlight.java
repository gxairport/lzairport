package com.lzairport.ais.models.aodb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 航班经停抽象类,动态，计划，历史都继承它
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@MappedSuperclass
public  class StopFlight extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */			
	public static String ID="id";
	public static String PLANLANDINTIME = "planLandInTime";
	public static String ORDERCODE = "orderCode";
	public static String PLANTAKEOFFTIME = "planTakeOffTime";
	public static String STOPAIRPORT = "stopAirport";
	public static String FLIGHT="flight";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;				
	
	@ManyToOne
	@JoinColumn(name="stopAirport")
	private Airport stopAirport;
	
	
	@Column(length=6)
	private String planLandInTime;
	
	@Column(length=6)
	private String planTakeOffTime;
	
	
	private int orderCode;

	
	
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
	 * @return the stopAirport
	 */
	public Airport getStopAirport() {
		return stopAirport;
	}

	/**
	 * @param stopAirport the stopAirport to set
	 */
	public void setStopAirport(Airport stopAirport) {
		this.stopAirport = stopAirport;
	}


	/**
	 * @return the planLandInTime
	 */
	public String getPlanLandInTime() {
		return planLandInTime;
	}

	/**
	 * @param planLandInTime the planLandInTime to set
	 */
	public void setPlanLandInTime(String planLandInTime) {
		this.planLandInTime = planLandInTime;
	}

	/**
	 * @return the planTakeOffTime
	 */
	public String getPlanTakeOffTime() {
		return planTakeOffTime;
	}

	/**
	 * @param planTakeOffTime the planTakeOffTime to set
	 */
	public void setPlanTakeOffTime(String planTakeOffTime) {
		this.planTakeOffTime = planTakeOffTime;
	}


	/**
	 * @return the orderCode
	 */
	public int getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	
	
	

}
