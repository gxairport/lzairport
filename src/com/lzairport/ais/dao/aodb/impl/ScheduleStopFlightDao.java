package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IScheduleStopFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.ScheduleStopFlight;

/**
 * 经停班期计划的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class ScheduleStopFlightDao extends
		AodbDaoImpl<Integer, ScheduleStopFlight> implements
		IScheduleStopFlightDao {


}
