package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;


/**
 * 
 * FileName      PlaneServiceCreater.java
 * @Description  TODO 飞机服务收费生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月15日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年11月15日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class AircraftCreater extends DefaultSingleCreater {

	@Override
	protected Double getPrice(HisFlight flight) {
		
		Double price = 0.0;
		int    payLoad= 0;
		if (flight.getAircraft() != null){
			payLoad = flight.getAircraft().getSettlementLoad();
		}
		if (payLoad <= 10){
			price = 6.0;
		}else{
			price = 7.0;
		}
		if (flight.isRunBefore()) {
			price *= 1.1;
		}
		if (flight.isRunAfter()) {
			price *= 1.2;
		}
		return price*payLoad;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		// TODO Auto-generated method stub
		return itemService.findByFieldSingle(SettlementItem.CODE, "AIRCRAFT-P");
	}

	@Override
	protected Double getNumber(HisFlight flight, SettlementType type) {
		// TODO Auto-generated method stub
		return type.getUnit();
	}

}
