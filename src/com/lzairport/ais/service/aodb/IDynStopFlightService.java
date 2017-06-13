package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.DynStopFlight;
import com.lzairport.ais.service.IService;

/**
 * 航班动态经停的Service的接口
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDynStopFlightService extends IService<Integer, DynStopFlight> {

}
