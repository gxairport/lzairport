package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.BoardingCard;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.service.settlement.IBoardingCardService;

/**
 * FileName      ForecastCardCreater.java
 * @Description  TODO 预测登机牌收费生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastCardCreater  extends ForecastSingleCreater {

	
	@EJB
	private IBoardingCardService cardService;
	
	@Override
	protected Double getPrice(ForecastBase base) {

		Double price = 0.0;
		BoardingCard card = cardService.findByFieldSingle(BoardingCard.AIRLINES,base.getAirlines());
		int seat = base.getSeat();
		if (seat < 50){
			price = ((Integer)card.getPrice1()).doubleValue();
		}else if (seat < 100){
			price = ((Integer)card.getPrice2()).doubleValue();
		}else if (seat < 200){
			price = ((Integer)card.getPrice3()).doubleValue();
		}else{
			price = ((Integer)card.getPrice4()).doubleValue();
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(ForecastBase base) {
		return itemService.findByFieldSingle(SettlementItem.CODE, "CARD");
	}

	@Override
	protected Double getNumber(ForecastBase base) {
		return base.getCountFlt().doubleValue();
	}

}
