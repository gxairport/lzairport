package com.lzairport.ais.service.statistics.forecast;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.forecast.ForecastMonthFlight;
import com.lzairport.ais.service.IService;

/**
 * FileName      IForecastMonthFlightService.java
 * @Description  TODO 预测航班月的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IForecastMonthFlightService extends IService<Integer, ForecastMonthFlight> {

}
