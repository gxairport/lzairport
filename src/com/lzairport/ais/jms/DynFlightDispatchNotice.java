package com.lzairport.ais.jms;

import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.DynFlightDisPatch;
import com.lzairport.ais.utils.EventCodeUtil;

/**
 *	当航班动态的服务内容改变时通知各客户端的实现类 
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 08/08/15
 */

@Stateless
public class DynFlightDispatchNotice extends Notice  implements INotice{
	
	private DynFlightDisPatch disPatch;

	@Override
	public void changeNotice(String eventCode, String property, Object obj) {
		// TODO Auto-generated method stub
		//正常通知Dispathch内容变化
		super.changeNotice(eventCode, property, obj);
		//如果是更新内容，则要通知各视图中相关的航班动态更新
		if (property.equals(EventCodeUtil.ModelsUpdate)){
			disPatch = (DynFlightDisPatch) obj;
			DynFlight flight = (DynFlight) disPatch.getFlight();
			super.changeNotice(eventCode, property, flight);
			
		}
	
		
	}
	
	
	
	
}
