package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.service.IService;

/**
 * 延误原因的Service接口
 * @author ZhangYu
 * @version 0.9a 28/09/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IDelayReasonService extends IService<Integer, DelayReason> {

}
