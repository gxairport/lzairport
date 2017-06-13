package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.PlnFlightLoad;

/**
 *航班计划的配载实体类的Dao接口
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Local
public interface IPlnFlightLoadDao extends IDao<Integer, PlnFlightLoad> {

}
