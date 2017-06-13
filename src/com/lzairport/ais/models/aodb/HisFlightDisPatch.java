package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 运输航班服务历史的实体类
 * @author ZhangYu
 * @version 0.9a 07/11/14
 * @since JDK 1.6
 *
 */

@Entity
public class HisFlightDisPatch extends FlightDisPatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="hisFlight_id")
	private HisFlight flight;

	@Override
	public Flight getFlight() {
		// TODO Auto-generated method stub
		return this.flight;
	}

	@Override
	public void setFlight(Flight flight) {

		if (flight instanceof HisFlight) {
			this.flight = (HisFlight) flight;
			
		}
	}

	

}
