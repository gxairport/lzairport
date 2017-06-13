package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IPlnFlightLoadDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.PlnFlightLoad;

/**
 *航班计划的配载实体类的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnFlightLoadDao extends AodbDaoImpl<Integer, PlnFlightLoad>
		implements IPlnFlightLoadDao {

	
}
