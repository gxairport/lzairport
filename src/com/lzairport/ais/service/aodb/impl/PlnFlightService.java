package com.lzairport.ais.service.aodb.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IPlnFlightDao;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.DynFlight;
import com.lzairport.ais.models.aodb.PlnFlight;
import com.lzairport.ais.models.aodb.PlnFlightDisPatch;
import com.lzairport.ais.models.aodb.PlnFlightLoad;
import com.lzairport.ais.models.aodb.PlnStopFlight;
import com.lzairport.ais.service.aodb.IDynFlightService;
import com.lzairport.ais.service.aodb.IPlnFlightService;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 
 * FileName      PlnFlightService.java
 * @Description  航班计划的Service接口的实现类，完成航班计划的操作
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015-9-22 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-22      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
 
public class PlnFlightService extends FlightService<Integer,PlnFlight> implements IPlnFlightService {

	
	@EJB
	private  IDynFlightService  dynFlightService ;
	
	
	@EJB
	public void setPlnFlightDao(IPlnFlightDao plnFlightDao){
		setDao(plnFlightDao);
	}
	
	/**
	 * 将一个航班计划转为动态
	 * @param plnFlight 计划航班实体
	 * @param Cover  是否覆盖
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private void PlnToDyn(PlnFlight plnFlight,Boolean Cover) throws Exception {
		
		//查找动态中是否有相同的航班实体
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{DynFlight.EXECDATE,"=",plnFlight.getExecDate(),SYS_VARS.LinkSqlAnd,
				DynFlight.DEPAIRPORT,"=",plnFlight.getDepAirport(),SYS_VARS.LinkSqlAnd,
				DynFlight.FLIGHTNO,"=",plnFlight.getFlightNO()
				});

		DynFlight dynFlight = dynFlightService.findByConditionSingle(conditions);
		
		//添加新航班标志
		Boolean addNewFlight = false;
		
		if (dynFlight == null){
			dynFlight = new DynFlight();
			addNewFlight = true;
		}
		
		//如果是添加的新航班或者覆盖标志为True
		if ((addNewFlight)||(Cover)){
			
			//航班基本信息复制
			ObjectMethodUtil.copybean(dynFlight, plnFlight);
			dynFlight.setId(0);
			
			//载量信息复制
			Set<PlnFlightLoad> plnFlightLoads = plnFlight.getLoads();
			copyFlightLoads(dynFlight, plnFlightLoads);
			
			//服务环节复制
			Set<PlnFlightDisPatch> plnFlightDisPatchs = plnFlight.getFlightDisPatchs();
			copyFlightDisPatchs(dynFlight, plnFlightDisPatchs);
			
			//经停航班创建
			Set<PlnStopFlight> plnStopFlights = plnFlight.getStopFlights();
			createStopFlights(dynFlight, plnStopFlights);
			
			
			//设置延误和发送FPL的次数提醒
			dynFlight.setDelayTimes(SYS_VARS.FlightDelayTimes);
			dynFlight.setClearanceTimes(SYS_VARS.FlightDelayTimes);
			dynFlight.setFPLTimes(SYS_VARS.FlightFPLTimes);
			
			dynFlight =  dynFlightService.add(dynFlight);
		}
		remove(plnFlight);
		
	}



	/**
	 * 将航班计划从开始日期转换为动态
	 * @throws Exception 
	 */
	@Override
	public void convertFlights(Date startDate,Boolean cover,Boolean forcedImport ) throws Exception {
		
		
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{PlnFlight.EXECDATE,"<=",startDate});
		List<PlnFlight> plnFlights = findByConditionAll(conditions);
		
		for (PlnFlight plnFlight:plnFlights){
			PlnToDyn(plnFlight,cover);
		}
	}


	

}
