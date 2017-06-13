package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Local;

import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * 
 * 
 * FileName      IForecastSettlementCreater.java
 * @Description  TODO 预测收入某个收费项目生成者接口 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IForecastSettlementCreater {

	/**
	 * 根据预测航线生成某个项目的收费，小数点保留2位
	 * @param base         预测航线收入基础
	 * @param type         收费项目
	 * @throws Exception
	 */
	public void create(ForecastBase base,SettlementType type) throws Exception;
}
