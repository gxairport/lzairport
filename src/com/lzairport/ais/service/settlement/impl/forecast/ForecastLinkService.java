package com.lzairport.ais.service.settlement.impl.forecast;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.forecast.IForecastLinkDao;
import com.lzairport.ais.models.settlement.forecast.ForecastLink;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.forecast.IForecastLinkService;
/**
 * 
 * 
 * FileName      ForecastLinkService.java
 * @Description  TODO 各预测服务环节的Service
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月24日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月24日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastLinkService extends Service<Integer, ForecastLink> implements IForecastLinkService {

	@EJB
	public void setForecastLinkDao(IForecastLinkDao dao){
		setDao(dao);
	}

}
