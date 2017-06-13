package com.lzairport.ais.models.aodb;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 航班计划经停的实体类
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class PlnStopFlight extends StopFlight {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="plnFlight_id")
	private PlnFlight flight;

}
