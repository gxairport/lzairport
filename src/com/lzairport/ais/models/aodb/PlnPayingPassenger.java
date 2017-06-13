package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 计划航班付费旅客实体类
 * @author ZhangYu
 * @version 0.9a 02/09/14
 * @since JDK 1.6
 *
 */
@Entity
public class PlnPayingPassenger extends PayingPassenger {

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
