package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IBaseScheduleDao;
import com.lzairport.ais.models.statistics.BaseSchedule;

/**
 * 
 * FileName      BaseScheduleDao.java
 * @Description  TODO 基准季度航线的Dao接口的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年9月10日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年9月10日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Stateless
public class BaseScheduleDao extends AodbDaoImpl<Integer, BaseSchedule> implements IBaseScheduleDao {

}
