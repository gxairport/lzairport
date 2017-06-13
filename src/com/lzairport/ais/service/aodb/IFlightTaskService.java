package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.service.IService;

/**
 * 飞机任务的Service接口
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IFlightTaskService extends IService<Integer, FlightTask> {
	
	/**
	 * 
	 * @Description: 返回补班的实体类
	 * @return
	 */
	public FlightTask getSupplementTask();

}
