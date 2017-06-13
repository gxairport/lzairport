package com.lzairport.ais.service.settlement.price;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.BoardingCard;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.settlement.IBoardingCardService;
import com.lzairport.ais.utils.SYS_VARS.OutIn;


/**
 * 
 * FileName      CardCreater.java
 * @Description  TODO 登机牌收费生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月15日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月15日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class CardCreater extends DefaultSingleCreater {
	
	@EJB
	private IBoardingCardService cardService;

	@Override
	protected Double getPrice(HisFlight flight) {
		// TODO Auto-generated method stub
		Double price = 0.0;
		BoardingCard card = cardService.findByFieldSingle(BoardingCard.AIRLINES,flight.getAirlines());
		int seat = flight.getAircraft().getAvailableSeat();
		if (card != null && OutIn.Dep.equals(flight.getIsOutIn())){
			if (seat < 50){
				price = ((Integer)card.getPrice1()).doubleValue();
			}else if (seat < 100){
				price = ((Integer)card.getPrice2()).doubleValue();
			}else if (seat < 200){
				price = ((Integer)card.getPrice3()).doubleValue();
			}else{
				price = ((Integer)card.getPrice4()).doubleValue();
			}
		}
		return price;
	}

	@Override
	protected SettlementItem getSetItem(HisFlight flight) {
		// TODO Auto-generated method stub
		return itemService.findByFieldSingle(SettlementItem.CODE, "CARD");
	}

	@Override
	protected Double getNumber(HisFlight flight, SettlementType type) {
		// TODO Auto-generated method stub
		return type.getUnit();
	}

}
