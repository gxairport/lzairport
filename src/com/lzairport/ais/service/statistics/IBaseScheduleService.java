package com.lzairport.ais.service.statistics;

import java.util.Date;
import javax.ejb.Remote;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.statistics.BaseSchedule;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.utils.SYS_VARS.RouteType;


/**
 * 
 * FileName      IBaseScheduleService.java
 * @Description  TODO 基准季度航班的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年9月10日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年9月10日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IBaseScheduleService extends IService<Integer, BaseSchedule> {
	
	
	/**
	 * 
	 * @Description: TODO 根据year指定年份的季度航班计划创建基准的季度航班计划
	 * @param year 指定的年份
	 * @throws Exception 
	 */
	public void createByScheduleYear(String baseYear,String fcstYear) throws Exception;
	
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
	 * @Description: 根据给定的条件，计算计划执行的航班数量
	 * @param conditions 给定的条件
	 * @return 计划执行的航班数量
	 */
	public Integer  CountFlightByCondition(QueryConditions conditions,Date startDate,Date endDate);
	
	/**
	 * 
	 * @Description: TODO 获取航线的类型
	 */
	public RouteType getRouteType(BaseSchedule flight);

}
