package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      ISettlementItemService.java
 * @Description  TODO结算项目明细的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月7日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Remote
public interface ISettlementItemService extends IService<Integer, SettlementItem> {
	
	

}
