package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 运输航班服务计划的实体类
 * @author ZhangYu
 * @version 0.9a 08/09/14
 * @since JDK 1.6
 *
 */

@Entity
public class PlnFlightDisPatch extends FlightDisPatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="plnFlight_id")
	private PlnFlight flight;
	

	@Override
	public Flight getFlight() {
		// TODO Auto-generated method stub
		return this.flight;
	}


	@Override
	public void setFlight(Flight flight) {
		// TODO Auto-generated method stub
		if (flight instanceof PlnFlight) {
			this.flight = (PlnFlight) flight;
			
		}		
	}

	

}
