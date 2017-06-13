package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IScheduleFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.ScheduleFlight;

/**
 * 航班班期计划的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class ScheduleFlightDao extends AodbDaoImpl<Integer, ScheduleFlight>
		implements IScheduleFlightDao {

	
}
