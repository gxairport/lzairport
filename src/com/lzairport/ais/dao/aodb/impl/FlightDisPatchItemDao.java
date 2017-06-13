package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IFlightDisPatchItemDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.FlightDisPatchItem;



/**
 * 调度环节的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 22/08/24
 * @since JDK 1.6
 *
 */

@Stateless
public class FlightDisPatchItemDao extends AodbDaoImpl<Integer, FlightDisPatchItem> implements
		IFlightDisPatchItemDao {


}
