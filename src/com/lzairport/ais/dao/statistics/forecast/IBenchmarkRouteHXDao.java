package com.lzairport.ais.dao.statistics.forecast;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.statistics.forecast.BenchmarkRouteHX;

/**
 * FileName      IBenchamarkRouteHXDao.java
 * @Description  TODO 预测航班的基准数据Dao接口 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IBenchmarkRouteHXDao extends IDao<Integer, BenchmarkRouteHX> {

}
