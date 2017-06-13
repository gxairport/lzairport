package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IChartPanelParamsDao;
import com.lzairport.ais.models.statistics.ChartPanelParams;

/**
 * 
 * FileName      ChartPanelParamsDao.java
 * @Description  TODO 图表定制面板的Dao实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年8月3日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年8月3日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ChartPanelParamsDao extends AodbDaoImpl<Integer, ChartPanelParams> implements IChartPanelParamsDao {

	
}
