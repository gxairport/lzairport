package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDynPayingPassengerDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.DynPayingPassenger;




/**
 * 航班付费旅客实体类的Dao的实现类
 * @author ZhangYu
 * @version 0.9a 02/09/14
 * @since JDK 1.6
 *
 */

@Stateless
public class DynPayingPassengerDao extends
		AodbDaoImpl<Integer, DynPayingPassenger> implements
		IDynPayingPassengerDao {


}
