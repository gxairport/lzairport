package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.ChartPanelParams;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      IChartPanelParamsService.java
 * @Description  TODO 图表定制的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年8月3日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年8月3日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IChartPanelParamsService extends IService<Integer, ChartPanelParams> {

}
