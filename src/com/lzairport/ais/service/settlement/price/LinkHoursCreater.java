package com.lzairport.ais.service.settlement.price;

import java.text.DecimalFormat;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.ServiceLink;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.settlement.IServiceLinkService;
import com.lzairport.ais.utils.DateTimeUtil;



/**
 * 
 * FileName      LinkHoursCreater.java
 * @Description  TODO 按小时收费的服务环节生成者
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月16日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年11月16日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public  class LinkHoursCreater extends DefaultLinkCreater {

	@EJB
	protected IServiceLinkService serviceLinkService;
	
	@Override
	protected Double getPrice(HisFlight flight) {
		return item.getPrice();
	}

	@Override
	protected Double getNumber(SettlementType type,ServiceLink link) {
		// TODO Auto-generated method stub
		Double number = 0.0;
		startTime = link.getStartTime();
		endTime   = link.getEndTime();
		if ( startTime != null && endTime != null){
			Double interval = DateTimeUtil.MillisecondBetween(startTime, endTime)/3600000.0;
			number = 1.0;
			interval = interval -1;
			do{
				if (interval > 0){
					number +=0.5;
				}
				interval = interval -0.5;
			}while(interval > 0 );
			DecimalFormat df = new DecimalFormat("#.0");
			number = Double.parseDouble(df.format(number));
		}
		return number*type.getUnit();
	}

}
