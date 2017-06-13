package com.lzairport.ais.service.aodb;

import java.util.List;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatchItem;
import com.lzairport.ais.service.IService;




/**
 * 航班DisPath环节Service接口
 * @author ZhangYu
 * @version 0.9a 14/11/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IFlightDisPatchItemService extends IService<Integer,FlightDisPatchItem> {

	
	/**
	 * 用于返回该航班所对应模版所有的调度环节
	 * @param flight
	 * @return
	 */
	public List<FlightDisPatchItem> FindFlightDisPaths(Flight flight);
	
}
