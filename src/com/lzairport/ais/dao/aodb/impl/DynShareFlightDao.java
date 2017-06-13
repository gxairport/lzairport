package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDynShareFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.DynShareFlight;

/**
 * 航班动态共享的实体类的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class DynShareFlightDao extends AodbDaoImpl<Integer, DynShareFlight>
		implements IDynShareFlightDao {

	
}
