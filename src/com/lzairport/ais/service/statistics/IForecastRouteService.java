package com.lzairport.ais.service.statistics;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.ForecastRoute;
import com.lzairport.ais.service.IService;



/**
 * 
 * FileName      IForecastRouteService.java
 * @Description  TODO 预测航线的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月1日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月1日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Remote
public interface IForecastRouteService extends IService<Integer, ForecastRoute> {

	/**
	 * 
	 * @Description: TODO 获取所有的运输航线
	 * @return 返回航线不为空的运输航线
	 */
	public List<ForecastRoute> getAllCarriages();
	
	
	/**
	 * 
	 * @Description: TODO 根据指定的航线创建预测航线数据
	 * @param routeHX 指定的航线
	 * @return 创建的预测航线
	 */
	public ForecastRoute createRoute(String year,String routeHX);
	
	/**
	 * 
	 * @Description: TODO 根据map的值更新实际的数据
	 * @param map 包含实际数据的map
	 */
	
	/**
	 * 
	 * @Description: TODO 根据map的值更新month之前的实际数据
	 * @param route   预测航线
	 * @param map     包含实际数据的map
	 * @param month   月份界限
	 */
	public void actualToForecast(ForecastRoute route,Map<String, Object> map,int eMonth);

	/**
	 * 
	 * @Description: TODO 将月份在eMonth前的实际数据代替预测数据
	 * @param route
	 * @param eMonth
	 */
	public void actualReplaceForecast(ForecastRoute route,int eMonth);
	
	
	/**
	 * 
	 * @Description: TODO 获取指定季度的数据之和
	 * @param route  预测航线
	 * @param quarter 指定季度
	 * @param field   指定字段 
	 * @return 数据之和
	 */
	public int  getQuarterSumValue(ForecastRoute route,int quarter,String field);
	
	/**
	 * 
	 * @Description: TODO 获取指定季度的数据平均值
	 * @param route       预测航线
	 * @param quarter     指定季度
	 * @param field       指定字段
	 * @return            数据平均值
	 */
	public double  getQuarterAvgValue(ForecastRoute route,int quarter,String field);
}
