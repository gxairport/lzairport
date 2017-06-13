package com.lzairport.ais.dao.statistics;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.statistics.ChartPanelParams;


/**
 * 
 * FileName      IChartPanelParamsDao.java
 * @Description  TODO 图表定制面板的Dao接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年8月3日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年8月3日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IChartPanelParamsDao extends IDao<Integer, ChartPanelParams> {

}
