package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDelayReasonDao;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.service.aodb.IDelayReasonService;
import com.lzairport.ais.service.impl.Service;


/**
 * 延误原因的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class DelayReasonService extends Service<Integer, DelayReason> implements
		IDelayReasonService {

	@EJB
	public void setDelayReasonDao(IDelayReasonDao delayReasonDao){
		setDao(delayReasonDao);
	}

}
