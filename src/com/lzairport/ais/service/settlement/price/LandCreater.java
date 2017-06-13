package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;




/**
 * 
 * FileName      TakeOffLandCreater.java
 * @Description  TODO起飞降落费生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月11日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月11日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class LandCreater extends  DefaultSingleCreater {
	

	@Override
	protected Double getPrice(HisFlight flight) {
		Double price = 0.0;
		int    weight= 0;
		if (flight.getAircraft() != null){
			weight = flight.getAircraft().getMaxWeight(); 
		}
		if (areaService.getDomAttribute().equals(flight.getAttribute())){
				/*
				 *  国内航班
				 */
				if (weight<=25){
					price = 270.0;
				}else if (weight <=50){
					price = 800.0;
				}else if (weight <= 100){
					price = 1400.0+26*(weight-50);
				}else if (weight <200){
					price = 2700.0+26*(weight-100);
				}else {
					price = 5300.0+33*(weight-200);
				}
			}else{
				/*
				 * 国际航班
				 */
				if (weight<=25){
					price = 2000.0;
				}else if (weight <=50){
					price = 2200.0;
				}else if (weight <= 100){
					price = 2600.0+40*(weight-50);
				}else if (weight <200){
					price = 4200.0+44*(weight-100);
				}else {
					price = 8600.0+56*(weight-200);
				}
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "LAND");
	}

	@Override
	protected Double getNumber(HisFlight flight,SettlementType type) {
		// TODO Auto-generated method stub
		return type.getUnit();
	}



}
