package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;

/**
 * 
 * FileName      CargoMailForecastCreater.java
 * @Description  TODO 预测货物邮件收费项目生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class ForecastCargoMailCreater extends ForecastSingleCreater {

	@Override
	protected Double getPrice(ForecastBase base) {
		Double price = 0.0;
		int    payLoad= base.getPayLoad();
		if (payLoad <= 10){
			price = 25.0*payLoad;
		}else{
			price = 28.0*payLoad;
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "CARGOMAIL");
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getCountFlt().doubleValue();
	}

}
