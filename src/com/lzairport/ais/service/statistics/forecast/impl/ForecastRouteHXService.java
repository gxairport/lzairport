package com.lzairport.ais.service.statistics.forecast.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.statistics.forecast.IForecastRouteHXDao;
import com.lzairport.ais.models.statistics.forecast.ForecastRouteHX;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.forecast.IForecastRouteHXService;


/**
 * FileName      ForecastRouteHXService.java
 * @Description  TODO 预测航线的Service接口的实现类 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastRouteHXService extends Service<Integer, ForecastRouteHX> implements IForecastRouteHXService {

	@EJB
	public void setForecastRouteHXDao(IForecastRouteHXDao dao){
		setDao(dao);
	}

}
