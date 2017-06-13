package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDynStopFlightDao;
import com.lzairport.ais.models.aodb.DynStopFlight;
import com.lzairport.ais.service.aodb.IDynStopFlightService;
import com.lzairport.ais.service.impl.Service;

/**
 * 航班动态经停的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class DynStopFlightService extends Service<Integer, DynStopFlight>
		implements IDynStopFlightService {

	@EJB
	public void setDynStopFlightDao(IDynStopFlightDao dynStopFlightDao){
		setDao(dynStopFlightDao);
	}
	
}
