package com.lzairport.ais.service.aodb;

import java.util.Date;
import java.util.Set;

import com.lzairport.ais.exception.FlightServiceException;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.StopFlight;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.utils.SYS_VARS.RouteType;

/**
 * 基本航班Service接口
 * @author ZhangYu
 * @version 0.9a 09/05/15
 * @param <E>
 * @since JDK 1.6
 *
 */

public interface IBaseFlightService<K, E extends BaseFlight >  extends IService<K, E> {
	
	/**
	 * 创建航班的经停线路
	 * @param flight 航班
	 * @param stops 经停列表
	 */
	public Set<? extends StopFlight> createStopFlights(BaseFlight flight, Set<? extends StopFlight> stops);
	
	
	
	/**
	 * 删除航班信息
	 * @param flight 航班实体类
	 * @throws FlightServiceException
	 */
	public void flightDel(BaseFlight flight) throws FlightServiceException;
	
	/**
	 * 从开始日期转换航班  
	 * 长期计划---计划
	 * 计划----动态
	 * 动态----历史
	 * @param StartDate
	 * @throws Exception 
	 */
	public void convertFlights(Date startDate,Boolean cover,Boolean forcedImport) throws Exception;
	
	/**
	 * 
	 * @Description: TODO 获取航线的类型
	 */
	public RouteType getRouteType(BaseFlight flight);
	
	/**
	 * 
	 * @Description: TODO  更新航班的合成航班号
	 * @param flight 航班实体
	 * @return 合成的航班号 
	 * 例如 CA1859/CA1860
	 */
	public String getBigFlightNo(BaseFlight flight);

}
