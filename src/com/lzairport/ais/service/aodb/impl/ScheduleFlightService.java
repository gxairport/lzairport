package com.lzairport.ais.service.aodb.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IScheduleFlightDao;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.PlnFlight;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.service.aodb.IFlightStateService;
import com.lzairport.ais.service.aodb.IPlnFlightService;
import com.lzairport.ais.service.aodb.IScheduleFlightService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.GrpDate;
import com.lzairport.ais.utils.SYS_VARS.Quarter;
import com.lzairport.ais.utils.SYS_VARS.Week;


/**
 * 计划航班Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 09/05/15
 * @since JDK 1.6
 * @update 
 */

@Stateless
public class ScheduleFlightService extends BaseFlightService<Integer, ScheduleFlight> implements
		IScheduleFlightService {

	@EJB 
	private IPlnFlightService plnFlightService;
	
	@EJB
	private IFlightStateService flightStateService;
	
	/**
	 * @Fields 冬春航班的开始
	 */
	private static String stWiSpQuarter = "-10-30";
	
	
	/**
	 * @Fields 冬春航班的结束
	 */
	private static String edWiSpQuarter = "-03-26";
	
	/**
	 * @Fields 夏秋航班的开始
	 */
	private static String stSuAuQuarter = "-03-27";
	
	
	/**
	 * @Fields 夏秋航班的结束
	 */
	private static String edSuAuQuarter = "-10-29";
	
	
	
	 @EJB
	 public void setScheduleFlightDao(IScheduleFlightDao scheduleFlightDao){
		 setDao(scheduleFlightDao);
	 }

	 
	 /**
	  * 根据长期航班计划创建一个航班计划
	  * @param scheduleFlight 长期航班实体
	  * @param execDate 执行日期
	 * @throws Exception 
	  */
	 private void SHtoPLN(ScheduleFlight scheduleFlight,Date execDate) throws Exception{
			/**
			 * 查找动态中是否有相同的航班实体
			 */
			QueryConditions conditions = new QueryConditions();
			conditions.setExpresstion(new Object[]{PlnFlight.EXECDATE,"=",execDate,SYS_VARS.LinkSqlAnd,
					PlnFlight.DEPAIRPORT,"=",scheduleFlight.getDepAirport(),SYS_VARS.LinkSqlAnd,
					PlnFlight.ARRAIRPORT,"=",scheduleFlight.getArrAirport(),SYS_VARS.LinkSqlAnd,
					PlnFlight.FLIGHTNO,"=",scheduleFlight.getFlightNO()
					});

			PlnFlight plnFlight = plnFlightService.findByConditionSingle(conditions);
			
			if (plnFlight == null){
				/**
				 * 设置基本的信息
				 */
				plnFlight = new PlnFlight();
				plnFlight.setFlightNO(scheduleFlight.getFlightNO());
				plnFlight.setDepAirport(scheduleFlight.getDepAirport());
				plnFlight.setArrAirport(scheduleFlight.getArrAirport());
				plnFlight.setExecDate(execDate);
				plnFlight.setState(flightStateService.getPlnState());
				plnFlight.setAirlines(scheduleFlight.getAirlines());
			}
			
			
			/**
			 * 创建计划航班
			 */
			plnFlightService.createFlight(plnFlight);
			

	 }
	 
	@Override
	public void convertFlights(Date execDate, Boolean cover,Boolean forcedImport) throws Exception {
		
		/**
		 * 取得当前的礼拜，并转换为枚举型
		 */
		Calendar cal = Calendar.getInstance();
		cal.setTime(execDate);
		Week day = Week.values()[cal.get(Calendar.DAY_OF_WEEK)-2];
		/**
		 * 查找创建航班日期在 长期计划结束日期>=执行日期>=长期航班开始日期
		 */
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{ScheduleFlight.STARTTIME,"<=",execDate,SYS_VARS.LinkSqlAnd,
				ScheduleFlight.ENDTIME,">=",execDate});
		conditions.setFetchOneToMany("ALL");
		List<ScheduleFlight> flights = findByConditionAll(conditions);
		
		for(ScheduleFlight flight:flights){

			/**
			 * 如果在执行礼拜的周期内
			 */
			if (flight.getExecWeek().contains(day)){
				SHtoPLN(flight, execDate);
			}
		}
		
	}


	@Override
	public void flightDel(BaseFlight flight) throws FlightServiceException {
		super.remove(flight);	
	}


	@Override
	public ScheduleFlight findScheduleFlight(Flight flight) {
		// TODO Auto-generated method stub
		
		QueryConditions conditions = new QueryConditions();
		Object[] expresstion = new Object[]{Flight.FLIGHTNO,"=",flight.getFlightNO(),
				SYS_VARS.LinkSqlAnd,ScheduleFlight.STARTTIME,"<=",flight.getExecDate()};
		
		if (flight.getDepAirport() != null){
			expresstion = new Object[]{Flight.FLIGHTNO,"=",flight.getFlightNO(),"AND",ScheduleFlight.STARTTIME,"<=",flight.getExecDate(),
					"AND",ScheduleFlight.ENDTIME,">=",flight.getExecDate(),"AND",Flight.DEPAIRPORT,"=",flight.getDepAirport()};
		}
		
		conditions.setExpresstion(expresstion);
		
		Week week = DateTimeUtil.getWeek(flight.getExecDate());
		
		List<ScheduleFlight> scheduleFlights = findByConditionAll(conditions);
		
		for (ScheduleFlight scheduleFlight:scheduleFlights){
			if (scheduleFlight.getExecWeek().contains(week)){
				return scheduleFlight;
			}
		}
		
		return null;
	}


	@Override
	public ScheduleFlight findScheduleFlightByZP(Flight flight) {
		QueryConditions conditions = new QueryConditions();
		
		String flightNO = flight.getFlightNO().substring(0, flight.getFlightNO().length()-1)+"%";
		
		Object[] expresstion = new Object[]{Flight.FLIGHTNO,SYS_VARS.Oper_Like,flightNO,
				SYS_VARS.LinkSqlAnd,ScheduleFlight.STARTTIME,"<=",flight.getExecDate()};
		
		if ((flight.getDepAirport() != null)&&(flight.getArrAirport() != null)){
			expresstion = new Object[]{Flight.FLIGHTNO,SYS_VARS.Oper_Like,flightNO,SYS_VARS.LinkSqlAnd,ScheduleFlight.STARTTIME,"<=",flight.getExecDate(),
					SYS_VARS.LinkSqlAnd,Flight.DEPAIRPORT,"=",flight.getDepAirport(),
					SYS_VARS.LinkSqlAnd,Flight.ARRAIRPORT,"=",flight.getArrAirport()};
		}
		
		conditions.setExpresstion(expresstion);
		
		ScheduleFlight scheduleFlight = findByConditionSingle(conditions);
		
		return scheduleFlight;
	}


	@Override
	public Integer CountFlightByCondition(QueryConditions conditions,Date startDate,Date endDate) {

		List<ScheduleFlight> scheduleFlights = findByConditionAll(conditions);
		Date date = startDate;
		int count = 0;
		while (date.compareTo(endDate)<=0){
			Week week = DateTimeUtil.getWeek(date);
			
			for (ScheduleFlight scheduleFlight:scheduleFlights){
				if (scheduleFlight.getExecWeek().contains(week)	&& scheduleFlight.getEndTime().compareTo(date)>=0 
						&& scheduleFlight.getStartTime().compareTo(date) <=0){
					count++;
				}
			}
			date = DateTimeUtil.addToDay(date, 1);
		}
		return count;
	}
	
	@Override
	public Integer getSeatByCondition(QueryConditions conditions,Date startDate,Date endDate) {

		List<ScheduleFlight> scheduleFlights = findByConditionAll(conditions);
		Date date = startDate;
		int count = 0;
		int seatSum = 0;
		while (date.compareTo(endDate)<=0){
			Week week = DateTimeUtil.getWeek(date);
			
			for (ScheduleFlight scheduleFlight:scheduleFlights){
				if (scheduleFlight.getExecWeek().contains(week)&& scheduleFlight.getEndTime().compareTo(date)>=0 
						&& scheduleFlight.getStartTime().compareTo(date) <=0){
					seatSum += scheduleFlight.getSeatNum();
					count++;
				}
			}
			date = DateTimeUtil.addToDay(date, 1);
		}
		if (count != 0){
			return seatSum/count;
		}else{
			return 0;
		}
	}


	@Override
	public Integer CountFlightByGrpDate(GrpDate date, String dateExpression) {
		/**
		 * 暂时不处理
		 */
		return null;
	}


	
	
	
	@Override
	public ScheduleFlight update(Object obj) {
		ScheduleFlight flight = (ScheduleFlight) obj;
		flight.setRouteHX(getRouteCn(flight));
		flight.setBigFlightNO(getBigFlightNo(flight));
		return super.update(flight);
	}


	@Override
	public String getRouteCn(ScheduleFlight flight) {
		
		if (flight.getTerminalAirport() != null&&flight.getStartAirport()  != null){
			String startAp = flight.getStartAirport().getThreeCharCode();
			String endAp = flight.getTerminalAirport().getThreeCharCode();
			/**
			 * 如果航线完整
			 */
			if (startAp.equals(SYS_VARS.LocalAirportThreeCode) ||endAp.equals(SYS_VARS.LocalAirportThreeCode)){
				if (endAp.equals(SYS_VARS.LocalAirportThreeCode)){
					/**
					 * 如果终端机场是本场，返回反向航线，比如:
					 * MU5379 TAO-CGO-LZH
					 * 
					 */
					return flight.getBackRouteCn();
				}else{
					/**
					 * 否则说明返回正向航线,比如：
					 * MU5204 LZH-SHA
					 */
					return flight.getForwardRouteCn();
				}
				
				
			}else{
				/**
				 * 代表航线是 中转航线是本场 ，EU2201 CTU-LZH-SZX 航线为 CTU-LZH-SZX,返回正向航线
				 */
				return flight.getForwardRouteCn();
			}
			
		}else{
			return "航线不完整";
		}
	}


	@Override
	public void replicateQuarter(String baseYear, String copyYear, Quarter baseQuarter, Quarter copyQuarter) throws Exception {
		// TODO Auto-generated method stub
		/*
		 *   定义航季的边界日期 
		 */
		
		Date baseQuarterEndTime   = null;
		Date copyQuarterStartTime = null;
		Date copyQuarterEndTime   = null;
		
		if (baseQuarter.equals(Quarter.WinterSpring)){
			baseQuarterEndTime = DateTimeUtil.strToDate(baseYear+edWiSpQuarter);
		}else if (baseQuarter.equals(Quarter.SummerAutumn)){
			baseQuarterEndTime = DateTimeUtil.strToDate(baseYear+edSuAuQuarter);
		}
		
		if (copyQuarter.equals(Quarter.WinterSpring)){
			 copyQuarterEndTime = DateTimeUtil.strToDate(copyYear+edWiSpQuarter);
			 copyQuarterStartTime = DateTimeUtil.strToDate(String.valueOf(Integer.parseInt(copyYear)-1)+stWiSpQuarter);
		}else if (copyQuarter.equals(Quarter.SummerAutumn)){
			copyQuarterEndTime = DateTimeUtil.strToDate(copyYear+edSuAuQuarter);
			copyQuarterStartTime = DateTimeUtil.strToDate(copyYear+stSuAuQuarter);
		}		
		
		/*
		 *  设定生成条件，查询符合记录的季度航班数据 
		 */
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{ScheduleFlight.STARTTIME,"<=",baseQuarterEndTime,
				"AND",ScheduleFlight.ENDTIME,"=",baseQuarterEndTime});
		conditions.setFetchOneToMany("ALL");
		List<ScheduleFlight> baseFlights = findByConditionAll(conditions);
		
		for(ScheduleFlight baseFlight:baseFlights){
			ScheduleFlight flight = new ScheduleFlight();
			ObjectMethodUtil.copybean(flight, baseFlight);
			flight.setStartTime(copyQuarterStartTime);
			flight.setEndTime(copyQuarterEndTime);
			flight.setQuarter(copyQuarter);
			flight.setId(0);
			update(flight);
		}
	}
}
