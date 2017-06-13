package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.DynFlightDisPatch;
import com.lzairport.ais.service.IService;

/**
 * 航班动态调度环节的Service接口
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDynFlightDisPatchService extends
		IService<Integer, DynFlightDisPatch> {

}
