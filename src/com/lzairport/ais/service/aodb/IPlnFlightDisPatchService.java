package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.PlnFlightDisPatch;
import com.lzairport.ais.service.IService;


/**
 * 航班计划调度环节的Service接口
 * @author ZhangYu
 * @version 0.9a 16/05/15
 * @since JDK 1.6
 *
 */


@Remote
public interface IPlnFlightDisPatchService extends
		IService<Integer, PlnFlightDisPatch> {

}
