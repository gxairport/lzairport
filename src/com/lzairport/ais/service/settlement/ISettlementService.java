package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.Settlement;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      ISettlementService.java
 * @Description  TODO结算收入的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月7日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface ISettlementService extends IService<Integer, Settlement> {
	/**
	 * 
	 * @Description: TODO 生成一个航班的收入
	 * @param flight
	 * @throws Exception 
	 */
	public void createSettlement(HisFlight flight) throws Exception;
	

}
