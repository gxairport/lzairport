package com.lzairport.ais.service.settlement.price;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.ServiceLink;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.settlement.IServiceLinkService;



/**
 * 
 * FileName      LinkTimesCreater.java
 * @Description  TODO 按次数收费的服务环节生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月16日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月16日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Stateless
public  class LinkTimesCreater extends DefaultLinkCreater {

	@EJB
	protected IServiceLinkService serviceLinkService;
	
	
	@Override
	protected Double getPrice(HisFlight flight) {
		return item.getPrice();
	}

	@Override
	protected Double getNumber(SettlementType type,ServiceLink link) {
		Double number = ((Integer)link.getTimes()).doubleValue();
		return number*type.getUnit();
	}

}
