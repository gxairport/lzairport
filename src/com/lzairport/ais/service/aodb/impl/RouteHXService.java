package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IRouteHXDao;
import com.lzairport.ais.models.aodb.RouteHX;
import com.lzairport.ais.service.aodb.IRouteHXService;
import com.lzairport.ais.service.impl.Service;


@Stateless
public class RouteHXService extends Service<Integer, RouteHX> implements IRouteHXService {
	
	@EJB
	public void setRouteDao(IRouteHXDao routeDao){
		setDao(routeDao);
	}
	
}
