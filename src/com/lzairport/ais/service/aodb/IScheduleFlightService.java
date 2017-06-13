package com.lzairport.ais.service.aodb;

import java.util.Date;

import javax.ejb.Remote;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.utils.SYS_VARS.GrpDate;
import com.lzairport.ais.utils.SYS_VARS.Quarter;


/**
 * 航班班期计划的Service接口
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IScheduleFlightService extends
		IBaseFlightService<Integer, ScheduleFlight> {
	
	
	/**
	 * 
	 * @Description: 根据给定的航班实体返回对应的长期计划航班计划
	 * @param flight 给定的航班实体
	 * @return
	 */
	public ScheduleFlight findScheduleFlight(Flight flight);
	
	
	/**
	 * 
	 * @Description: 根据给定的航班实体返回对应的长期计划航班计划(用于补班)
	 * @param flight
	 * @return
	 */
	public ScheduleFlight findScheduleFlightByZP(Flight flight);
	
	/**
	 * 
	 * @Description: 根据给定的条件，计算计划执行的航班数量
	 * @param conditions 给定的条件
	 * @return 计划执行的航班数量
	 */
	public Integer  CountFlightByCondition(QueryConditions conditions,Date startDate,Date endDate);
	
	/**
	 * 
	 * @Description: TODO 根据给定的条件，计算计划一个航班的座位数
	 * @param conditions 给定的条件
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return 一个航班的平均座位数
	 */
	public Integer getSeatByCondition(QueryConditions conditions,Date startDate,Date endDate);
	

	/**
	 * 
	 * @Description: 根据给定的GrpDate类型，计算计划执行的航班数量
	 * @param date 
	 * @param dateExpression
	 * @return 计划执行的航班数量
	 */
	public Integer CountFlightByGrpDate(GrpDate date,String dateExpression);
	
	
	

	/**
	 * 
	 * @Description: 返回航班的航线，规则如下：
	 * 1、MU5203 SHA-LZH 航线为 SHA-LZH-SHA
	 * 2、MU5204 LZH-SHA 航线为 SHA-LZH-SHA
	 * 3、Mu5379 TAO-CGO-LZH 航线为 LZH-CGO-TAO
	 * 4、MU5380 LZH-CGO-TAO 航线为 LZH-CGO-TAO
	 * 5、EU2201 CTU-LZH-SZX 航线为 CTU-LZH-SZX
	 * 6、EU2202 SZX-LZH-CTU 航线为 SZX-LZH-CTU
	 * @return
	 */
	public String getRouteCn(ScheduleFlight flight);
	
	
	/**
	 * 
	 * @Description: TODO 根据给定的条件复制季度航班
	 * @param baseYear  被复制的季度航班的年份
	 * @param copyYear  复制的季度航班的年份
	 * @param baseQuarter 被复制的季度航班的航季
	 * @param copyQuarter 复制的季度航班的航季
	 * @throws Exception 
	 */
	public void replicateQuarter(String baseYear,String copyYear,Quarter baseQuarter,Quarter copyQuarter) throws Exception;
	
	
}
