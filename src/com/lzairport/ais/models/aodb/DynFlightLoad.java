package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 航班动态的配载实体类
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class DynFlightLoad extends FlightLoad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="dynFlight_id")
	private DynFlight flight;
	
	
	@Override
	public Flight getFlight() {
		// TODO Auto-generated method stub
		return this.flight;
	}


	
	@Override
	public void setFlight(Flight flight) {
		// TODO Auto-generated method stub

		if (flight instanceof DynFlight) {
			this.flight = (DynFlight) flight;
			
		}
	}

	
	
	
	
	

}
