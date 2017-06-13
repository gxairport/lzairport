package com.lzairport.ais.service.aodb.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IDynFlightDao;
import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.DynFlightDisPatch;
import com.lzairport.ais.models.aodb.DynFlightLoad;
import com.lzairport.ais.models.aodb.DynPayingPassenger;
import com.lzairport.ais.models.aodb.DynStopFlight;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.service.aodb.IDynFlightService;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 
 * FileName      DynFlightService.java
 * @Description  航班动态的Service的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate:  09/05/15
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-21      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class DynFlightService extends FlightService<Integer, DynFlight>
		implements IDynFlightService {

	@EJB
	private IFlightStateService flightStateService;

	@EJB
	private IHisFlightService hisFlightService;

	/**
	 * 误差最大时间，用于判断采用那种飞行时间
	 */
	private static int SetdeviationMinute = 20;

	/**
	 * 航班起飞次数，用于平均计算飞行时间之用
	 */
	private static int FlightTakeOffNum = 5;

	

	

	@EJB
	public void setDynFlightDao(IDynFlightDao dynFlightDao) {
		setDao(dynFlightDao);
	}

	/**
	 * 将一个航班动态归档到历史数据里，并删除该航班动态
	 * 
	 * @param dynFlight
	 *            航班动态实体
	 * @param cover
	 *            覆盖标志
	 * @throws Exception 
	 */
	private void DynToHis(DynFlight dynFlight, Boolean cover) throws Exception {

		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[] { HisFlight.EXECDATE, "=",
				dynFlight.getExecDate(), SYS_VARS.LinkSqlAnd,
				HisFlight.DEPAIRPORT, "=", dynFlight.getDepAirport(),
				SYS_VARS.LinkSqlAnd, HisFlight.ARRAIRPORT, "=",
				dynFlight.getArrAirport(), SYS_VARS.LinkSqlAnd,
				HisFlight.FLIGHTNO, "=", dynFlight.getFlightNO() });
		HisFlight hisFlight = hisFlightService
				.findByConditionSingle(conditions);

		// 添加新航班标志
		Boolean addNewFlight = false;

		if (hisFlight == null) {
			hisFlight = new HisFlight();
			addNewFlight = true;
		}

		// 如果是添加的新航班或者覆盖标志为True
		if ((addNewFlight) || (cover)) {
			// 航班基本信息复制
			ObjectMethodUtil.copybean(hisFlight,dynFlight);
			hisFlight.setId(0);
			
			// 载量复制
			Set<DynFlightLoad> dynFlightLoads = dynFlight.getLoads();
			copyFlightLoads(hisFlight, dynFlightLoads);

			// 服务环节复制
			Set<DynFlightDisPatch> dynFlightDisPatchs = dynFlight.getFlightDisPatchs();
			copyFlightDisPatchs(hisFlight, dynFlightDisPatchs);

			// 付费旅客复制
			Set<DynPayingPassenger> dynPayingPassengers = dynFlight.getPayingPassengers();
			copyFlightPayingPassengers(hisFlight, dynPayingPassengers);
			
			//经停航班创建
			Set<DynStopFlight> dynStopFlights =  dynFlight.getStopFlights();
			createStopFlights(hisFlight, dynStopFlights);
			
			//设置航空公司
			if ((hisFlight.getAircraft() != null)&&(hisFlight.getAircraft().getCarrier() != null)){
				hisFlight.setAirlines(hisFlight.getAircraft().getCarrier().getAirlines());
			}
			
			//设置航线性质
			if ((hisFlight.getDepAirport() != null)&&(hisFlight.getArrAirport() != null)){
				if ((hisFlight.getDepAirport().getAreaAttribute().getCnShortName().equals("国际"))||
						(hisFlight.getArrAirport().getAreaAttribute().getCnShortName().equals("国际"))){
					hisFlight.setAttribute(attributeService.getIntAttribute());
				}else if ((hisFlight.getDepAirport().getAreaAttribute().getCnShortName().equals("地区"))||
						(hisFlight.getArrAirport().getAreaAttribute().getCnShortName().equals("地区"))){
					hisFlight.setAttribute(attributeService.getRegAttribute());
				}else{
					hisFlight.setAttribute(attributeService.getDomAttribute());
				}
			}
			//根据飞机的基本数据设置航班的可用座位、可供业载
			if (hisFlight.getAircraft() != null){
				hisFlight.setAvailableLoad(hisFlight.getAircraft().getAvailableLoad());
				hisFlight.setAvailableSeat(hisFlight.getAircraft().getAvailableSeat());
			}

			//设置审核为False
			hisFlight.setApprove(false);
			if (dynFlight.getTakeOffLandInCount() < 1 && dynFlight.getState().equals(flightStateService.getLandInState())){
				hisFlight.setTakeOffLandInCount(1);
			}else if(dynFlight.getState().equals(flightStateService.getCnlState())){
				hisFlight.setTakeOffLandInCount(0);
			}
			hisFlight.setBigFlightNO(hisFlightService.getBigFlightNo(hisFlight));
			hisFlight = hisFlightService.add(hisFlight);
			hisFlightService.updateExtraParams(hisFlight);
			
		}
		this.remove(dynFlight);
	}

	@Override
	public String checkFlightCompelete(DynFlight flight) {
		String errStr = "";

		if (!flight.getState().equals(flightStateService.getCnlState())) {
			// 航班不等于取消,检查航班的数据完整性，如果是取消可以忽略检查
			if (!flight.getState().equals(flightStateService.getLandInState())) {
				errStr += "不是落地状态\n ";
			}

			if (flight.getActualTakeOffTime() == null) {
				errStr += "起飞时间为空\n";
			}

			if (flight.getActualLandInTime() == null) {
				errStr += "落地时间为空\n";
			}

			if (flight.getLinkFlight() == null) {
				errStr += "关联航班为空\n";
			}

			if (flight.getLoads() == null) {
				errStr += "载量为空\n";
			}

			if (flight.getGuestRate() == 0 || flight.getGuestRate() > 100) {
				errStr += "客座率为0或者大于100\n ";
			}

		}

		if (!errStr.equals("")) {
			errStr = "\n" + flight + " 数据不完整，原因如下:\n" + errStr;
			errStr += "-------------------------------------------------------------------------------";
		}
		return errStr;

	}

	private static String AvailableTakeOff = " PLN FPL 备降落地 返航落地 延误 前场起飞 本场起飞 备降起飞 返航起飞 ";

	@Override
	public DynFlight setFlightTakeOff(DynFlight flight, Date takeOffTime)
			throws FlightServiceException {

		long calcMillis = 0;
		int count = 0;
		long fplMillis = DateTimeUtil.HHMMToMillisecond(flight.getFlightTime());
		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (fplMillis == 0) {
			// 如果没有有FPL记录的飞行时间
			throw new FlightServiceException(flight.getFlightNO()
					+ "更新起飞状态失败\r" + "原因：航班预计飞行时间为0");
		} else if (AvailableTakeOff.indexOf(stateStr) != -1) {
			// 如果航班状态在起飞许可中,设置起飞时间,航班起飞状态
			if (flight.getState().equals(
					flightStateService.getAlternateLandInState())) {
				// 备降航班起飞处理,不计算平均飞行时间,直接采用FPL的飞行时间
				flight.setState(flightStateService.getAlternateTakeOffState());
				flight.setAlternateActualTakeOffTime(takeOffTime);
			} else {
				if (flight.getState().equals(
						flightStateService.getReturnLandInState())) {
					// 返航航班起飞处理
					flight.setState(flightStateService.getReturnTakeoffState());
				} else if (flight.getDepAirport().getThreeCharCode()
						.equals(SYS_VARS.LocalAirportThreeCode)) {
					// 如果是本场起飞
					flight.setState(flightStateService.getLocalTakeOffState());
				} else {
					// 前场起飞
					flight.setState(flightStateService
							.getPreviousTakeOffState());
				}
				flight.setActualTakeOffTime(takeOffTime);

				// 查找最近FlightTakeOffNum次该航线的飞行时间,并计算平均飞行时间
				QueryConditions conditions = new QueryConditions();
				conditions.setExpresstion(new Object[] { HisFlight.FLIGHTNO,
						"=", flight.getFlightNO(), SYS_VARS.LinkSqlAnd,
						HisFlight.DEPAIRPORT, "=", flight.getDepAirport()});
				AisOrder order = new AisOrder();
				order.setName(HisFlight.EXECDATE);
				order.setSortMode(SYS_VARS.DescSORT);
				conditions.setOrders(new AisOrder[] { order });
				conditions.setMax(FlightTakeOffNum);
				List<HisFlight> hisFlights = hisFlightService
						.findByConditionAll(conditions);
				for (HisFlight hisFlight : hisFlights) {
					if ((hisFlight.getActualTakeOffTime() != null) &&(hisFlight.getActualLandInTime() != null)){
						count++;
						calcMillis = calcMillis+DateTimeUtil.MillisecondBetween(hisFlight.getActualTakeOffTime(),
								hisFlight.getActualLandInTime());
					}
				}

				if (count != 0) {
					calcMillis = calcMillis / count;
				}
			}

			// 计算FPL的飞行时间的误差
			long deviationMinute = Math.abs(fplMillis - calcMillis)
					/ (1000 * 60);
			// 设置预计落地的时间，如果大于最大误差时间，则用FPL的飞行时间，如果小于误差，则用计算的平均飞行时间
			if ((calcMillis != 0) && (deviationMinute < SetdeviationMinute)) {
				flight.setAlterateLandinTime(DateTimeUtil
						.roundDate(DateTimeUtil.addMillisecond(takeOffTime,
								calcMillis)));
			} else {
				flight.setAlterateLandinTime(DateTimeUtil
						.roundDate(DateTimeUtil.addMillisecond(takeOffTime,
								fplMillis)));
			}
			return this.update(flight);
		} else {
			// 如果航班状态不在起飞许可中，抛出异常
			throw new FlightServiceException(flight.getFlightNO()
					+ "更新起飞状态失败\r" + "原因：航班状态不是" + AvailableTakeOff + "中的一种");
		}

	}

	private static String AvailableAlterateTakeOff = " PLN FPL 备降落地 返航落地 延误 ";

	@Override
	public DynFlight setFlighttAlterateTakeOffTime(DynFlight flight, Date takeOffTime)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableAlterateTakeOff.indexOf(stateStr) != -1) {
			flight.setAlteraTeakeOffTime(takeOffTime);
			update(flight);
		} else {
			throw new FlightServiceException(flight.getFlightNO()
					+ "变更航班预计起飞时间失败\r" + "原因：航班状态不是" + AvailableAlterateTakeOff
					+ "中的一种");
		}

		return flight;
	}

	private static String AvailableLandIn = " 前场起飞 本场起飞 备降起飞 备降中 返航起飞 返航中 备降落地 返航落地 落地 ";

	@Override
	public DynFlight setFlightLandIn(DynFlight flight, Date landInTime)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";
		

		if (AvailableLandIn.indexOf(stateStr) != -1) {

			//如果是航班起飞状态落地，起降架次+1
			if (flight.getState().equals(flightStateService.getLocalTakeOffState()) ||
					flight.getState().equals(flightStateService.getPreviousTakeOffState())||
					flight.getState().equals(flightStateService.getReturnState())||
					flight.getState().equals(flightStateService.getReturnTakeoffState())
					){
				flight.setTakeOffLandInCount(flight.getTakeOffLandInCount()+1);
			}
			
			if (flight.getState()
					.equals(flightStateService.getAlternateState())) {
				// 航班备降落地
				flight.setState(flightStateService.getAlternateLandInState());
				flight.setAlternateActualLandInTime(landInTime);
			} else if (flight.getState().equals(
					flightStateService.getReturnState())) {
				// 航班返航落地
				flight.setState(flightStateService.getReturnLandInState());
				flight.setActualTakeOffTime(null);

			} else {
				//如果航班状态是备降落地 返航落地，则只更新时间
				if (!flight.getState().equals(flightStateService.getReturnLandInState())
						&&!flight.getState().equals(flightStateService.getAlternateLandInState())){
					flight.setState(flightStateService.getLandInState());
				}
				flight.setActualLandInTime(landInTime);
			}
			super.update(flight);
		} else {
			// 如果航班状态不在落地许可中，抛出异常
			throw new FlightServiceException(flight.getFlightNO()
					+ "更新落地状态失败\r" + "原因：航班状态不是" + AvailableLandIn + "中的一种");

		}
		return flight;

	}

	private static String AvailableAlterateLandIn = " 前场起飞 本场起飞 备降起飞 备降中 返航起飞 返航中 ";

	@Override
	public DynFlight setFlightAlterateLandInTime(DynFlight flight, Date landInTime)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableAlterateLandIn.indexOf(stateStr) != -1) {
			flight.setAlterateLandinTime(landInTime);
			update(flight);
		} else {
			throw new FlightServiceException(flight.getFlightNO()
					+ "变更航班预计落地时间失败\r" + "原因：航班状态不是" + AvailableAlterateLandIn
					+ "中的一种");
		}
		return flight;
	}

	private static String AvailableDly = " PLN FPL 前场起飞 本场起飞 备降起飞 备降中 返航起飞 返航中 备降落地 返航落地  ";

	@Override
	public DynFlight setFlightDly(DynFlight flight, DelayReason reason,
			Date alteraTeakeOffTime) throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableDly.indexOf(stateStr) != -1) {
			flight.setNormal(false);
			flight.setAlteraTeakeOffTime(alteraTeakeOffTime);
			flight.setInternalDelayReason(reason);
			flight.setState(flightStateService.getDlyState());
			super.update(flight);
		} else {
			// 如果航班状态不在发布延误许可中，抛出异常
			throw new FlightServiceException(flight.getFlightNO()
					+ "更新延误状态失败\r" + "原因：航班状态不是" + AvailableDly + "中的一种");
		}
		return flight;

	}

	private static String AvailableCNL = " PLN FPL 延误 备降落地 返航落地 ";

	@Override
	public DynFlight setFlightCnl(DynFlight flight, DelayReason reason)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableCNL.indexOf(stateStr) != -1) {
			flight.setNormal(false);
			flight.setState(flightStateService.getCnlState());
			flight.setInternalDelayReason(reason);
			super.update(flight);
		} else {
			// 如果航班状态不在发布延误状态中，抛出异常
			throw new FlightServiceException(flight.getFlightNO()
					+ "更新取消状态失败\r" + "原因：航班状态不是" + AvailableCNL + "中的一种");
		}
		return flight;

	}

	private static String AvailableAlternate = " 前场起飞 本场起飞 备降起飞 备降中 返航起飞 ";

	@Override
	public DynFlight setFlightAlternate(DynFlight flight, DelayReason reason,
			Airport alternateAirport) throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableAlternate.indexOf(stateStr) != -1) {
			flight.setNormal(false);
			flight.setState(flightStateService.getAlternateState());
			flight.setAlternateAirport(alternateAirport);
			flight.setInternalDelayReason(reason);
			super.update(flight);

		} else {
			// 如果航班状态不在发布备降状态中，抛出异常
			throw new FlightServiceException(flight.getFlightNO()
					+ "更新备降状态失败\r" + "原因：航班状态不是" + AvailableAlternate + "中的一种");

		}
		return flight;

	}

	private static String AvailableReturn = " 前场起飞 本场起飞 备降起飞  返航起飞 返航中 ";

	@Override
	public DynFlight setFlightReturn(DynFlight flight, DelayReason reason)
			throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableReturn.indexOf(stateStr) != -1) {
			flight.setNormal(false);
			flight.setState(flightStateService.getReturnState());
			flight.setInternalDelayReason(reason);
			super.update(flight);

		} else {
			// 如果航班状态不在发布返航状态中，抛出异常
			throw new FlightServiceException(flight.getFlightNO()
					+ "更新返航状态失败\r" + "原因：航班状态不是" + AvailableReturn + "中的一种");

		}
		return flight;

	}

	private static String AvailableFPL = " PLN FPL 延误 备降落地 返航落地 ";

	@Override
	public DynFlight setFlightFPL(DynFlight flight) throws FlightServiceException {

		String stateStr = " " + flight.getState().getCnShortName() + " ";

		if (AvailableFPL.indexOf(stateStr) != -1) {
			if (flight.getState().equals(flightStateService.getPlnState())) {
				flight.setState(flightStateService.getFPLState());
			}
			super.update(flight);
		} else {
			// 如果航班状态不在发布FPL中，抛出异常
			throw new FlightServiceException(flight.getFlightNO()
					+ "更新FPL状态失败\r" + "原因：航班状态不是" + AvailableFPL + "中的一种");
		}

		return flight;
	}

	@Override
	public void flightChangeAircraft(DynFlight flight, Aircraft aircraft) {

		// 更换飞机
		flight.setAircraft(aircraft);
		update(flight);
		// 查找关联的航班，更换飞机
		DynFlight linkFlight = getLinkFlight(flight);
		while (linkFlight != null) {
			linkFlight.setAircraft(aircraft);
			update(linkFlight);
			linkFlight = getLinkFlight(linkFlight);
		}
	}

	@Override
	public void convertFlights(Date startDate, Boolean cover,Boolean forcedImport) throws Exception {

		// 查找指定日期的航班
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[] { DynFlight.EXECDATE, "<=",
				startDate });
		List<DynFlight> flights = findByConditionAll(conditions);

		for (DynFlight dynFlight : flights) {
			// 如果航班检查完整，或者需要强制导入
			if (checkFlightCompelete(dynFlight).equals("") || forcedImport) {
				DynToHis(dynFlight, cover);
			}
		}
	}

	@Override
	public boolean CheckFlightDelay(DynFlight flight) {
		// 如果航班状态还在未起飞的状态内
		if (flight.getState().equals(flightStateService.getPlnState())
				|| flight.getState().equals(flightStateService.getFPLState())) {

			if ((flight.getDepAirport() != null) && (flight.getPlanTakeOffTime() != null)){
				// 取得机场的判断延误时长 ，一般机场 15分钟 北京 上海30分钟
				int IntervalDelayTime = flight.getDepAirport()
						.getIntervalDelayTime();
				// 航班延误判断时间，大于此时间航班延误，小于此时间航班正常
				Date notMaxDelayTime = DateTimeUtil.addMillisecond(
						flight.getPlanTakeOffTime(), IntervalDelayTime);
				
				
				if (new Date().compareTo(notMaxDelayTime) == 1) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean CheckLocalFPLDelay(DynFlight flight) {
		// 如果航班是计划状态且是本场起飞航班
		if	((flight.getDepAirport() != null)&& 
		(flight.getState().equals(flightStateService.getPlnState())
				&& flight.getDepAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode))) {
			// 航班未发FPL判断的时间，大于此时间航班未发FPL返回True,小于返回False
			Date notMaxDelayFPLTime = DateTimeUtil.addMillisecond(
					flight.getPlanTakeOffTime(), SYS_VARS.FlightFPLSecond);
		    
			if (new Date().compareTo(notMaxDelayFPLTime) == 1) {
				return true;
			}
		}
		return false;
	}

	

}
