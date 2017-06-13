package com.lzairport.ais.service.statistics.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.statistics.IChartPanelParamsDao;
import com.lzairport.ais.models.statistics.ChartPanelParams;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IChartPanelParamsService;


/**
 * 
 * FileName      ChartPanelParamsService.java
 * @Description  TODO 图表定制的Service实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年8月3日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年8月3日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ChartPanelParamsService extends Service<Integer, ChartPanelParams> implements IChartPanelParamsService {

	@EJB
	public void setChartPanelParamsDao(IChartPanelParamsDao dao){
		setDao(dao);
	}
}
