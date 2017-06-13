package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * FileName      ForecastLandCreater.java
 * @Description  TODO  预测起降收入的生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastLandCreater  extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		Double price = 0.0;
		int    weight= base.getWeight();
		if (areaService.getDomAttribute().equals(base.getAttribute())){
				/*
				 *  国内航班
				 */
				if (weight<=25){
					price = 270.0;
				}else if (weight <=50){
					price = 800.0;
				}else if (weight <= 100){
					price = 1300.0+24*(weight-50);
				}else if (weight <200){
					price = 2500.0+26*(weight-100);
				}else {
					price = 5150.0+33*(weight-200);
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
	protected SettlementItem getSetItem(ForecastBase base) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "LAND");
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getCountFlt().doubleValue();
	}



}
