package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.ScheduleShareFlight;

/**
 * 航班共享班期计划的Dao接口
 * @author ZhangYu
 * @version 0.9a 23/08/14
 * @since JDK 1.6
 *
 */
@Local
public interface IScheduleShareFlightDao extends
		IDao<Integer, ScheduleShareFlight> {

}
