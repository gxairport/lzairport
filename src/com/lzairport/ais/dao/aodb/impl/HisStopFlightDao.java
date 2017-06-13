package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IHisStopFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.HisStopFlight;

/**
 * 航班经停历史的实体类的Dao接口实现类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class HisStopFlightDao extends AodbDaoImpl<Integer, HisStopFlight>
		implements IHisStopFlightDao {


}
