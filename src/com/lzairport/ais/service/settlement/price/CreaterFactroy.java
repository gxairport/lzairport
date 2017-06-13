package com.lzairport.ais.service.settlement.price;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.models.settlement.SettlementType;

/**
 * 
 * FileName      CreaterFactroy.java
 * @Description  TODO 结算项目生成者的制造工厂
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月11日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年11月11日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Stateless
public class CreaterFactroy {
	
	
	@EJB
	private LandCreater landCreater;
	
	@EJB
	private PaCreater paxCreater;
	
	@EJB
	private ScCreater scCreater;
	
	@EJB
	private SpCreater spCreater;
	
	@EJB
	private ParkCreater parkCreater;
	
	@EJB
	private StowageCreater stowageCreater;
	
	@EJB
	private CargoMailCreater cargoMailCreater;
	
	@EJB
	private ApronCreater apronCreater;
	
	@EJB
	private GeneralCreater generalCreater;
	
	@EJB
	private AircraftCreater aircraftCreater;
	
	@EJB
	private CardCreater cardCreater;
	
	@EJB
	private AprcCreater aprcCreater;
	
	@EJB
	private LinkHoursCreater linkHoursCreater;
	
	
	@EJB
	private LinkTimesCreater linkTimesCreater;
	
	
	/**
	 * 
	 * @Description: TODO 根据结算项目返回对应的结算项目的单价
	 * @param settlementType
	 * @return
	 */
	public ISettlementCreater getCreater(SettlementType settlementType){
		Integer code =  settlementType.getCode(); 
		ISettlementCreater creater = null;
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
			
		case 101:
			creater = linkHoursCreater;
			break;
		case 104:
			creater = linkTimesCreater;
			break;			
		default:
			break;
		}
		return creater;
	}

}
