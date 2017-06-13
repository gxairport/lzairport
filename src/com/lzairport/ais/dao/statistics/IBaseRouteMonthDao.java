package com.lzairport.ais.dao.statistics;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.statistics.BaseRouteMonth;


/**
 * 
 * FileName      IBaseRouteMonthDao.java
 * @Description  TODO 预测航线每个月的基准数据的Dao接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月7日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IBaseRouteMonthDao extends IDao<Integer, BaseRouteMonth> {

}
