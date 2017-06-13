package com.lzairport.ais.service.statistics.forecast;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.forecast.BenchmarkMonth;
import com.lzairport.ais.service.IService;

/**
 * FileName      IBenchmarkMonthService.java
 * @Description  TODO 基准数据月的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IBenchmarkMonthService extends IService<Integer, BenchmarkMonth> {

}
