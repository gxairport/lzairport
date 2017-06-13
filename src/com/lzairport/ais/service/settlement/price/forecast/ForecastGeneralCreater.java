package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;


/**
 * 
 * FileName      GeneralForecastCreater.java
 * @Description  TODO 预测飞机一般勤务收费生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastGeneralCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		// TODO Auto-generated method stub
		Double price = 0.0;
		int    seat=   base.getSeat();
		if (seat < 100){
			price = 100.0;
		}else if (seat < 200){
			price = 150.0;
		}else if (seat < 300){
			price = 300.0;
		}else {
			price = 600.0;
		}

		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "GENERAL");
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getCountFlt().doubleValue();
	}

}
