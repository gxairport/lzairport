package com.lzairport.ais.service.statistics.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.beanutils.BeanUtils;
import com.lzairport.ais.dao.aodb.IScheduleFlightDao;
import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.dao.statistics.IBaseScheduleDao;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.models.statistics.BaseSchedule;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IBaseScheduleService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.utils.SYS_VARS.RouteType;
import com.lzairport.ais.utils.SYS_VARS.Week;

/**
 * 
 * FileName      BaseScheduleService.java
 * @Description  TODO 基准季度航线Service接口的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年9月10日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年9月10日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
 
@Stateless
public class BaseScheduleService extends Service<Integer, BaseSchedule> implements IBaseScheduleService {

	private static String[] compareFields ={BaseSchedule.CRAFTTYPE,BaseSchedule.ROUTEHX,BaseSchedule.BIGFLIGHTNO,BaseSchedule.ATTRIBUTE}; 
	
	@EJB
	private IScheduleFlightDao scheduleFlightDao;
	
	@EJB
	private IAreaAttributeService attributeService;
	
	private static	String strTransit = "-"+SYS_VARS.LocalAirportCn+"-";	
	
	private Date baseFirstDate;
	
	private Date baseLastDate;
	
   
	
	
	@EJB
	private void setBaseScheduleDao(IBaseScheduleDao dao){
		setDao(dao);
	}
	
	
	/**
	 * @Description: TODO 根据一个季度航班返回一个基准季度航班
	 * @param scheduleFlight  季度航班实体
	 * @throws Exception  
	 */
	private BaseSchedule createBaseSchedule(ScheduleFlight scheduleFlight,String fcstYear) throws Exception{
		BaseSchedule baseSchedule = new BaseSchedule();
		ObjectMethodUtil.copybean(baseSchedule, scheduleFlight);
		
		/*
		 *  用fcstYear 替换date中的Year
		 */
		String startMD = DateTimeUtil.dateToYYYYMMDD(scheduleFlight.getStartTime()).substring(4 );
		String endMD = DateTimeUtil.dateToYYYYMMDD(scheduleFlight.getEndTime()).substring(4);
		baseSchedule.setStartTime(DateTimeUtil.strToDate(fcstYear+startMD));
		baseSchedule.setEndTime(DateTimeUtil.strToDate(fcstYear+endMD));

		/*
		 *  startTime 必须是fcstYear-01-01以后
		 *  endTime   必须是fcstYear-12-31以前
		 */
		if (scheduleFlight.getStartTime().compareTo(baseFirstDate) < 0 ){
			baseSchedule.setStartTime(DateTimeUtil.strToDate(fcstYear+"-01-01"));
		}
		if(scheduleFlight.getEndTime().compareTo(baseLastDate) > 0){
			baseSchedule.setEndTime(DateTimeUtil.strToDate(fcstYear+"-12-31"));
		}
		
		return baseSchedule;
	}
	
	
	/**
	 * 
	 * @Description: TODO 判断航线是否可以进行合并
	 * @param merge    原航线    
	 * @param org  合并航线
	 * @return
	 * @throws Exception
	 *  合并规则:
	 *  LZH-PEK CA1859/1860 2016-01-01 2016-03-27  12346(基准)
	 *  LZH-PEK CA1859/1860 2016-01-01 2016-03-27  57(合并)    频率合并
	 *  LZH-PEK CA1859/1860 2016-03-28 2016-10-27  12346(合并) 时间合并 
	 */
	private boolean isMerge(BaseSchedule merge,BaseSchedule org) throws Exception{
		
		/*
		 * 如果基本字段属性不一致，返回false 
		 */
		for(String field:compareFields){
			Object orgObj = BeanUtils.getProperty(merge, field);
			Object compareObj = BeanUtils.getProperty(org, field);
			if (!orgObj.equals(compareObj)){
				return false;
			}
		}
		
		if (merge.getStartTime().equals(org.getStartTime())&&merge.getEndTime().equals(org.getEndTime())){
			/*
			 *  如果开始日期和结束日期都一致 说明可能是执行频率不一致
			 *  该基准航班符合频率合并条件
			 */
			return true;
		}else{
			/*
			 *  如果执行周期不一致，返回false;
			 */
			if (merge.getExecWeek().size() == org.getExecWeek().size()){
				for(Week week:org.getExecWeek()){
					if (!merge.getExecWeek().contains(week)){
						return false;
					}
				}
			}else{
				return false;
			}
			
			/*
			 * 如果合并的StartTime-1 = 被合并的EndTime 返回真，否则为假
			 * 如果合并的endTime+1 = 被合并的StartTime 返回真，否则为假
			 */
			Date orgStartDate = DateTimeUtil.addToDay(org.getStartTime(),-1);
			Date orgEndDate   = DateTimeUtil.addToDay(org.getEndTime(),+1);
			if (orgStartDate.equals(merge.getEndTime()) ||
					orgEndDate.equals(merge.getStartTime())){
				/* 
				 *  该航班符合时间合并条件
				 */
				return true;
			}else{
				return false;
			}
			
		}
		
		
		
		
		
	}
	
	
	/**
	 * @Description: TODO 找到能和基准季度航班合并的基准航班
	 * @param baseSchedule 基准季度航班
	 * @return  可以合并的基准季度航班
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private BaseSchedule findMergeBaseSchedule(BaseSchedule baseSchedule) throws Exception{
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{BaseSchedule.BIGFLIGHTNO,"=",baseSchedule.getBigFlightNO()});
		AisOrder order = new AisOrder();
		order.setName(BaseSchedule.STARTTIME);
		order.setSortMode(SYS_VARS.AscSORT);
		conditions.setOrders(new AisOrder[]{order});
		List<BaseSchedule> baseSchedules = dao.findByConditionAll(conditions);
		if (baseSchedules.size() > 0){
			BaseSchedule lastBaseSchedule = baseSchedules.get(baseSchedules.size()-1);
			
			if (isMerge(lastBaseSchedule,baseSchedule)){
				return lastBaseSchedule; 
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @Description: TODO 合并基准季度航班
	 * @param merge 被合并的基准季度航班
	 * @param org  合并的基准季度航班
	 * @return 合并后的基准季度航班
	 */
	private void mergeBaseSchedule(BaseSchedule merge,BaseSchedule org){
		/*
		 *  合并起始时间段
		 *  
		 */
		if (merge.getEndTime().compareTo(org.getEndTime()) > 0){
			merge.setStartTime(org.getStartTime());
		}else{
			merge.setEndTime(org.getEndTime());			
		}
		
		/*
		 *  合并执行频率
		 */
		for(Week week:org.getExecWeek()){
			if (!merge.getExecWeek().contains(week)){
				merge.getExecWeek().add(week);
			}
		}
		
		
	}

	@Override
	public void createByScheduleYear(String baseYear,String fcstYear) throws Exception {
		

		/*清除基准季度航班的所有记录*/
		for(BaseSchedule baseSchedule:dao.getAll()){
			dao.delete(baseSchedule);
		}
		
		/*
		 *  查找所有符合条件的季度航班的记录集合 (>=baseYear-01-01 and <=baseYear-12-31),按StartTime升序排列;
		 */
		baseFirstDate = DateTimeUtil.strToDate(baseYear+"-01-01");
		baseLastDate  = DateTimeUtil.strToDate(baseYear+"-12-31");
		
		
		
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{ScheduleFlight.STARTTIME,"<=",baseLastDate,
				"AND",ScheduleFlight.ENDTIME,">=",baseFirstDate,"AND",ScheduleFlight.ISOUTIN,"=",OutIn.Dep});
		AisOrder timeOrder = new AisOrder();
		timeOrder.setName(ScheduleFlight.STARTTIME);
		timeOrder.setSortMode(SYS_VARS.AscSORT);
		conditions.setOrders(new AisOrder[]{timeOrder});
		List<ScheduleFlight> scheduleFlights = scheduleFlightDao.findByConditionAll(conditions);
		
		/*
		 *  根据季度航班记录集合，合并或者新增基准季度航班
		 */
		for(ScheduleFlight scheduleFlight:scheduleFlights){
			BaseSchedule baseSchedule      = createBaseSchedule(scheduleFlight,fcstYear);
			BaseSchedule mergeBaseSchedule = findMergeBaseSchedule(baseSchedule);
			if (mergeBaseSchedule != null){
				mergeBaseSchedule(mergeBaseSchedule,baseSchedule);
				update(mergeBaseSchedule);
			}else{
				add(baseSchedule);
			}
		}
	}


	@Override
	public Integer CountFlightByCondition(QueryConditions conditions, Date startDate, Date endDate) {

		List<BaseSchedule> baseSchedules = findByConditionAll(conditions);
		Date date = startDate;
		int count = 0;
		while (date.compareTo(endDate)<=0){
			Week week = DateTimeUtil.getWeek(date);
			
			for (BaseSchedule BaseSchedule:baseSchedules){
				if (BaseSchedule.getExecWeek().contains(week)	&& BaseSchedule.getEndTime().compareTo(date)>=0 
						&& BaseSchedule.getStartTime().compareTo(date) <=0){
					count++;
				}
			}
			date = DateTimeUtil.addToDay(date, 1);
		}
		return count;
	}


	@Override
	public Integer getSeatByCondition(QueryConditions conditions, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<BaseSchedule> baseSchedules = findByConditionAll(conditions);
		Date date = startDate;
		int count = 0;
		int seatSum = 0;
		while (date.compareTo(endDate)<=0){
			Week week = DateTimeUtil.getWeek(date);
			
			for (BaseSchedule baseSchedule:baseSchedules){
				if (baseSchedule.getExecWeek().contains(week)&& baseSchedule.getEndTime().compareTo(date)>=0 
						&& baseSchedule.getStartTime().compareTo(date) <=0){
					seatSum += baseSchedule.getCraftType().getSeatNum();
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
	public RouteType getRouteType(BaseSchedule flight) {
		boolean bTransit = false;
		/* 
		 *   判断航班是否为中转航班 
		 *   判断依据"深圳-柳州-成都"字符串中的"-柳州-" 
		 */
		
		if (flight.getRouteHX() != null  && flight.getRouteHX().indexOf(strTransit) != -1){
			bTransit = true;
		}
		
		if(flight.getAttribute() != null){
			/*如果是类型是国内并且是中转航班返回国内中转*/
			if (attributeService.getDomAttribute().equals(flight.getAttribute())){
				if (bTransit){
					return RouteType.Dom_Tra;
				}else{
					return RouteType.Dom;
				}
			}if (attributeService.getIntAttribute().equals(flight.getAttribute())){
				if (bTransit){
					return RouteType.Int_Tra;
				}else{
					return RouteType.Int;
				}
			}
		}
		
		return null;
	}

	
}
