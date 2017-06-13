package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.PlnStopFlight;
import com.lzairport.ais.service.IService;



/**
 * 航班计划经停的Service接口
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IPlnStopFlightService extends IService<Integer, PlnStopFlight> {

}
