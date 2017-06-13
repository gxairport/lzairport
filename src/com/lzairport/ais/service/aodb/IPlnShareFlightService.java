package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.PlnShareFlight;
import com.lzairport.ais.service.IService;

/**
 * 共享航班计划的实体类的Dao接口
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IPlnShareFlightService extends
		IService<Integer, PlnShareFlight> {

}
