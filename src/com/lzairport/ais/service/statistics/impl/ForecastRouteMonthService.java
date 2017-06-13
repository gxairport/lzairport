package com.lzairport.ais.service.statistics.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.statistics.IForecastRouteMonthDao;
import com.lzairport.ais.models.statistics.ForecastRouteMonth;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IForecastRouteMonthService;

/**
 * 
 * FileName      ForecastRouteMonthService.java
 * @Description  TODO 预测航线月的Service接口的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月1日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月1日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastRouteMonthService extends Service<Integer, ForecastRouteMonth> implements IForecastRouteMonthService {

	
	
	
	
	
	
	
	@EJB
	public void setForecastRouteMonthDao(IForecastRouteMonthDao dao){
		setDao(dao);
	}

	
	

	@Override
	public ForecastRouteMonth add(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ForecastRouteMonth){
			ForecastRouteMonth mRoute = (ForecastRouteMonth) obj;
			mRoute.autoCalc();
			return super.add(mRoute);
		}else{
			return null;
		}
	}




	@Override
	public ForecastRouteMonth update(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ForecastRouteMonth){
			ForecastRouteMonth mRoute = (ForecastRouteMonth) obj;
			mRoute.autoCalc();
			return super.update(mRoute);
		}else{
			return null;
		}		
		
	}
	
	
	
	
}
