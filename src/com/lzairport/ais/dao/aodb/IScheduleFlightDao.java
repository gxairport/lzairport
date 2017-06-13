package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.ScheduleFlight;

/**
 * 航班班期计划的Dao接口
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */
@Local
public interface IScheduleFlightDao extends IDao<Integer, ScheduleFlight> {

}
