package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IDynPayingPassengerDao;
import com.lzairport.ais.models.aodb.DynPayingPassenger;
import com.lzairport.ais.service.aodb.IDynPayingPassengerService;
import com.lzairport.ais.service.impl.Service;


/**
 * 航班付费旅客实体类的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class DynPayingPassengerService extends
		Service<Integer, DynPayingPassenger> implements
		IDynPayingPassengerService {
	@EJB
	public void setDynPayingPassengerDao(IDynPayingPassengerDao dynPayingPassengerDao){
		setDao(dynPayingPassengerDao);
	}

}
