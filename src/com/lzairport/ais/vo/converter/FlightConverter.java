package com.lzairport.ais.vo.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightLoad;
import com.lzairport.ais.utils.AisBeanUtils;
import com.lzairport.ais.vo.FlightLoadVO;
import com.lzairport.ais.vo.FlightVO;

/**
 * 
 * FileName      FlightConverter.java
 * @Description  TODO航班信息Vo与各航班实体转换类(不包括HisFlight实体类)
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年9月27日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年9月27日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class FlightConverter extends BaseConverter<Flight,FlightVO> implements IConverter<Flight,FlightVO>{

	@Override
	public void copyDiffPropertiesFromVO2PO(Flight target, FlightVO source) throws Exception {
		
	}

	@Override
	public void copyDiffPropertiesFromPO2VO(FlightVO target, Flight source) throws Exception {
		
		if (source.getDepAirport() != null ){
			target.setDepAP_Id(source.getDepAirport().getThreeCharCode());
			target.setDepAP_Name(source.getDepAirport().getCnName());
		}

		
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
		
		Set<? extends FlightLoad>  srcLoads = source.getLoads();
		if (srcLoads != null){
			List<FlightLoadVO> trgLoads = new ArrayList<FlightLoadVO>();
			for (FlightLoad srcload:srcLoads){
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
	protected Flight findEntity(FlightVO vo) {
		// TODO Auto-generated method stub
		return null;
	}



}
