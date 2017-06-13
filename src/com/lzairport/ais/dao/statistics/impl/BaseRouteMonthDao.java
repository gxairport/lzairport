package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IBaseRouteMonthDao;
import com.lzairport.ais.models.statistics.BaseRouteMonth;

/**
 * 
 * FileName      BaseRouteMonthDao.java
 * @Description  TODO 预测航线每个月的基准数据的Dao实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月7日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class BaseRouteMonthDao extends AodbDaoImpl<Integer, BaseRouteMonth> implements IBaseRouteMonthDao {

	
}
