package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.service.IService;

/**
 * 机场Service接口
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IAirportService extends IService<Integer, Airport> {
	
	/**
	 * 取得国内中转机场
	 * @return
	 */
	public Airport getTransitDomAirport();
	
	public Airport getLocalAirport();

}
