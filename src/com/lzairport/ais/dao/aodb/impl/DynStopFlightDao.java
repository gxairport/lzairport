package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDynStopFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.DynStopFlight;

/**
 * 航班动态经停的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 19/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class DynStopFlightDao extends AodbDaoImpl<Integer, DynStopFlight>
		implements IDynStopFlightDao {

	
}
