package com.lzairport.ais.service.settlement.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.settlement.ISettlementDao;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.Settlement;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.ISettlementService;
import com.lzairport.ais.service.settlement.ISettlementTypeService;
import com.lzairport.ais.service.settlement.price.ISettlementCreater;
import com.lzairport.ais.service.settlement.price.CreaterFactroy;


/**
 * 
 * FileName      SettlementService.java
 * @Description  TODO 结算收入的Service实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月7日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年11月7日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class SettlementService extends Service<Integer, Settlement> implements ISettlementService {
	
	@EJB
	private ISettlementTypeService settlementTypeService;
	
	@EJB
	private CreaterFactroy createrFactroy;

	@EJB
	private IFlightStateService stateService;
	@EJB
	public void setSettlementDao(ISettlementDao dao){
		setDao(dao);
	}
	
	@Override
	public void createSettlement(HisFlight flight) throws Exception {
		for (SettlementType type:settlementTypeService.getAll()){
			ISettlementCreater creater = createrFactroy.getCreater(type);
			if (creater != null && !stateService.getCnlState().equals(flight.getState())){
				creater.create(flight, type);
			}
		}
	}

}
