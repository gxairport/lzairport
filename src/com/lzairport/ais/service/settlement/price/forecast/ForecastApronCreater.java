package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * FileName      ApronForecastCreater.java
 * @Description  TODO 预测地面服务费收费生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastApronCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		Double price = 0.0;
		int    payLoad= base.getPayLoad();
		if (payLoad <= 10){
			price = 5.0;
		}else{
			price = 6.0;
		}
		return price*payLoad;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "APRON");
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getCountFlt().doubleValue();
	}

}
