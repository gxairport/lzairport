package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IHisShareFlightDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.HisShareFlight;


/**
 * 共享航班历史的实体类的Dao接口实现类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */
@Stateless
public class HisShareFlightDao extends AodbDaoImpl<Integer, HisShareFlight>
		implements IHisShareFlightDao {

	
}
