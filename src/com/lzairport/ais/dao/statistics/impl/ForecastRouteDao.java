package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IForecastRouteDao;
import com.lzairport.ais.models.statistics.ForecastRoute;

/**
 * 
 * FileName      ForecastRouteDao.java
 * @Description  TODO 预测航线的实体Dao的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月1日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月1日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastRouteDao extends AodbDaoImpl<Integer, ForecastRoute> implements IForecastRouteDao {


}
