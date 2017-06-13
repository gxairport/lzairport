package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IFlightTaskDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.FlightTask;


/**
 * 飞机任务的实体类的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */
@Stateless
public class FlightTaskDao extends AodbDaoImpl<Integer, FlightTask> implements
		IFlightTaskDao {


}
