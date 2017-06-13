package com.lzairport.ais.dao.statistics.impl.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.forecast.IBenchmarkRouteHXDao;
import com.lzairport.ais.models.statistics.forecast.BenchmarkRouteHX;


/**
 * FileName      BenchmarkRouteHXDao.java
 * @Description  TODO 预测航班的基准数据Dao接口的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class BenchmarkRouteHXDao extends AodbDaoImpl<Integer, BenchmarkRouteHX> implements IBenchmarkRouteHXDao {



}
