package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IHisPayingPassengerDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.HisPayingPassenger;



/**
 * 航班历史付费旅客实体类的Dao的实现类
 * @author ZhangYu
 * @version 0.9a 19/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class HisPayingPassengerDao extends
		AodbDaoImpl<Integer, HisPayingPassenger> implements
		IHisPayingPassengerDao {


}
