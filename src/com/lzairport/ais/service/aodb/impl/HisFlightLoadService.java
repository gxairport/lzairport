package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IHisFlightLoadDao;
import com.lzairport.ais.models.aodb.HisFlightLoad;
import com.lzairport.ais.service.aodb.IHisFlightLoadService;
import com.lzairport.ais.service.impl.Service;

/**
 * 航班配载历史的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 04/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class HisFlightLoadService extends Service<Integer, HisFlightLoad>
		implements IHisFlightLoadService {

	@EJB
	public void setHisFlightLoadDao(IHisFlightLoadDao hisFlightLoadDao){
		setDao(hisFlightLoadDao);
	}

}
