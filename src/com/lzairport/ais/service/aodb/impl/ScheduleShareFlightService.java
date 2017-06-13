package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IScheduleShareFlightDao;
import com.lzairport.ais.models.aodb.ScheduleShareFlight;
import com.lzairport.ais.service.aodb.IScheduleShareFlightService;
import com.lzairport.ais.service.impl.Service;

/**
 * 航班共享班期计划的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 04/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class ScheduleShareFlightService extends
		Service<Integer, ScheduleShareFlight> implements
		IScheduleShareFlightService {

	@EJB
	public void setScheduleShareFlightDao(IScheduleShareFlightDao scheduleShareFlightDao){
		setDao(scheduleShareFlightDao);
	}
	

}
