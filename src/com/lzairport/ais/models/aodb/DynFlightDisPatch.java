package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ExcludeSuperclassListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lzairport.ais.jms.DynFlightDispatchListeners;



/**
 * 
 * FileName      DynFlightDisPatch.java
 * @Description  动态运输航班服务的实体类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 07/11/14
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-21      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Entity
@ExcludeSuperclassListeners
@EntityListeners(DynFlightDispatchListeners.class)
public class DynFlightDisPatch extends FlightDisPatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="dynFlight_id")
	private DynFlight flight;

	@Override
	public Flight getFlight() {

		return this.flight;
	}


	@Override
	public void setFlight(Flight flight) {

		if (flight instanceof DynFlight) {
			this.flight = (DynFlight) flight;
			
		}
	}

	
}
