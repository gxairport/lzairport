package com.lzairport.ais.service.settlement.forecast;

import javax.ejb.Remote;

import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.models.settlement.forecast.ForecastSettlement;
import com.lzairport.ais.service.IService;

/**
 * 
 * 
 * FileName      IForecastSettlement.java
 * @Description  TODO 预测收入结算的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月24日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月24日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Remote
public interface IForecastSettlementService extends IService<Integer, ForecastSettlement> {
	
	/**
	 * 
	 * @Description: TODO  生成一条航线的收入
	 * @param  Base        航线基础数据
	 * @throws Exception 
	 */
	public void createSettlement(ForecastBase base) throws Exception;

}
