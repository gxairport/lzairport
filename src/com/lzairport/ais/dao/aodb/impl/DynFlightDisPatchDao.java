package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDynFlightDisPatchDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.DynFlightDisPatch;

/**
 * 航班动态调度环节的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class DynFlightDisPatchDao extends
		AodbDaoImpl<Integer, DynFlightDisPatch> implements
		IDynFlightDisPatchDao {



}
