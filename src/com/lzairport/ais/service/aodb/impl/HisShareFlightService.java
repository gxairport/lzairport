package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IHisShareFlightDao;
import com.lzairport.ais.models.aodb.HisShareFlight;
import com.lzairport.ais.service.aodb.IHisShareFlightService;
import com.lzairport.ais.service.impl.Service;


/**
 * 共享航班历史的Service接口实现类
 * @author ZhangYu
 * @version 0.9a 04/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class HisShareFlightService extends Service<Integer, HisShareFlight>
		implements IHisShareFlightService {

	@EJB
	public void setHisShareFlightDao(IHisShareFlightDao hisShareFlightDao){
		setDao(hisShareFlightDao);
	}

}
