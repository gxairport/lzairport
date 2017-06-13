package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.utils.SYS_VARS.OutIn;

/**
 * 
 * FileName      PaxCheckCreater.java
 * @Description  TODO  旅客行李检查收费项生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月13日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月13日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class SpCreater extends DefaultSingleCreater {

	@Override
	protected Double getPrice(HisFlight flight) {
		Double price = 0.0;
		if (OutIn.Dep.equals(flight.getIsOutIn())){
			if(areaService.getDomAttribute().equals(flight.getAttribute())){
				/*
				 *  国内航班
				 */				
				price = 10.0;
			}else{
				/*
				 * 国际航班
				 */
				price = 12.0;
			}
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		// TODO Auto-generated method stub
		if (areaService.getDomAttribute().equals(flight.getAttribute())){
			return itemService.findByFieldSingle(SettlementItem.CODE, "SP-D");
		}else {
			return itemService.findByFieldSingle(SettlementItem.CODE, "SP-I");
		}
	}

	@Override
	protected Double getNumber(HisFlight flight, SettlementType type) {
		// TODO Auto-generated method stub
		Integer persons = flight.getLoc_Adult()+flight.getLoc_Chd()+flight.getLoc_Inf();
		return persons.doubleValue();
	}



}
