package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 
 * FileName      PlnFlightLoad.java
 * @Description  航班计划的配载实体类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 16/08/14 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-21      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Entity
public class PlnFlightLoad extends FlightLoad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="plnFlight_id")
	private PlnFlight flight;

	@Override
	public Flight getFlight() {

		return this.flight;
	}

	@Override
	public void setFlight(Flight flight) {

		if (flight instanceof PlnFlight) {
			this.flight = (PlnFlight) flight;
			
		}				
	}

	
}
