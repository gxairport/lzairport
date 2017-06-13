package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;



/**
 * 
 * FileName      AprcCreater.java
 * @Description  TODO 进近指挥费生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月16日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月16日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class AprcCreater extends DefaultSingleCreater {

	@Override
	protected Double getPrice(HisFlight flight) {
		// TODO Auto-generated method stub
		Double price = 0.0;
		int    weight= 0;
		if (flight.getAircraft() != null){
			weight = flight.getAircraft().getMaxWeight(); 
		}
		if (weight<=25){
			price = weight*2.2;
		}else if (weight <=50){
			price = weight*2.88;
		}else if (weight <= 100){
			price = weight*3.25;
		}else if (weight <200){
			price = weight*4.55;
		}else {
			price = weight*5.60;
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "APRC");
	}

	@Override
	protected Double getNumber(HisFlight flight, SettlementType type) {
		return type.getUnit();
	}

}
