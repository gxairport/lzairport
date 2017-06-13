package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IPlnFlightDisPatchDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.PlnFlightDisPatch;

/**
 * 航班计划调度环节的Dao接口实现类
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */
@Stateless
public class PlnFlightDisPatchDao extends AodbDaoImpl<Integer, PlnFlightDisPatch> implements
		IPlnFlightDisPatchDao {


}
