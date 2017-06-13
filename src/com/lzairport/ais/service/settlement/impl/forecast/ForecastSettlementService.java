package com.lzairport.ais.service.settlement.impl.forecast;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.forecast.IForecastSettlementDao;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.models.settlement.forecast.ForecastSettlement;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.ISettlementTypeService;
import com.lzairport.ais.service.settlement.forecast.IForecastSettlementService;
import com.lzairport.ais.service.settlement.price.forecast.ForecastCreaterFactory;
import com.lzairport.ais.service.settlement.price.forecast.ForecastLinkCreater;
import com.lzairport.ais.service.settlement.price.forecast.IForecastSettlementCreater;

/**
 * 
 * 
 * FileName      ForecastSettlementService.java
 * @Description  TODO  预测收入结算的Service
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月24日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月24日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Stateless
public class ForecastSettlementService extends Service<Integer, ForecastSettlement>
		implements IForecastSettlementService {

	@EJB
	private ISettlementTypeService settlementTypeService;
	
	@EJB
	private ForecastCreaterFactory createrFactory;
	
	@EJB
	private ForecastLinkCreater linkCreater;
	
	@EJB
	public void setForecastSettlementDao(IForecastSettlementDao dao){
		setDao(dao);
	}

	@Override
	public void createSettlement(ForecastBase base) throws Exception {
		for (SettlementType type:settlementTypeService.getAll()){
			IForecastSettlementCreater creater =  createrFactory.getCreater(type);
			if (creater != null){
				creater.create(base, type);
			}
		}
		linkCreater.create(base);
	}	

}
