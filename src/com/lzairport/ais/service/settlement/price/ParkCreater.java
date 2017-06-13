package com.lzairport.ais.service.settlement.price;

import javax.ejb.Stateless;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
/**
 * FileName      ParkCreater.java
 * @Description  TODO 停场费收费项目生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月13日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年11月13日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class ParkCreater extends DefaultSingleCreater {
	
	protected static  String parkBegin = "滑入机位";
	protected static  String parkEnd   = "许可开车";
	
	private int getDiff(HisFlight flight){
		Long interval =  0L ;
		HisFlight prFlight = null;
		HisFlight ntFlight = null;
		if (OutIn.Dep.equals(flight.getIsOutIn())){
			prFlight =  flightService.getPreviousFlight(flight);
            ntFlight =  flight; 					
		}else if (OutIn.Arr.equals(flight.getIsOutIn())) {
			ntFlight = flightService.getLinkFlight(flight);
            prFlight = flight; 					
		}
		if (prFlight != null && ntFlight != null){
			for(FlightDisPatch disPatch:prFlight.getFlightDisPatchs()){
				if (parkBegin.equals(disPatch.getDisPatchItem().getName())){
					startTime = disPatch.getEndRealTime();
				}
			}
			
			for(FlightDisPatch disPatch:ntFlight.getFlightDisPatchs()){
				if (parkEnd.equals(disPatch.getDisPatchItem().getName())){
					endTime = disPatch.getEndRealTime();
				}
			}
		}
		
		if (startTime != null && endTime != null){
			
			/*
			 *  间隔时间(小时)
			 */
			interval = DateTimeUtil.MillisecondBetween(startTime, endTime)/3600000;
			/*
			 *  如果大于2小时小于24小时计算1，大于24小时每24小时加1
			 */
		}
		return interval.intValue();
	}

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
		
		int diff = getDiff(flight); 
		if (diff <= 2){
			/*
			 *   如果2小时以内免收
			 */
			price = price * 0;
		}else if (diff <= 6){
			/*
			 *   2-6 小时 20% 
			 */
			price = price * 0.2;
		}else if (diff <= 24 ){
			/*
			 *   6-24小时25%
			 */
			price = price *0.25 ;
		}else{
			/*
			 *  24小时以上每24小时25%，不足24小时按24小时计
			 */
			Double basePrice = price *0.25 ;
			price = 0.0;
			do{
				price += basePrice;
				diff = diff -24;
			}while(diff > 0 );
		}
		
		
		return price;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		
		return itemService.findByFieldSingle(SettlementItem.CODE, "PARK");
	}

	@Override
	protected Double getNumber(HisFlight flight, SettlementType type) {

	
		
		return type.getUnit();
	}

}
