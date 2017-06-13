package com.lzairport.ais.service.statistics.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.dao.statistics.IBaseRouteDao;
import com.lzairport.ais.dao.statistics.IForecastRouteDao;
import com.lzairport.ais.models.statistics.BaseRoute;
import com.lzairport.ais.models.statistics.BaseRouteMonth;
import com.lzairport.ais.models.statistics.BaseSchedule;
import com.lzairport.ais.models.statistics.ForecastRoute;
import com.lzairport.ais.models.statistics.ForecastRouteMonth;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IBaseScheduleService;
import com.lzairport.ais.service.statistics.IForecastRouteMonthService;
import com.lzairport.ais.service.statistics.IForecastRouteService;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.RouteType;

/**
 * 
 * FileName      ForecastRouteService.java
 * @Description  TODO 预测航线的Service接口的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月1日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月1日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class ForecastRouteService extends Service<Integer, ForecastRoute> implements IForecastRouteService {
	
	/**
	 *  更新实际数据map中key的集合
	 */
	private static String[] ActualKeys = {"COUNTFLT","PAX","PAX.Arr","PAX.Dep","RATE","RATE.Dep","RATE.Arr"};
	
	/**
	 *  更新实际对应数据字段集合
	 */
	private static String[] ActualFields ={"actualCountFLT","actualPax","actualiPax","actualoPax","actualRate","actualoRate","actualiRate"};
	
	/**
	 *  实数类型转换格式
	 */
	private static DecimalFormat df = new DecimalFormat("#.0");
	
	
	
	/**
	 *  预测航线月的Service
	 */
	@EJB
	private IForecastRouteMonthService fmRouteService;
	
	/**
	 *  基准季度航班计划的Service
	 */
	@EJB
	private IBaseScheduleService baseScheduleService;
	
	/**
	 *  基准航线的Service
	 */
	@EJB
	private IBaseRouteDao bRouteDao;
	
	
	@EJB
	public void setForecastRouteDao(IForecastRouteDao dao){
		setDao(dao);
	}
	
	

	public ForecastRouteService() {
		super();
		df.setRoundingMode(RoundingMode.HALF_UP);
	}



	/**
	 * 
	 * @Description: TODO 计算自动字段，根据各个月的数据汇总
	 * @param route 需要计算的航线 
	 */
	private void calcAutoField(ForecastRoute route){
		/*
		 *  初始化汇总变量
		 */
		int iPax            = 0;
		int oPax            = 0;
		int pax	            = 0;
		double iRate        = 0;
		double oRate        = 0;
		double rate         = 0;
		int countFLT        = 0;
		int compareCountFLT = 0;
		int comparePax      = 0;
		int CR              = 0;
		
		Set<ForecastRouteMonth> mRoutes = route.getFmRoutes();
		if (mRoutes != null){
			/*
			 *  遍历各月份数据，进行统计
			 */			
			for(ForecastRouteMonth mRoute:mRoutes){
				if (mRoute.getRate() != 0){
					iRate   += mRoute.getiRate();
					oRate   += mRoute.getoRate();
					CR++;
					
				}
				iPax            += mRoute.getiPax();
				oPax            += mRoute.getoPax();
				countFLT        += mRoute.getCountFLT();
				compareCountFLT += mRoute.getCompareCountFLT();
				comparePax      += mRoute.getComparePax();
			}
			pax = iPax + oPax;
			if (CR != 0){
				iRate =  Double.parseDouble(df.format(iRate / CR));
				oRate =  Double.parseDouble(df.format(oRate / CR));
				rate  = Double.parseDouble(df.format((iRate+oRate)/2));
			}
		}
		/*
		 *  填充字段
		 */		
		route.setiPax(iPax);
		route.setoPax(oPax);
		route.setPax(pax);
		route.setComparePax(comparePax);
		route.setiRate(iRate);
		route.setoRate(oRate);
		route.setRate(rate);
		route.setCountFLT(countFLT);
		route.setCompareCountFLT(compareCountFLT);		
	}
	
	/**
	 * 
	 * @Description: TODO 计算实际数据与预测数据的偏差，以month指定的值为界限
	 * @param route 需要计算的航线
	 * @param eMonth 月份，该月份前的实际数据和预测数据需要进行比较
	 */
	private void calcStageField(ForecastRoute route,int eMonth){
		/*预测汇总*/
		int fcstPax	           = 0;
		double fcstRate        = 0;
		int fcstCountFLT       = 0;
		int fcstCR             = 0;
		/*实际汇总*/
		int actPax	           = 0;
		int actiPax	           = 0;
		int actoPax	           = 0;
		double actiRate         = 0;
		double actoRate         = 0;
		double actRate         = 0;
		int actCountFLT        = 0;
		int actCR              = 0;
		
		Set<ForecastRouteMonth> mRoutes = route.getFmRoutes();
		if (mRoutes != null){
			/*
			 *  遍历各月份数据，如果月份<=界限，则进行统计
			 */
			for(ForecastRouteMonth mRoute:mRoutes){
				if (mRoute.getMonth() <= eMonth){
					if (mRoute.getRate() != 0){
						fcstRate   += mRoute.getRate();
						fcstCR++;
					}
					if (mRoute.getActualRate() != 0){
						actRate  += mRoute.getActualRate();
						actiRate += mRoute.getActualiRate();
						actoRate += mRoute.getActualoRate();
						actCR++;
					}
					fcstPax      += mRoute.getPax();
					actPax       += mRoute.getActualPax();
					actiPax      += mRoute.getActualiPax();
					actoPax      += mRoute.getActualoPax();
					fcstCountFLT += mRoute.getCountFLT();
					actCountFLT  += mRoute.getActualCountFLT();
				}
			}
			/*
			 *  进行客座率计算
			 */
			if (fcstCR > 0){
				fcstRate = fcstRate / fcstCR;
			}
			if(actCR > 0){
				actRate  = actRate  / actCR;
				actiRate = actiRate / actCR;
				actoRate = actoRate / actCR;
			}
			/*
			 *  填充字段
			 */
			route.setActualCountFLT(actCountFLT);
			route.setActualPax(actPax);
			route.setActualiPax(actiPax);
			route.setActualoPax(actoPax);
			route.setActualRate(Double.parseDouble(df.format(actRate)));
			route.setActualiRate(Double.parseDouble(df.format(actiRate)));
			route.setActualoRate(Double.parseDouble(df.format(actoRate)));
			route.setActualDiffCountFLT(actCountFLT-fcstCountFLT);
			route.setActualDiffPax(actPax-fcstPax);
			route.setActualDiffRate(Double.parseDouble(df.format(actRate-fcstRate)));
		}
		
		
		
	}

	
	@Override
	public ForecastRoute add(Object obj) {

		if (obj instanceof ForecastRoute){
			ForecastRoute route = (ForecastRoute) obj;
			calcAutoField(route);
			return super.add(route);
		}else{
			return null;
		}
		
	}


	@Override
	public ForecastRoute update(Object obj) {

		if (obj instanceof ForecastRoute){
			ForecastRoute route = (ForecastRoute) obj;
			calcAutoField(route);
			return super.update(route);
		}else{
			return null;
		}
		
	}


	@Override
	public List<ForecastRoute> getAllCarriages() {
		QueryConditions condistion = new QueryConditions();
		condistion.setExpresstion(new Object[]{ForecastRoute.ROUTEHX,"<>",""});
		condistion.setFetchOneToMany("ALL");
		return dao.findByConditionAll(condistion);
	}
	
	
	private ForecastRoute createRoute(String routeHX){
		ForecastRoute route = new ForecastRoute();
		route.setRouteHX(routeHX);
		Set<ForecastRouteMonth> fmRoutes = new HashSet<ForecastRouteMonth>();
		for(int i=1;i<=12;i++){
			ForecastRouteMonth mRoute = new ForecastRouteMonth();
			mRoute.setMonth(i);
			fmRoutes.add(mRoute);
		}
		route.setFmRoutes(fmRoutes);
		return route;
	}

	/**
	 * 
	 * @Description: TODO 获取某月内航线计划飞行的架次
	 * @param year   年
	 * @param month  月
	 * @param routeHX 航线名
	 * @return 计划飞行的架次
	 */
	private int getmFLTCount(String year,String month,String routeHX){
		Date startDate = DateTimeUtil.strToDate(year+"-"+month+"-01");
		Date endDate = DateTimeUtil.getMonthLastDate(startDate);
		QueryConditions conditions = new QueryConditions(); 
		conditions.setExpresstion(new Object[]{BaseSchedule.ROUTEHX,"=",routeHX});
		return baseScheduleService.CountFlightByCondition(conditions, startDate, endDate);
		
	}
	
	/**
	 * 
	 * @Description: TODO 获取某月内航线的计划座位数
	 * @param year     年
	 * @param month    月
	 * @param routeHX  航线名
	 * @return 座位数
	 */
	private int getmFLTSeat(String year,String month,String routeHX){
		Date startDate = DateTimeUtil.strToDate(year+"-"+month+"-01");
		Date endDate = DateTimeUtil.getMonthLastDate(startDate);
		QueryConditions conditions = new QueryConditions(); 
		conditions.setExpresstion(new Object[]{BaseSchedule.ROUTEHX,"=",routeHX});
		return baseScheduleService.getSeatByCondition(conditions, startDate, endDate);
		
	}

	@Override
	public ForecastRoute createRoute(String year,String routeHX) {
		ForecastRoute fRoute = findByFieldSingle(ForecastRoute.ROUTEHX, routeHX);
		/* 查找对应航线的基准航线*/
		BaseRoute bRoute = bRouteDao.findByFieldSingle(BaseRoute.ROUTEHX, routeHX);
		/*
		 *  查询对应航线的基准缺省航线
		 */
		RouteType routeType = baseScheduleService.getRouteType(baseScheduleService.findByFieldSingle(BaseSchedule.ROUTEHX,routeHX));
		if (routeType != null){
			String dRouteHX = SYS_VARS.RouteTypeCn.get(routeType.ordinal())+"(缺省)";
			/*缺省的基准航线*/
			BaseRoute dbRoute = bRouteDao.findByFieldSingle(BaseRoute.ROUTEHX, dRouteHX); 
			if (bRoute == null){
				/*如果基准航线为空，则用缺省的基准航线代替*/
				bRoute = dbRoute;
			}
			/*获取基准航线各月的数据*/
			if (fRoute != null){
				/*删除已经存在的预测航线*/
				remove(fRoute);;
			}
			fRoute = createRoute(routeHX);
			for(ForecastRouteMonth fmRoute:fRoute.getFmRoutes()){
				String month = String.valueOf(fmRoute.getMonth());
				/**
				 *  准备进行计算的基准航线的月数据
				 */
				BaseRouteMonth mRoute =null;
				/*确定对应基准航线月的数据*/
				for(BaseRouteMonth bmRoute:bRoute.getmRoutes()){
					if (bmRoute.getMonth() == fmRoute.getMonth()){
						if (bmRoute.getRate() > 0){
							/*如果对应的基准航线的月数据不为0，
							 * 则使用该基航线月数据，否则使用缺省基准航线的月数据
							 */
							mRoute = bmRoute;
							break;
						}else{
							for(BaseRouteMonth dbmRoute:dbRoute.getmRoutes()){
								if (dbmRoute.getMonth() == fmRoute.getMonth()){
									mRoute = dbmRoute;
									break;
								}
							}
							break;
						}
					}
				}
				
				int mFLTCount = getmFLTCount(year, month,routeHX);
				int mFLTSeat  = getmFLTSeat(year, month,routeHX);
				Double miPax = mRoute.getiRate()*mFLTCount*mFLTSeat/100;
				Double moPax = mRoute.getoRate()*mFLTCount*mFLTSeat/100;
				fmRoute.setiRate(mRoute.getiRate());
				fmRoute.setoRate(mRoute.getoRate());
				fmRoute.setiPax(miPax.intValue());
				fmRoute.setoPax(moPax.intValue());
				fmRoute.setSeat(mFLTSeat);
				fmRoute.setComparePax(mRoute.getPax());
				fmRoute.setCountFLT(mFLTCount);
				fmRoute.setCompareCountFLT(mRoute.getCountFLT()/2);
				fmRoute.autoCalc();
			}
			//因为Extjs合计汇总有bug固增加一个分割月  
			ForecastRouteMonth fmRoute = new ForecastRouteMonth();
			fmRoute.setMonth(13);
			fRoute.getFmRoutes().add(fmRoute);
			/*更新预测航线*/
			update(fRoute);
		}
		
		return fRoute;
	}

	/**
	 * 
	 * @Description: TODO 利用反射方法对字段进行复制
	 * @param obj  需要复制的对象
	 * @param map  包含数据的map
	 * @param key  需要进行复制数据的key
	 * @param field key所对应的字段
	 */
	private void setActualField(Object obj,Map<String, Object> map,String key,String field){
		if (map.get(key) != null){
			Class<?>  parmClass = ObjectMethodUtil.getFieldType(obj.getClass(),field);
			ObjectMethodUtil.setFieldObject(obj, field, map.get(key), parmClass);
		}
	}

	@Override
	public void actualToForecast(ForecastRoute route,Map<String, Object> map,int eMonth) {


		/*如果月份<=界限,赋值航线各月实际数据*/
		for(ForecastRouteMonth fmRoute:route.getFmRoutes()){
			if (fmRoute.getMonth() <= eMonth){
				for(int i=0;i<ActualKeys.length;i++){
				/*
				 *   生成对应月的key Pax.01
				 */
				String m;
				String key = null;
				int Separator = ActualKeys[i].indexOf(".");
				if (fmRoute.getMonth() >=10){
					m = String.valueOf(fmRoute.getMonth());
				}else{
					m = "0"+String.valueOf(fmRoute.getMonth());
				}
				if (Separator != -1 ){
					key = ActualKeys[i].substring(0, Separator)+"."+m+ActualKeys[i].substring(Separator);
				}else{
					key = ActualKeys[i]+"."+m; 
				}
				/*
				 *  写入实际字段
				 */
				setActualField(fmRoute, map, key, ActualFields[i]);
			}
			fmRoute.setActualCountFLT(fmRoute.getActualCountFLT()/2);	
		   }
		}
		calcStageField(route, eMonth);
		update(route);
	}


	@Override
	public void actualReplaceForecast(ForecastRoute route, int eMonth) {
		for(ForecastRouteMonth fmRoute:route.getFmRoutes()){
			if (fmRoute.getMonth() <= eMonth){
				fmRoute.setiPax(fmRoute.getActualiPax());
				fmRoute.setoPax(fmRoute.getActualoPax());
				fmRoute.setPax(fmRoute.getActualPax());
				fmRoute.setiRate(fmRoute.getActualiRate());
				fmRoute.setoRate(fmRoute.getActualoRate());
				fmRoute.setRate(fmRoute.getActualRate());
				fmRoute.setCountFLT(fmRoute.getActualCountFLT());
			}
		}
		calcStageField(route, eMonth);
		update(route);
	}

	@Override
	public int getQuarterSumValue(ForecastRoute route, int quarter, String field) {
		Set<ForecastRouteMonth> fmRoutes = route.getFmRoutes();
		int sum = 0;
		for(int m=1+3*(quarter-1);m<=3*quarter;m++){
			for(ForecastRouteMonth fmRoute:fmRoutes){
				if (fmRoute.getMonth() == m){
					/*
					 *  如果字段的第二个字母为小写，则需要调用改变第一个字母大写，否则调用不改变的直接方法
					 */
					if (Character.isLowerCase(field.charAt(1))){
						sum += (Integer) ObjectMethodUtil.getFieldObject(fmRoute, field);
					}else{
						sum += (Integer) ObjectMethodUtil.getDirectFieldObject(fmRoute, field);
					}
				}
			}
		}
		return sum;
	}

	@Override
	public double getQuarterAvgValue(ForecastRoute route, int quarter, String field) {
		Set<ForecastRouteMonth> fmRoutes = route.getFmRoutes();
		Double avg = 0.0;
		for(int m=1+3*(quarter-1);m<=3*quarter;m++){
			for(ForecastRouteMonth fmRoute:fmRoutes){
				if (fmRoute.getMonth() == m){
					/*
					 *  如果字段的第二个字母为小写，则需要调用改变第一个字母大写，否则调用不改变的直接方法
					 */
					if (Character.isLowerCase(field.charAt(1))){
						avg += (Double) ObjectMethodUtil.getFieldObject(fmRoute, field);
					}else{
						avg += (Double) ObjectMethodUtil.getDirectFieldObject(fmRoute, field);
					}
					
				}
			}
		}
		return  Double.parseDouble(df.format(avg/3));
	}
	
	
	
	
	
}
