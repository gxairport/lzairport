/**
 * 
 */
package com.lzairport.ais.dao.settlement.forecast.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.settlement.forecast.IForecastLinkDao;
import com.lzairport.ais.models.settlement.forecast.ForecastLink;

/**
 * 
 * FileName      ForecastLinkDao.java
 * @Description  TODO 各预测服务环节的Dao
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月24日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月24日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastLinkDao extends AodbDaoImpl<Integer, ForecastLink> implements IForecastLinkDao {


}
