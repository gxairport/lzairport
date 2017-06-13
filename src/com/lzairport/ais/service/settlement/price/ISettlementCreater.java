package com.lzairport.ais.service.settlement.price;

import javax.ejb.Local;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementType;

/**
 * 
 * FileName      ISettlementCreater.java
 * @Description  TODO航班结算某个收费项目的单价生成者接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月11日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月11日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface ISettlementCreater {
	/**
	 * 
	 * @Description: TODO 根据航班生成某个航班的收费项的单价，小数点保留2位
	 * @param flight
	 * @throws Exception 
	 */
	public void create(HisFlight flight,SettlementType type) throws Exception;

}
