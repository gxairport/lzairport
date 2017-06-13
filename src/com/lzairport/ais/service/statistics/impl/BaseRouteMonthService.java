package com.lzairport.ais.service.statistics.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.statistics.IBaseRouteMonthDao;
import com.lzairport.ais.models.statistics.BaseRouteMonth;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IBaseRouteMonthService;


/**
 * 
 * FileName      BaseRouteMonthService.java
 * @Description  TODO 预测航线每个月的基准数据的Service实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月7日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class BaseRouteMonthService extends Service<Integer, BaseRouteMonth> implements IBaseRouteMonthService {
	
	
	private DecimalFormat df = new DecimalFormat("#.0");
	
	@EJB
	public void setBaseRouteMonthDao(IBaseRouteMonthDao dao){
		setDao(dao);
	}

	@Override
	public BaseRouteMonth update(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof BaseRouteMonth){
			/**
			 *  计算平均客座率
			 */
			BaseRouteMonth month = (BaseRouteMonth) obj;
			month.setRate((month.getiRate()+month.getoRate())/2);
			df.setRoundingMode(RoundingMode.HALF_UP);
			month.setRate(Double.parseDouble(df.format(month.getRate())));
			return super.update(month);
		}else{
			return null;
		}
		
		
	}
	
	
	
	
	
}
