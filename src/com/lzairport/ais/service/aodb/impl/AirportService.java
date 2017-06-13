package com.lzairport.ais.service.aodb.impl;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IAirportDao;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.service.aodb.IAirportService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.utils.SYS_VARS;


/**
 * 机场Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class AirportService extends Service<Integer, Airport> implements
		IAirportService {

	//新老三字代码转换定义
	private static List<String> OLD_CITY_CODE = Arrays.asList( 
			"SIA","LHW");
	
	private static List<String> NEW_CITY_CODE =Arrays.asList(
			"XIY","ZGC");
	
	@EJB
	public void setAirportDao(IAirportDao airportDao){
		setDao(airportDao);
	}

	@Override
	public Airport getTransitDomAirport() {
		// TODO Auto-generated method stub
		return dao.findByFieldSingle(Airport.THREECHARCODE, "999");
	}

	@Override
	public Airport getLocalAirport() {
		// TODO Auto-generated method stub
		return dao.findByFieldSingle(Airport.THREECHARCODE, SYS_VARS.LocalAirportThreeCode);
	}

	@Override
	public Airport findByFieldSingle(String field, Object value) {
		int switchIndex = OLD_CITY_CODE.indexOf(value.toString().trim());
		if (switchIndex != -1){
			value = NEW_CITY_CODE.get(switchIndex);
			System.out.println(value);
		}
		
		return super.findByFieldSingle(field, value);
	}
	
	
	

}
