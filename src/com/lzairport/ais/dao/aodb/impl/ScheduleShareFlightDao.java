package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IScheduleShareFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.ScheduleShareFlight;

/**
 * 航班共享班期计划的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 23/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class ScheduleShareFlightDao extends
		AodbDaoImpl<Integer, ScheduleShareFlight> implements
		IScheduleShareFlightDao {

	}
