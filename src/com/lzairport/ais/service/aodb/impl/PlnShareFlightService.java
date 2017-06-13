package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IPlnShareFlightDao;
import com.lzairport.ais.models.aodb.PlnShareFlight;
import com.lzairport.ais.service.aodb.IPlnShareFlightService;
import com.lzairport.ais.service.impl.Service;

/**
 * 共享航班计划的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 04/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnShareFlightService extends Service<Integer, PlnShareFlight>
		implements IPlnShareFlightService {

	@EJB
	public void setPlnShareFlightDao(IPlnShareFlightDao plnShareFlightDao){
		
	}
}
