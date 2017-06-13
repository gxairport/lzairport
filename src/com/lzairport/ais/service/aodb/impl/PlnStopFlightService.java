package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IPlnStopFlightDao;
import com.lzairport.ais.models.aodb.PlnStopFlight;
import com.lzairport.ais.service.aodb.IPlnStopFlightService;
import com.lzairport.ais.service.impl.Service;

/**
 * 航班计划经停的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 04/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnStopFlightService extends Service<Integer, PlnStopFlight>
		implements IPlnStopFlightService {

	@EJB
	public void setPlnStopFlightDao(IPlnStopFlightDao plnStopFlightDao){
		
	}
}
