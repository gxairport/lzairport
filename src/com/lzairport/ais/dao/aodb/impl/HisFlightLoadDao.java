package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IHisFlightLoadDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.HisFlightLoad;

/**
 * 航班配载历史的实体类的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class HisFlightLoadDao extends AodbDaoImpl<Integer, HisFlightLoad>
		implements IHisFlightLoadDao {

	
}
