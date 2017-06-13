package com.lzairport.ais.service.aodb.impl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IFlightDisPatchItemDao;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatchItem;

import com.lzairport.ais.service.aodb.IFlightDisPatchItemService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.utils.SYS_VARS.OutIn;

/**
 * 航班调度环节项目Service接口的实现类，返回给定航班的调度环节
 * @author ZhangYu
 * @version 0.9a 19/11/14
 * @since JDK 1.6
 *
 */

@Stateless
public class FlightDisPatchItemService extends Service<Integer,FlightDisPatchItem> implements
	IFlightDisPatchItemService {

	
	@EJB
	public void setFlightDisPatchItemDao(IFlightDisPatchItemDao flightDisPatchItemDao){
		setDao(flightDisPatchItemDao);
	}

	@Override
	public List<FlightDisPatchItem> FindFlightDisPaths(Flight flight) {
		// TODO Auto-generated method stub
		List<FlightDisPatchItem> flightDisPatchItems = dao.getAll();
		List<FlightDisPatchItem> result =new ArrayList<FlightDisPatchItem>();
		for(FlightDisPatchItem disPatchItem:flightDisPatchItems){
			if (disPatchItem.getIsOffIn().equals(flight.getIsOutIn())){
				result.add(disPatchItem);
			}else if (disPatchItem.getIsOffIn().equals(OutIn.Srd)){
				result.add(disPatchItem);
			}
		}
		return result;
	}

}
