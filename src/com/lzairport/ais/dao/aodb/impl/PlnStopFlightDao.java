package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IPlnStopFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.PlnStopFlight;

/**
 * 航班计划经停的实体类的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnStopFlightDao extends AodbDaoImpl<Integer, PlnStopFlight>
		implements IPlnStopFlightDao {



}
