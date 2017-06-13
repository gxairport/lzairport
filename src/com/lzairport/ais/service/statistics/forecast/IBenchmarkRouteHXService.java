package com.lzairport.ais.service.statistics.forecast;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.forecast.BenchmarkRouteHX;
import com.lzairport.ais.service.IService;


/**
 * FileName      IBenchmarkRouteHX.java
 * @Description  TODO 预测航班的基准数据Service接口 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IBenchmarkRouteHXService extends IService<Integer, BenchmarkRouteHX> {
	
	
	/**
	 * @Description: TODO 根据统计报表更新全部的航线基础数据
	 * @param rList 统计报表
	 * @param year  指定的年份
	 */
	public void updateForReportList(List<Map<String, Object>> rList,String year);
	
}
