package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IHisStopFlightDao;
import com.lzairport.ais.models.aodb.HisStopFlight;
import com.lzairport.ais.service.aodb.IHisStopFlightService;
import com.lzairport.ais.service.impl.Service;

/**
 * 航班经停历史的Service接口实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class HisStopFlightService extends Service<Integer, HisStopFlight>
		implements IHisStopFlightService {

	@EJB
	public void setHisStopFlightDao(IHisStopFlightDao hisStopFlightDao){
		
	}
}
