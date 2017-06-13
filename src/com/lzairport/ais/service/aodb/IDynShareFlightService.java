package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.DynShareFlight;
import com.lzairport.ais.service.IService;

/**
 * 航班动态共享的Service接口
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDynShareFlightService extends
		IService<Integer, DynShareFlight> {

}
