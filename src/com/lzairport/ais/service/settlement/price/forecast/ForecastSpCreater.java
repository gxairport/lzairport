package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * FileName      ForecastPaCheckCreater.java
 * @Description  TODO 预测旅客行李检查收费项生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastSpCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		Double price = 0.0;
		if(areaService.getDomAttribute().equals(base.getAttribute())){
			/*
			 *  国内航班
			 */				
			price = 7.0;
		}else{
			/*
			 * 国际航班
			 */
			price = 12.0;
		}		
		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		
		if (areaService.getDomAttribute().equals(base.getAttribute())){
			return itemService.findByFieldSingle(SettlementItem.CODE, "SP-D");
		}else {
			return itemService.findByFieldSingle(SettlementItem.CODE, "SP-I");
		}
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getPax().doubleValue();
	}

}
