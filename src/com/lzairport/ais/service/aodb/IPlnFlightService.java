package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.PlnFlight;

/**
 * 航班计划的Service接口
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IPlnFlightService extends IFlightService<Integer, PlnFlight> {

}
