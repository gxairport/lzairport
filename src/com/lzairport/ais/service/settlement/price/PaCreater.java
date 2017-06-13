package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.utils.SYS_VARS.OutIn;

/**
 * 
 * FileName      PaxCreater.java
 * @Description  TODO 旅客服务费生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月11日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月11日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class PaCreater extends DefaultSingleCreater {

	
	
	@Override
	protected Double getPrice(HisFlight flight) {

		Double price = 0.0;
		if (OutIn.Dep.equals(flight.getIsOutIn())){
			if(areaService.getDomAttribute().equals(flight.getAttribute())){
				/*
				 *  国内航班
				 */				
				price = 42.0;
			}else{
				/*
				 * 国际航班
				 */
				price = 70.0;
			}
		}
				
			
		return price;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {

		if (areaService.getDomAttribute().equals(flight.getAttribute())){
			return itemService.findByFieldSingle(SettlementItem.CODE, "PA-D");
		}else {
			return itemService.findByFieldSingle(SettlementItem.CODE, "PA-I");
		}
		
	}

	@Override
	protected Double getNumber(HisFlight flight,SettlementType type) {
		// TODO Auto-generated method stub
		return ((Integer)flight.getLoc_Adult()).doubleValue();
	}




}
