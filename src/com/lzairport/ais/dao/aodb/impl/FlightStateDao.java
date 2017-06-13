package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IFlightStateDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.FlightState;

/**
 * 航班状态实体类的的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 19/08/14
 * @since JDK 1.6
 *
 */
@Stateless
public class FlightStateDao extends AodbDaoImpl<Integer, FlightState> implements
		IFlightStateDao {


}
