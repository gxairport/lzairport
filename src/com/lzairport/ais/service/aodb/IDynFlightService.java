package com.lzairport.ais.service.aodb;

import java.util.Date;

import javax.ejb.Remote;

import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.models.aodb.DynFlight;

/**
 * 航班动态Service接口，定义航班动态的各种状态更新及航班的行为
 * @author ZhangYu
 * @version 0.9a 04/16/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IDynFlightService extends IFlightService<Integer, DynFlight>{
	
	/**
	 * 更新航班的起飞状态
	 * @param flight 航班实体类
	 * @param takeOffTime 起飞时间
	 * @throws FlightServiceException
	 */
	
	public DynFlight setFlightTakeOff(DynFlight flight,Date takeOffTime)  throws FlightServiceException;
	
	
	/**
	 * 更改航班预计起飞时间
	 * @param flight 航班实体类
	 * @param takeOffTime 起飞时间
	 * @throws FlightServiceException
	 */
	
	public DynFlight setFlighttAlterateTakeOffTime(DynFlight flight,Date takeOffTime)  throws FlightServiceException;
	
	/**
	 * 更新航班的落地状态
	 * @param flight 航班实体类
	 * @param landInTime 落地时间
	 * @throws FlightServiceException
	 */
	
	public DynFlight setFlightLandIn(DynFlight flight,Date landInTime)  throws FlightServiceException;
	
	/**
	 * 更改航班预计落地时间
	 * @param flight
	 * @param takeOffTime
	 * @throws FlightServiceException
	 */
	
	public DynFlight setFlightAlterateLandInTime(DynFlight flight,Date landInTime)  throws FlightServiceException;
	

	/**
	 * 更新航班的延误状态
	 * @param flight 航班实体类
	 * @param reason 延误原因
	 * @throws FlightServiceException
	 */
	public DynFlight setFlightDly(DynFlight flight,DelayReason reason,Date alteraTeakeOffTime) throws FlightServiceException;
	
	/**
	 * 更新航班为取消状态
	 * @param flight 航班实体类
	 * @param reason 取消原因
	 * @throws FlightServiceException
	 */
	public DynFlight setFlightCnl(DynFlight flight,DelayReason reason) throws FlightServiceException;
	
	
	/**
	 * 更新航班为备降状态
	 * @param flight 航班实体类
	 * @param reason 备降原因
	 * @throws FlightServiceException
	 */
	public DynFlight setFlightAlternate(DynFlight flight,DelayReason reason,Airport alternateAirport) throws FlightServiceException;
	
	

	/**
	 * 更新航班为返航状态
	 * @param flight 航班实体类
	 * @param reason 返航原因
	 * @throws FlightServiceException
	 */
	public DynFlight setFlightReturn(DynFlight flight,DelayReason reason) throws FlightServiceException;
	
	
	/**
	 * 更新航班为FPL状态
	 * @throws FlightServiceException 
	 */
	public DynFlight setFlightFPL(DynFlight flight) throws FlightServiceException;
	
	

	
	/**
	 * 航班更换飞机
	 * @param flight 航班实体类
	 * @param aircraft 更换飞机
	 * @throws FlightServiceException
	 */
	
	public void flightChangeAircraft(DynFlight flight,Aircraft aircraft) ;
	
	
	/**
	 * 检查航班是否延误
	 * @param flight 航班实体类
	 * @return 是否
	 */
	public boolean CheckFlightDelay(DynFlight flight);
	
	/**
	 * 检查本场航班是否发送FPL报文
	 * @param flight 航班实体类
	 * @return 是否
	 */
	public boolean CheckLocalFPLDelay(DynFlight flight);

	/**
	 * 检查一个航班数据的完整性
	 * 具体检查是否落地、起飞、落地时间，关联航班、载量数据等 
	 * @param flight 航班实体类
	 * @return 如果完整返回 字符串"",如果不完整，返回具体不完整的详细信息
	 */
	public String checkFlightCompelete(DynFlight flight);


	
	
}
