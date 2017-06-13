package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;
import com.lzairport.ais.models.aodb.Carrier;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;

/**
 * 
 * FileName      GoodsMailCreater.java
 * @Description  TODO 货物邮件收费项目生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月15日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月15日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class CargoMailCreater extends DefaultSingleCreater {

	@Override
	protected Double getPrice(HisFlight flight) {
		
		Double price = 0.0;
		int    payLoad= 0;
		if (flight.getAircraft() != null && flight.getAircraft().getCarrier() != null){
			Carrier carrier = flight.getAircraft().getCarrier();
			int goods = flight.getLoc_Goods()+flight.getLoc_Mail();
			if (!(carrier.isGoodsCheck() && goods < 0)){
				/*
				 *  如果航空公司货物检查标志不为真或者有货物，则需要进行收费 
				 */
				if (flight.getAircraft() != null){
					payLoad = flight.getAircraft().getSettlementLoad();
				}				
			}
		}
		if (payLoad <= 10){
			price = 30.0*payLoad;
		}else{
			price = 34.0*payLoad;
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "CARGOMAIL");
	}

	@Override
	protected Double getNumber(HisFlight flight, SettlementType type) {
		return type.getUnit();
	}

}
