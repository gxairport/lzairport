package com.lzairport.ais.service.aodb;

import java.util.Date;
import java.util.Set;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.models.aodb.FlightLoad;
import com.lzairport.ais.models.aodb.PayingPassenger;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.vo.FlightVO;

/**
 * 
 * FileName      航班Service接口，定义基本航班的各种基本操作
 * @Description  TODO(用一句话描述该文件做什么)
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 12/11/14
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015-9-21      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
public interface IFlightService<K,E extends Flight> extends IBaseFlightService<K, E>{

	
	/**
	 * 
	 * @Description: 返回EXTJS页面所需要的Response对象
	 * @param conditions 条件
	 * @return DataFetchResponseInfo对象 
	 * @throws Exception
	 */
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception;
	
	/**
	 * 增加一个航班
	 * @param flight 航班实体类
	 * @throws Exception 
	 * @throws FlightServiceException
	 */
	public E createFlight(Flight flight) throws Exception ;
	
	
	
	/**
	 * 根据航班创建该航班的所有调度环节
	 * @param flight 航班实体类
	 * @throws FlightServiceException 
	 */
	public void createFlightDisPatchs(Flight flight) throws FlightServiceException;
	
	/**
	 * 
	 * @Description: 根据disPatchs创建航班的服务环节
	 * @param flight 航班实体
	 * @param disPatchs 服务环节集合
	 * @throws FlightServiceException 
	 */
	public void copyFlightDisPatchs(Flight flight, Set<? extends FlightDisPatch> scrDispatchs ) throws FlightServiceException;
	
	
	/**
	 * 
	 * @Description: 根据passengers创建航班的付费旅客信息
	 * @param flight 航班实体
	 * @param passengers 付费旅客集合
	 * @throws FlightServiceException 
	 */
	public void copyFlightPayingPassengers(Flight flight, Set<? extends PayingPassenger> scrPassengers) throws FlightServiceException;
	
	
	/**
	 * 
	 * @Description: 根据scrLoads创建航班载量数据
	 * @param flight 航班实体
	 * @param scrLoads 载量数据集合
	 * @throws FlightServiceException 
	 */
	public void copyFlightLoads(Flight flight,Set<? extends FlightLoad> scrLoads) throws FlightServiceException;

	
	/**
	 * 从航班开始日期将航班进行相对应的关联
	 * @param startDate  关联航班开始日期
	 */
	public void linkFlights(Date startDate) throws  FlightServiceException;
	
	
	/**
	 * 找到航班的关联航班
	 * @param linkFlight 关联航班字符串
	 * @return 返回航班的关联航班
	 */
	public E getLinkFlight(Flight flight);
	
	
	/**
	 * 
	 * @Description: TODO 找到航班的前段航班
	 * @return 返回航班的前段航班
	 */
	public E getPreviousFlight(E flight);
	
	
	/**
	 * 
	 * @Description: TODO 将航班实体类转换为FlightVO
	 * @param flight 航班实体类
	 * @return
	 * @throws Exception 
	 */
	public FlightVO toFlightVO(E flight) throws Exception;
	
	
	/**
	 * 
	 * @Description: TODO 判断一个航班是否放行正常
	 * @param flight 航班实体类
	 * @return 是否
	 */
	public boolean checkClearanceNormal(E flight);
	
	

	
}
