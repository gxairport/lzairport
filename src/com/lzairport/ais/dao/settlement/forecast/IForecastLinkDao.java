package com.lzairport.ais.dao.settlement.forecast;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.settlement.forecast.ForecastLink;

/**
 * FileName      IForecastLinkDao.java
 * @Description  TODO 各预测服务环节的Dao接口 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月24日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月24日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IForecastLinkDao extends IDao<Integer, ForecastLink> {

}
