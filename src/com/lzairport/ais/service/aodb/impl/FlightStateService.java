package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IFlightStateDao;
import com.lzairport.ais.models.aodb.FlightState;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.impl.Service;

/**
 * 航班状态Service实现类，用以返回定义好的航班各状态
 * @author ZhangYu
 * @version 0.9a 17/11/14
 * @since JDK 1.6
 *
 */

@Stateless
public class FlightStateService extends Service<Integer,FlightState> implements IFlightStateService {

	@EJB
	public void setFlightStateDao(IFlightStateDao flightStateDao){
		setDao(flightStateDao);
	}

	@Override
	public FlightState getPlnState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "PLN");
	}

	@Override
	public FlightState getPreviousTakeOffState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "前场起飞");
	}

	@Override
	public FlightState getLocalTakeOffState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "本场起飞");
	}

	@Override
	public FlightState getAlternateTakeOffState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "备降起飞");
	}

	@Override
	public FlightState getReturnTakeoffState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "返航起飞");
	}

	@Override
	public FlightState getLandInState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "落地");
	}

	@Override
	public FlightState getAlternateLandInState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "备降落地");
	}

	@Override
	public FlightState getReturnLandInState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "返航落地");
	}

	@Override
	public FlightState getAlternateState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "备降中");
	}

	@Override
	public FlightState getReturnState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "返航中");
	}

	@Override
	public FlightState getDlyState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "延误");
	}

	@Override
	public FlightState getCnlState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "取消");
	}


	@Override
	public FlightState getFPLState() {
		// TODO Auto-generated method stub
		return (FlightState) dao.findByFieldSingle(FlightState.CNSHORTNAME, "FPL");
	}

}
