package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.FlightState;
import com.lzairport.ais.service.IService;


/**
 * 航班状态Service接口，用以返回定义好的航班各状态
 * @author ZhangYu
 * @version 0.9a 12/11/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IFlightStateService extends IService<Integer,FlightState> {
	
    /**
     * 
     * @return 航班计划状态
     */
	public FlightState getPlnState(); 

	/**
	 * 
	 * @return 航班前场起飞
	 */
	public FlightState getPreviousTakeOffState();
	
	/**
	 * 
	 * @return 航班本场起飞
	 */
	public FlightState getLocalTakeOffState();

	/**
	 * 
	 * @return 航班备降起飞
	 */
	public FlightState getAlternateTakeOffState();
	
	
	/**
	 * 
	 * @return 返航后起飞
	 */
	public FlightState getReturnTakeoffState();
	
	/**
	 * 
	 * @return 航班正常落地
	 */
	public FlightState getLandInState();
	
	/**
	 * 
	 * @return 航班备降落地
	 */
	public FlightState getAlternateLandInState();
	
	/**
	 * 
	 * @return 航班返航落地
	 */
	public FlightState getReturnLandInState();
	
	/**
	 * 
	 * @return 航班备降中
	 */
	public FlightState getAlternateState();
	
	/**
	 * 
	 * @return 航班返航中
	 */
	public FlightState getReturnState();
	
	/**
	 * 
	 * @return 航班延误
	 */
	public FlightState getDlyState();
	
	/**
	 * 
	 * @return 航班取消
	 */
	public FlightState getCnlState();
	
	
	/**
	 * 
	 * @return  航班FPL
	 */
	public FlightState getFPLState();
	
	
}
