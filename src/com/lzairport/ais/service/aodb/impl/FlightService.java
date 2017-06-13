package com.lzairport.ais.service.aodb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;

import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.models.aodb.FlightDisPatchItem;
import com.lzairport.ais.models.aodb.FlightLoad;
import com.lzairport.ais.models.aodb.PayingPassenger;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.models.aodb.ScheduleStopFlight;
import com.lzairport.ais.models.aodb.ShareFlight;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.service.aodb.IAirportService;
import com.lzairport.ais.service.aodb.IFlightDisPatchItemService;
import com.lzairport.ais.service.aodb.IFlightService;
import com.lzairport.ais.service.aodb.IFlightTaskService;
import com.lzairport.ais.service.aodb.IScheduleFlightService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.vo.FlightVO;
import com.lzairport.ais.vo.converter.IConverter;

/**
 * 
 * FileName      FlightService.java
 * @Description  航班Service接口的接口，实现基本航班的各种基本操作
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 上午10:53:57 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-21      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

public abstract  class FlightService<K,E extends Flight>  extends BaseFlightService<K, E> implements IFlightService<K,E> {
	
	@EJB(beanName="FlightConverter")
	private IConverter<Flight,FlightVO> flightConverter;
	
	@EJB
	private IFlightDisPatchItemService flightDisPatchItemService;
	
	@EJB
	private IScheduleFlightService scheduleFlightService;
	
	@EJB
	private IFlightTaskService flightTaskService;
	
	
	@EJB
	protected IAirportService airportService;
	
	
	protected static  String parkBegin = "滑入机位";
	protected static  String parkEnd   = "许可开车";
	
	
	/**
	 * 将航班列表中关联清空
	 * @param flights
	 */
	protected void clearLinkFlights(List<E> flights) {
		for(E flight:flights){
			flight.setLinkFlight(null);
			update(flight);
		}	
	}
	
	@Override
	public void createFlightDisPatchs(Flight flight) throws FlightServiceException {

		//获取实际的FLIGHTDISPATCHS的类型
		@SuppressWarnings("unchecked")
		Class<FlightDisPatch> dispatchClass = (Class<FlightDisPatch>) ObjectMethodUtil.getFieldGenericType(flight, Flight.FLIGHTDISPATCHS,0);
		
		//获取航班的所有调度环节，添加到航班中
		Set<FlightDisPatch> flightDisPatchs = new HashSet<FlightDisPatch>();
		List<FlightDisPatchItem> disPatchItems = flightDisPatchItemService.FindFlightDisPaths(flight);
		for (FlightDisPatchItem disPatchItem:disPatchItems){
			FlightDisPatch disPatch;
			try {
				disPatch = dispatchClass.newInstance();
				disPatch.setDisPatchItem(disPatchItem);
				flightDisPatchs.add(disPatch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new FlightServiceException("航班在创建服务环节时出错");
			}
		}
		flight.setFlightDisPatchs(flightDisPatchs);
	}

	
	


	@Override
	@SuppressWarnings("unchecked")
	public void copyFlightDisPatchs(Flight flight,Set<? extends FlightDisPatch> ScrDispatchs) throws FlightServiceException {

	
		Class<FlightDisPatch> dispatchClass = (Class<FlightDisPatch>) ObjectMethodUtil.getFieldGenericType(flight, Flight.FLIGHTDISPATCHS, 0);
		
		//将航班服务列表写入航班
		Set<FlightDisPatch> destDispatchs = new HashSet<FlightDisPatch>();
		for (FlightDisPatch scrDispatch:ScrDispatchs){
			try {
				FlightDisPatch destDispatch = dispatchClass.newInstance();
				ObjectMethodUtil.copybean(destDispatch, scrDispatch);
				destDispatch.setId(0);
				destDispatchs.add(destDispatch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FlightServiceException("航班在复制服务环节时出错");
			}
		}
		
		flight.setFlightDisPatchs(destDispatchs);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void copyFlightPayingPassengers(Flight flight,	Set<? extends PayingPassenger> scrPassengers) throws FlightServiceException {
		
		
	Class<PayingPassenger>  passengerClass = (Class<PayingPassenger>) ObjectMethodUtil.getFieldGenericType(flight, Flight.PAYINGPASSENGERS, 0);
		
		//将航班付费旅客列表写入航班
		Set<PayingPassenger> destPassengers = new HashSet<PayingPassenger>();
		for (PayingPassenger scrPassenger:scrPassengers){
			try {
				PayingPassenger destPassenger = passengerClass.newInstance();
				ObjectMethodUtil.copybean(destPassenger, scrPassenger);
				destPassenger.setId(0);
				destPassengers.add(destPassenger);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FlightServiceException("航班在付费旅客环节时出错");
			}
		}
		
		flight.setPayingPassengers(destPassengers);
	}
	
	


	@SuppressWarnings("unchecked")
	@Override
	public void copyFlightLoads(Flight flight,Set<? extends FlightLoad> scrLoads) throws FlightServiceException {
		// TODO Auto-generated method stub
		Class<FlightLoad>  loadClass = (Class<FlightLoad>) ObjectMethodUtil.getFieldGenericType(flight, Flight.LOADS, 0);
		
		//将航班服务载量写入航班
		Set<FlightLoad> destLoads = new HashSet<FlightLoad>();
		for (FlightLoad scrLoad:scrLoads){
			try {
				FlightLoad destLoad = loadClass.newInstance();
				ObjectMethodUtil.copybean(destLoad,scrLoad);
				destLoad.setId(0);
				destLoads.add(destLoad);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FlightServiceException("航班在复制载量时出错");
			}
		}
		
		flight.setLoads(destLoads);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public E createFlight(Flight flight) throws Exception {
	
		
		//查找长期计划表，补全计划航班的数据
		
		
		ScheduleFlight scheduleFlight = scheduleFlightService.findScheduleFlight(flight);
		
		if ((scheduleFlight == null)&&(flightTaskService.getSupplementTask().equals(flight.getTask()))){
			//如果是补班航班，进行模糊查找
			scheduleFlight = scheduleFlightService.findScheduleFlightByZP(flight);
		}
	

		if (scheduleFlight != null){
			
			//补全各基本数据字段
			if (flight.getDepAirport() == null){
				flight.setDepAirport(scheduleFlight.getDepAirport());
			}
			if (flight.getArrAirport() == null){
				flight.setArrAirport(scheduleFlight.getArrAirport());
			}
			if (flight.getTask() == null){
				flight.setTask(scheduleFlight.getTask());
			}
			
			if ((flight.getPlanLandInTime() == null)&&(scheduleFlight.getPlanLandInTime()!= null)){
				flight.setPlanLandInTime(DateTimeUtil.addMillisecond(flight.getExecDate(), 
						DateTimeUtil.HHMMToMillisecond(scheduleFlight.getPlanLandInTime())));
			}
			if ((flight.getPlanTakeOffTime() == null)&&(scheduleFlight.getPlanTakeOffTime() != null)){
				flight.setPlanTakeOffTime(DateTimeUtil.addMillisecond(flight.getExecDate(), 
								DateTimeUtil.HHMMToMillisecond(scheduleFlight.getPlanTakeOffTime())));
			}
			
			//补全航线
			flight.setStartAirport(scheduleFlight.getStartAirport());
			flight.setStartPlanTakeOffTime(scheduleFlight.getStartPlanTakeOffTime());
			flight.setTerminalAirport(scheduleFlight.getTerminalAirport());
			flight.setTerminalPlanLandInTime(scheduleFlight.getTerminalPlanLandInTime());
					
			//根据长期航班计划 创建航班经停站
			Set<ScheduleStopFlight>  stopFlights = scheduleFlight.getStopFlights();
				createStopFlights(flight,stopFlights);
				
			}
		
			//设置航班为正常
			flight.setNormal(true);
				
			//初始化航班的oneToMany属性
			flight.setLoads(new HashSet<FlightLoad>());
			flight.setPayingPassengers(new HashSet<PayingPassenger>());
			flight.setShareFlights(new HashSet<ShareFlight>());
			
			if ((flight.getDepAirport() != null) && flight.getDepAirport().equals(airportService.getLocalAirport())){
				if ((flight.getArrAirport() != null)&&flight.getArrAirport().equals(airportService.getLocalAirport())){
					//如果起飞机场和落地机场都是本场
					flight.setIsOutIn(OutIn.Srd);
				}else{
					//如果起飞机场是本场，落地机场不为本场
					flight.setIsOutIn(OutIn.Dep);
				}
				
			}else if (flight.getArrAirport() !=null &&flight.getArrAirport().equals(airportService.getLocalAirport())) {
				//如果起飞机场不是本场，落地机场是本场
				flight.setIsOutIn(OutIn.Arr);
			}
		
			//设置航空公司
			if ((flight.getAircraft() != null)&&(flight.getAircraft().getCarrier() != null)){
				flight.setAirlines(flight.getAircraft().getCarrier().getAirlines());
			}
			
			//设置航线性质
			if ((flight.getDepAirport() != null)&&(flight.getArrAirport() != null)){
				if ((flight.getDepAirport().getAreaAttribute().getCnShortName().equals("国际"))||
						(flight.getArrAirport().getAreaAttribute().getCnShortName().equals("国际"))){
					flight.setAttribute(attributeService.getIntAttribute());
				}else if ((flight.getDepAirport().getAreaAttribute().getCnShortName().equals("地区"))||
						(flight.getArrAirport().getAreaAttribute().getCnShortName().equals("地区"))){
					flight.setAttribute(attributeService.getRegAttribute());
				}else{
					flight.setAttribute(attributeService.getDomAttribute());
				}
			}
			
			
			//如果计划起飞时间小于5点，说明这个航班是前一天的航班
			if (flight.getPlanTakeOffTime() != null){
				String strPlnTk = DateTimeUtil.dateToHH_MM(flight.getPlanTakeOffTime());
				if (strPlnTk.compareTo("05:00") < 0){
					flight.setExecDate(DateTimeUtil.addToDay(flight.getExecDate(), -1));
					
				}
			}
			
			
			QueryConditions conditions = new QueryConditions();
			//如果航班存在，删除航班重新添加
			conditions.setExpresstion(new Object[]{ Flight.FLIGHTNO,"=",flight.getFlightNO(),SYS_VARS.LinkSqlAnd,
						Flight.EXECDATE,"=",flight.getExecDate(),SYS_VARS.LinkSqlAnd,
						Flight.DEPAIRPORT,"=",flight.getDepAirport()
						});
			
			//创建该航班所对应的调度环节
			createFlightDisPatchs(flight);
			
			E sameFlight = findByConditionSingle(conditions);
			if ( sameFlight != null){
				remove(sameFlight);
			}
			
			if (flight.getIsOutIn() != null){
				flight =  add(flight);
			}
			return (E) flight;	
	}


	@Override
	public void flightDel(BaseFlight flight) throws FlightServiceException {

		List<E> linkFlights = findByFieldAll(DynFlight.LINKFLIGHT, flight.toString());
		for(E linkflight:linkFlights){
			linkflight.setLinkFlight(null);
			update(linkflight);
		}
		super.remove(flight);	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void linkFlights(Date startDate) throws FlightServiceException {
		

		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{Flight.EXECDATE,"<=",startDate});
		AisOrder order = new AisOrder();
		order.setName(Flight.PLANTAKEOFFTIME);
		order.setSortMode(SYS_VARS.AscSORT);
		conditions.setOrders(new AisOrder[]{order});
		List<E> flights = findByConditionAll(conditions);
		//清空关联
		clearLinkFlights(flights);
		for(E flight:flights){

			//记录需连接关联航班的信息
			//String flightInfo = flight.toString();
			//更新航班状态，主要是取得secondFlights段更新的状态
			flight = findByID((K) flight.getId());
			//如果航班没有关联，则对该航班的飞机所执行的所有本场航班都进行关联
			if ((flight != null) && (flight.getLinkFlight() == null)){
				if (flight.getDepAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode)){
					//如果是出港航班，说明关联航班是昨天的最后一个相关进港航班
					flight.setLinkFlight(SYS_VARS.LinkFlightDep);
				}else if (flight.getArrAirport().getThreeCharCode().equals(SYS_VARS.LocalAirportThreeCode)){
					//如果是进港航班，说明关联航班不是本场的航班，此航班是进港开始
					flight.setLinkFlight(SYS_VARS.LinkFlightArr);
				}else{
					throw new FlightServiceException(flight+"不是本场的航班，无法进行关联");				
				}
				update(flight);
				
				//查找执行该航班飞机的所有航班任务
				conditions.setExpresstion(new Object[]{Flight.EXECDATE,"<=",startDate,SYS_VARS.LinkSqlAnd,
						Flight.AIRCRAFT,"=",flight.getAircraft()});
				List<E> secondFlights = findByConditionAll(conditions);
				//如果该执行的飞机航班任务数大于1才进行关联
				if (secondFlights.size() > 1){
					
					Airport previousArrAirport = flight.getArrAirport();
					Flight previousFlight = flight;
					//将执行该航班飞机的所有航班进行关联
					for (E secondFlight:secondFlights) {
						if(secondFlight.getPlanTakeOffTime() == null){
							//如果计划起飞时间为零，无法进行关联
							throw new FlightServiceException(secondFlight+"起飞时间为空，无法进行关联");		
						}else if (secondFlight.getDepAirport().equals(previousArrAirport) &&
							secondFlight.getPlanTakeOffTime().compareTo(previousFlight.getPlanTakeOffTime())==1){
							//如果后续航班的起飞等于前一个的落地，则说明两个航班为关联
							String linkFlight = previousFlight.toString();
							secondFlight.setLinkFlight(linkFlight);
							//继续查找下一个航班的关联航班，此航班作为前一个航班继续查找
							previousArrAirport = secondFlight.getArrAirport();
							previousFlight = secondFlight;
							update(secondFlight);
						}else if (!flight.equals(secondFlight)) {
							//如果后续航班等于最开始的航班，说明是是最开始的航班，向后寻找就可以
							throw new FlightServiceException("无法找到"+secondFlight+"的关联航班");		
						}
					}
					
				}
			}else if (flight == null) {
				throw new FlightServiceException("的起飞、目的机场，航班日期可能为空，无法定位该航班");		
			}
		}                                                
	}



	@Override
	public E getLinkFlight(Flight flight) {
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{Flight.LINKFLIGHT,"=",flight.toString()});
		conditions.setFetchOneToMany("ALL");
		E linkFlight = findByConditionSingle(conditions);
		return linkFlight;
	}


	@Override
	public E getPreviousFlight(E flight) {
		// TODO Auto-generated method stub
	    String[] strs = flight.getLinkFlight().split("/");
	    if (strs.length == 3){
	    	String No   = strs[0];
	    	Date   date = DateTimeUtil.strToDate(strs[2]);
	    	OutIn outIn = OutIn.Arr;
	    	if (OutIn.Arr.equals(flight.getIsOutIn())){
	    		outIn = OutIn.Dep;
	    	}
			QueryConditions conditions = new QueryConditions();
			conditions.setExpresstion(new Object[]{Flight.FLIGHTNO,"=",No,"AND",Flight.ISOUTIN,"=",outIn,"AND",
					Flight.EXECDATE,"=",date});
			conditions.setFetchOneToMany("ALL");
			return findByConditionSingle(conditions);
	    }
		return null;
	}

	@Override
	public void convertFlights(Date startDate, Boolean cover, Boolean forcedImport) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception {
		List<E> flights = findByConditionAll(conditions);
		List<FlightVO> flightVOs = new ArrayList<FlightVO>();
		for (E flight:flights){
			flightVOs.add(flightConverter.getVOject(flight));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("records", flightVOs);
		result.put("totalCount", findCountByCondition(conditions));
		DataFetchResponseInfo responseInfo = new DataFetchResponseInfo();
		responseInfo.setTotalRows(findCountByCondition(conditions));
		responseInfo.setMatchingObjects(flightVOs);
		return responseInfo;
	}

	@Override
	public FlightVO toFlightVO(E flight) throws Exception {
		// TODO Auto-generated method stub
		return flightConverter.getVOject(flight);
	}

	
	@Override
	public boolean checkClearanceNormal(E flight) {
		
		/*
		 *  只有出港不正常的航班才进行放行正常的检查
		 */
		
		
		if (OutIn.Dep.equals(flight.getIsOutIn())&&!flight.isNormal()&&flight.getLinkFlight() != null){
			
			E previousFlight = getPreviousFlight(flight);
			/*
			 *  如果找到前一段的航班并且落地时间不为空，进行放行正常的检查
			 */
			if (previousFlight != null &&  flight.getAircraft() != null && flight.getAircraft().getCraftType() != null){
				
				Date startTime  = null;
				Date endTime    = null; 
				
				for(FlightDisPatch disPatch:previousFlight.getFlightDisPatchs()){
					if (parkBegin.equals(disPatch.getDisPatchItem().getName())){
						startTime = disPatch.getEndRealTime();
					}
				}
				
				for(FlightDisPatch disPatch:flight.getFlightDisPatchs()){
					if (parkEnd.equals(disPatch.getDisPatchItem().getName())){
						endTime = disPatch.getEndRealTime();
					}
				}
					
				if (startTime != null && endTime != null){
					Long mSecond = DateTimeUtil.MillisecondBetween(startTime, endTime)/1000;
					Long cSecond = flight.getAircraft().getCraftType().getClearanceSecond();
					if (mSecond > cSecond){
						/*
						 *  如果停场的时间大于该航班执行机型最大放行时间
						 */
						return false;
					}
				}
			}
		}
		
		return true;
	}


	



	

}
