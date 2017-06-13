package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 动态航班付费旅客实体类
 * @author ZhangYu
 * @version 0.9a 02/09/14
 * @since JDK 1.6
 *
 */
@Entity
public class DynPayingPassenger extends PayingPassenger {

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
		this.flight = (DynFlight) flight;
	}
		

}
