/**
 * 
 */
package com.lzairport.ais.vo.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.models.aodb.FlightState;
import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.aodb.HisFlightLoad;
import com.lzairport.ais.service.aodb.IAircraftService;
import com.lzairport.ais.service.aodb.IAirlinesService;
import com.lzairport.ais.service.aodb.IAirportService;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.aodb.IDelayReasonService;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.aodb.IFlightTaskService;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.utils.AisBeanUtils;
import com.lzairport.ais.vo.FlightVO;
import com.lzairport.ais.vo.FlightLoadVO;

/**
 * FileName      HisFlightConverter.java
 * @Description  历史航班信息值对象和航班实体转换类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015年10月3日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015年10月3日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class HisFlightConverter extends BaseConverter<HisFlight,FlightVO> implements IConverter<HisFlight,FlightVO> {
	
	
	@EJB
	private IHisFlightService flightService;
	
	@EJB
	private IAirlinesService airlinesService;
	
	@EJB
	private IAirportService airportService;
	
	@EJB
	private IFlightTaskService taskService;
	
	@EJB
	private IAreaAttributeService attributeService;
	
	@EJB
	private IAircraftService aircraftService;
	
	@EJB
	private IFlightStateService stateService;
	
	@EJB
	private IDelayReasonService dlyReasonService;
	


	
	@Override
	public void copyDiffPropertiesFromVO2PO(HisFlight target, FlightVO source) throws Exception {
		
		
		
		if (source.getDepAP_Id() != null ){
			target.setDepAirport(airportService.
					findByFieldSingle(Airport.THREECHARCODE,source.getDepAP_Id()));
		}
		
		target.setAvailableLoad(source.getAvb_Load());
		target.setAvailableSeat(source.getAvb_SeatNum());
		
		
		if (source.getTask_Id() != null){
			target.setTask(taskService.findByFieldSingle(FlightTask.CODE,source.getTask_Id() ));
		}
		
		if (source.getArrAP_Id() != null){
			target.setArrAirport(airportService.
					findByFieldSingle(Airport.THREECHARCODE,source.getArrAP_Id()));
		}
		if(source.getAttribute_Id() != 0){
			target.setAttribute(attributeService.findByFieldSingle
					(AreaAttribute.ID,source.getAttribute_Id()));
		}
		if(source.getAirlines_Id() != null){
			target.setAirlines(airlinesService.
					findByFieldSingle(Airlines.CORPTWOCHARCODE,source.getAirlines_Id() ));
		}
		if (source.getAircraft_NO() != null){
			Aircraft aircraft = aircraftService.findByFieldSingle(Aircraft.CRAFTNO, source.getAircraft_NO());
			//如果有这个飞机且数据有更改，则更新此飞机数据
			if (aircraft != null &&(aircraft.getMaxSeat()!=source.getMax_SeatNum()
					||aircraft.getMaxLoad() != source.getMax_Load()||
				    aircraft.getAvailableSeat()!=source.getAvb_SeatNum()||
					aircraft.getAspType()==null||!aircraft.getAspType().equals(source.getAspType()))){
				aircraft.setMaxSeat(source.getMax_SeatNum());
				aircraft.setAvailableSeat(source.getAvb_SeatNum());
				aircraft.setMaxLoad(source.getMax_Load());
				aircraft.setAvailableSeat(source.getAvb_SeatNum());
				aircraft.setAspType(source.getAspType());
				aircraftService.update(aircraft);
			}
		}
		if (source.getState_Id() != 0){
			target.setState(stateService.
					findByFieldSingle(FlightState.ID, source.getState_Id()));
		}
		if (source.getDlyReason_Id() != 0){
			target.setInternalDelayReason(dlyReasonService.findByFieldSingle(DelayReason.ID, 
					source.getDlyReason_Id()));
		}

	
		List<FlightLoadVO>  srcLoads = source.getLoadsVO();
		if (srcLoads != null){
			Set<HisFlightLoad> trgLoads = new HashSet<HisFlightLoad>();
			for (FlightLoadVO srcload:srcLoads){
				if (srcload != null){
					HisFlightLoad trgLoad = new HisFlightLoad();
					AisBeanUtils.copyProperties(trgLoad, srcload);
					
					if (srcload.getDepAP_Id() != null){
						trgLoad.setLd_DepAirport(airportService.
								findByFieldSingle(Airport.THREECHARCODE,srcload.getDepAP_Id()));
					}
					
					if (srcload.getArrAP_Id() != null){
						trgLoad.setLd_ArrAirport(airportService.
								findByFieldSingle(Airport.THREECHARCODE,srcload.getArrAP_Id()));
					}
					trgLoads.add(trgLoad);
				}
			}
			target.setLoads(trgLoads);
		}
		
		
	}

	@Override
	public void copyDiffPropertiesFromPO2VO(FlightVO target, HisFlight source) throws Exception {

		if (source.getDepAirport() != null ){
			target.setDepAP_Id(source.getDepAirport().getThreeCharCode());
			target.setDepAP_Name(source.getDepAirport().getCnName());
		}
		
		target.setAvb_SeatNum(source.getAvailableSeat());
		target.setAvb_Load(source.getAvailableLoad());

		
		if (source.getTask() != null){
			target.setTask_Id(source.getTask().getCode());
			target.setTask_Name(source.getTask().getCnShortName());
		}
		
		if (source.getArrAirport() != null){
			target.setArrAP_Id(source.getArrAirport().getThreeCharCode());
			target.setArrAP_Name(source.getArrAirport().getCnName());
		}
		if(source.getAttribute() != null){
			target.setAttribute_Id((Integer) source.getAttribute().getId());
			target.setAttribute_Name(source.getAttribute().getCnShortName());
		}
		if(source.getAirlines() != null){
			target.setAirlines_Id(source.getAirlines().getCorpTwoCharCode());
			target.setAirlines_Name(source.getAirlines().getCnShortName());
		}
		if (source.getAircraft() != null){
			target.setAircraft_NO(source.getAircraft().getCraftno());
			target.setMax_SeatNum(source.getAircraft().getMaxSeat());
			target.setMax_Load(source.getAircraft().getMaxLoad());
			target.setAspType(source.getAircraft().getAspType());
			target.setCarrier_Name(source.getAircraft().getCarrier().getCnShortName());
		}
		if (source.getState() != null){
			target.setState_Id((Integer) source.getState().getId());
			target.setState_Name(source.getState().getCnShortName());
		}
		if (source.getInternalDelayReason() != null){
			target.setDlyReason_Id((Integer) source.getInternalDelayReason().getId());
			target.setDlyReason_Name(source.getInternalDelayReason().getCnShortName());
		}
		
		Set<HisFlightLoad>  srcLoads = source.getLoads();
		if (srcLoads != null){
			List<FlightLoadVO> trgLoads = new ArrayList<FlightLoadVO>();
			for (HisFlightLoad srcload:srcLoads){
				FlightLoadVO trgLoad = new FlightLoadVO();
				AisBeanUtils.copyProperties(trgLoad, srcload);
				if (srcload.getLd_DepAirport() != null){
					trgLoad.setDepAP_Id(srcload.getLd_DepAirport().getThreeCharCode());
				}
				
				if (srcload.getLd_ArrAirport() != null){
					trgLoad.setArrAP_Id(srcload.getLd_ArrAirport().getThreeCharCode());
				}
				trgLoads.add(trgLoad);
			}
			target.setLoadsVO(trgLoads);
		}
	}

	@Override
	protected HisFlight findEntity(FlightVO flightVO) {
		// TODO Auto-generated method stub
		return flightService.findByID(flightVO.getId());
	}

}
