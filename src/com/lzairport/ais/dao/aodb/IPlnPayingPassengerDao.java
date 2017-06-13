package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.PlnPayingPassenger;

/**
 * 计划航班付费旅客实体类的Dao接口
 * @author ZhangYu
 * @version 0.9a 18/05/15
 * @since JDK 1.6
 *
 */

@Local
public interface IPlnPayingPassengerDao extends IDao<Integer, PlnPayingPassenger> {

}
