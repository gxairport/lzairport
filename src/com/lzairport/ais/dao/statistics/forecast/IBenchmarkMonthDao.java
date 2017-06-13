package com.lzairport.ais.dao.statistics.forecast;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.statistics.forecast.BenchmarkMonth;

/**
 * FileName      IBenchmarkMonthDao.java
 * @Description  TODO 基准数据月的Dao接口 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IBenchmarkMonthDao extends IDao<Integer, BenchmarkMonth> {

}
