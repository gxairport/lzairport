package com.lzairport.ais.service.statistics;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.BaseRoute;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IBaseRouteService.java
 * @Description  TODO 预测航线的基准数据的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月7日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IBaseRouteService extends IService<Integer, BaseRoute> {
	
	/**
	 * 
	 * @Description: TODO 根据统计报表更新全部的航线基础数据
	 * @param rList 统计报表
	 */
	public void updateForReportList(List<Map<String, Object>> rList);
}
