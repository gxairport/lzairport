package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IPlnFlightLoadDao;
import com.lzairport.ais.models.aodb.PlnFlightLoad;
import com.lzairport.ais.service.aodb.IPlnFlightLoadService;
import com.lzairport.ais.service.impl.Service;



/**
 *航班计划的配载的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 04/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class PlnFlightLoadService extends Service<Integer, PlnFlightLoad>
		implements IPlnFlightLoadService {

	@EJB
	public void setPlnFlightLoadDao(IPlnFlightLoadDao plnFlightLoadDao){
		setDao(plnFlightLoadDao);
	}
}
