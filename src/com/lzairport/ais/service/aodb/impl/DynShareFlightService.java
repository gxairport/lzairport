package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDynShareFlightDao;
import com.lzairport.ais.models.aodb.DynShareFlight;
import com.lzairport.ais.service.aodb.IDynShareFlightService;
import com.lzairport.ais.service.impl.Service;

/**
 * 航班动态共享的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class DynShareFlightService extends Service<Integer, DynShareFlight>
		implements IDynShareFlightService {

	@EJB
	public void setDynShareFlightDao(IDynShareFlightDao dynShareFlightDao){
		setDao(dynShareFlightDao);
	}

}
