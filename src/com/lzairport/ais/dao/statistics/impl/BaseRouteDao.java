package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IBaseRouteDao;
import com.lzairport.ais.models.statistics.BaseRoute;

/**
 * 
 * FileName      BaseRouteDao.java
 * @Description  TODO预测航线的基准数据的Dao实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月7日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class BaseRouteDao extends AodbDaoImpl<Integer, BaseRoute> implements IBaseRouteDao {

	

}
