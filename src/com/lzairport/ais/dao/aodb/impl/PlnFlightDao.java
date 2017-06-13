package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IPlnFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.PlnFlight;

/**
 * 航班计划的实体类的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnFlightDao extends AodbDaoImpl<Integer, PlnFlight> implements
		IPlnFlightDao {



}
