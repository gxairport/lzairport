package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IAirportDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.Airport;

/**
 * 机场Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 22/08/24
 * @since JDK 1.6
 *
 */

@Stateless
public class AirportDao extends AodbDaoImpl<Integer, Airport> implements
		IAirportDao {


}
