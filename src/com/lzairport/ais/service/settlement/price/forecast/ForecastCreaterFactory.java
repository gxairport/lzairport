package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.models.settlement.SettlementType;


/**
 * FileName      ForecastCreaterFactory.java
 * @Description  TODO 预测结算项目生成者的制造工厂
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastCreaterFactory {
	
	@EJB
	private ForecastLandCreater landCreater;
	
	@EJB
	private ForecastPaCreater paxCreater;
	
	@EJB
	private ForecastScCreater scCreater;
	
	@EJB
	private ForecastSpCreater spCreater;
	
	@EJB
	private ForecastParkCreater parkCreater;
	
	@EJB
	private ForecastStowageCreater stowageCreater;
	
	@EJB
	private ForecastCargoMailCreater cargoMailCreater;
	
	@EJB
	private ForecastApronCreater apronCreater;
	
	@EJB
	private ForecastGeneralCreater generalCreater;
	
	@EJB
	private ForecastAircraftCreater aircraftCreater;
	
	@EJB
	private ForecastCardCreater cardCreater;
	
	@EJB
	private ForecastAprcCreater aprcCreater;
	
	
	
	
	public IForecastSettlementCreater getCreater(SettlementType settlementType){
		Integer code =  settlementType.getCode(); 
		IForecastSettlementCreater creater = null;
		switch (code) {
		case 1:
			creater = landCreater;
			break;
		case 2:
			creater = paxCreater;
			break;		

		case 3:
			creater = scCreater;
			break;		
		
		case 4:
			creater = spCreater;
			break;		
			
		case 5:
			creater = parkCreater;
			break;		
			
		case 6:
			creater = stowageCreater;
			break;		

		case 7:
			creater = cargoMailCreater;
			break;		
			
		case 8:
			creater = apronCreater;
			break;		
			
		case 9:
			creater = generalCreater;
			break;		
			
		case 10:
			creater = aircraftCreater;
			break;		
			
		case 11:
			creater = cardCreater;
			break;		
			
		case 12:
			creater = aprcCreater;
			break;		
			
		default:
			break;
		}
		return creater;
	}

}
