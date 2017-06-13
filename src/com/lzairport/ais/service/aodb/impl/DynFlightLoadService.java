package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IDynFlightLoadDao;
import com.lzairport.ais.models.aodb.DynFlightLoad;
import com.lzairport.ais.service.aodb.IDynFlightLoadService;
import com.lzairport.ais.service.impl.Service;

/**
 * 航班动态的配载Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class DynFlightLoadService extends Service<Integer, DynFlightLoad>
		implements IDynFlightLoadService {

	@EJB
	public void setDynFlightLoadDao(IDynFlightLoadDao dynFlightLoadDao){
		setDao(dynFlightLoadDao);
	}

}
