package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.HisStopFlight;
import com.lzairport.ais.service.IService;

/**
 * 航班经停历史的Service接口
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IHisStopFlightService extends IService<Integer, HisStopFlight> {

}
