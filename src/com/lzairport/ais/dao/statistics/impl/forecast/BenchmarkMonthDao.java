package com.lzairport.ais.dao.statistics.impl.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.forecast.IBenchmarkMonthDao;
import com.lzairport.ais.models.statistics.forecast.BenchmarkMonth;

/**
 * FileName      BenchmarkMonthDao.java
 * @Description  TODO 基准数据月的Dao接口 的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class BenchmarkMonthDao extends AodbDaoImpl<Integer, BenchmarkMonth> implements IBenchmarkMonthDao {

	

}
