package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IPlnPayingPassengerDao;
import com.lzairport.ais.models.aodb.PlnPayingPassenger;
import com.lzairport.ais.service.aodb.IPlnPayingPassengerService;
import com.lzairport.ais.service.impl.Service;


/**
 * 航班计划付费旅客实体类的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnPayingPassengerService extends
		Service<Integer, PlnPayingPassenger> implements IPlnPayingPassengerService {

	@EJB
	public void setPlnPayingPassengerDao(IPlnPayingPassengerDao plnPayingPassengerDao){
		setDao(plnPayingPassengerDao);
	}

}
