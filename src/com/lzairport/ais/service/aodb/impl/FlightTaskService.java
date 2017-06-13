package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IFlightTaskDao;
import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.service.aodb.IFlightTaskService;
import com.lzairport.ais.service.impl.Service;


/**
 * 飞机任务的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class FlightTaskService extends Service<Integer, FlightTask> implements
		IFlightTaskService {

	@EJB
	public void setFlightTaskDao(IFlightTaskDao flightTaskDao){
		setDao(flightTaskDao);
	}

	@Override
	public FlightTask getSupplementTask() {
		return findByFieldSingle(FlightTask.CODE, "Z/P");
	}
}
