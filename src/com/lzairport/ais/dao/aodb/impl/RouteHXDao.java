package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IRouteHXDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.RouteHX;

/**
 * 
 * FileName      RouteDao.java
 * @Description  航线Dao的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年4月5日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年4月5日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class RouteHXDao extends AodbDaoImpl<Integer, RouteHX> implements 
	IRouteHXDao{

}
