package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IAircraftDao;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.service.aodb.IAircraftService;
import com.lzairport.ais.service.impl.Service;

/**
 * 飞机的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class AircraftService extends Service<Integer, Aircraft> implements
		IAircraftService {

	@EJB
	public void setAircraftDao(IAircraftDao aircraftDao){
		setDao(aircraftDao);
	}
	


	@Override
	public Aircraft update(Object obj) {
		Aircraft aircraft = (Aircraft) obj;
		setLastId(aircraft);
		return super.update(aircraft);
	}

	@Override
	public Aircraft add(Object obj) {
		// TODO Auto-generated method stub
		Aircraft aircraft = (Aircraft) obj;
		setLastId(aircraft); 
		return super.add(obj);
	}

	
	
}
