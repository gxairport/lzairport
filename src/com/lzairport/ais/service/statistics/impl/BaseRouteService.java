package com.lzairport.ais.service.statistics.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.dao.statistics.IBaseRouteDao;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.statistics.BaseRoute;
import com.lzairport.ais.models.statistics.BaseRouteMonth;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IBaseRouteService;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.RouteType;



/**
 * 
 * FileName      BaseRouteService.java
 * @Description  TODO 预测航线每个月的基准数据的Service实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月7日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年7月7日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class BaseRouteService extends Service<Integer, BaseRoute> implements IBaseRouteService {
	
	
	private static String RouteHX = "ROUTEHXGROUP";
	
	private static String Flight  = "FLIGHTGROUP";
	
	private static String Rate = "RATE";
	
	private static String SeatSum = "SEATSUM";
	
	private static String Seat ="SEAT";
	
	private static String Dep = "Dep";
	
	private static String Arr = "Arr";
	
	private static String Pax = "PAX";
	
	private static String sRouteType="ROUTETYPE";
	
	private static int LimitFLT =15;
	
	private static String CountFLT = "COUNTFLT";
	
	private static String[] headers = {Pax,CountFLT,SeatSum};
	
	private static String[] suffixs = {Dep,Arr};
	
	private static String[] mNum = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	
	private static String iRate ="iRate";
	
	private static String oRate = "oRate";
	
	private static String count = "count";

	@SuppressWarnings("unchecked")
	private static Map<String,Double>[] mapValues = new Map[12];
	
	private static String defaultCN="(缺省)";
	
	private DecimalFormat df = new DecimalFormat("#.0");

	@EJB
	private IHisFlightService flightService;


	
	@EJB
	public void setBaseRouteDao(IBaseRouteDao dao){
		setDao(dao);
	}
	
	
	
	public BaseRouteService() {
		super();
		df.setRoundingMode(RoundingMode.HALF_UP);
	}



	/**
	 * 
	 * @Description: TODO 根据航线名，创建一条基准航线
	 * @param routeHX 航线名
	 * @return 航线名为给定值的基准航线
	 */
	private BaseRoute createRoute(String routeHX){
		BaseRoute result = new BaseRoute();
		result.setRouteHX(routeHX);
		Set<BaseRouteMonth> mRoutes = new HashSet<BaseRouteMonth>();
		for(String m:mNum){
			BaseRouteMonth mRoute = new BaseRouteMonth();
			mRoute.setMonth(Integer.valueOf(m));
			mRoutes.add(mRoute);
		}
		result.setmRoutes(mRoutes);
		return result;
	}
	

	/**
	 * 
	 * @Description: TODO 将原始Map中key所对应的值放入目标Map中
	 * @param mapOrg   原始的Map
	 * @param mapDest  目标的Map
	 * @param key      需要放入Key值
	 */
	private void put(Map<String, Object>mapOrg,Map<String, Object>mapDest,String key){
		Object obj = mapOrg.get(key);
		if (obj != null){
			mapDest.put(key, obj);
		}else{
			mapDest.put(key,0);
		}
	}

	/**
	 * 
	 * @Description: TODO 合并 mapFound 与 mapAdd key所对应的数值并放入mapFound中
	 * @param mapFound  合并Map主体
	 * @param mapAdd    累加Map
	 * @param key       需要累加的Key值
	 */
	private void calc(Map<String, Object> mapFound,Map<String, Object> mapAdd,String key){
		Double n1 = Double.parseDouble(mapFound.get(key).toString());
		Double n2 = Double.parseDouble(mapAdd.get(key).toString());
		mapFound.put(key, n1+n2);		
	}
	
	/**
	 * 
	 * @Description: TODO 根据传入的Map创建一个新的Map 
	 * @param map    原始的MAP
	 * @param headers 头数组
	 * @param suffixs 后缀数组
	 * @return Map <ROUTEHX "柳州-首都/北京"><PAX.01 1000> <COUNTFLT.01 12> <PAX.01.Dep 500> <PAX.01.Arr 500>
	 */
	private Map<String, Object> createMap(Map<String, Object>map){
		Map<String, Object> mResult = new HashMap<String, Object>();
		mResult.put(sRouteType, map.get(sRouteType));
		for(String m:mNum){


			for(String header:headers){
				/*放入<PAX.01 1000> <COUNTFLT.01 12><SEATSUM 500>放入Map*/
				put(map, mResult, header+"."+m);
				for(String suffix:suffixs){
					/*放入<PAX.01.Dep 500> <PAX.01.Arr 500 >.......放入MAP*/					
					put(map, mResult, header+"."+m+"."+suffix);
				}
			}
		}
		return mResult;
	}

	/**
	 * 
	 * @Description: TODO 合并MAP
	 * @param mapFound  找到的合并的Map
	 * @param mapAdd    合并的主体
	 * @param headers   
	 * @param suffixs
	 */
	private void mergeMap(Map<String, Object> mapFound,Map<String, Object> mapAdd){
		for(String m:mNum){
			
			for(String header:headers){
				/*累加<PAX.01 1000> <COUNTFLT.01 12><SEATSUM 500>放入Map*/
				calc(mapFound, mapAdd, header+"."+m);
				for(String suffix:suffixs){
					/*累加<PAX.01.Dep 500> <PAX.01.Arr 500 >.......放入MAP*/					
					calc(mapFound, mapAdd, header+"."+m+"."+suffix);
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @Description: TODO更新缺省航线的数据
	 */
	public void updateDefaultRoute(){
		
		/*赋值累加的数组*/
		for(int i=0;i<mapValues.length;i++){
			mapValues[i] = new HashMap<String, Double>();
		}
		
		QueryConditions conditions = new QueryConditions();
		for(String routeTypeCn:SYS_VARS.RouteTypeCn){
			/*初始化用于累加的数组*/
			for(int i=0;i<mapValues.length;i++){
				mapValues[i].put(iRate, 0.0);
				mapValues[i].put(oRate, 0.0);
				mapValues[i].put(Rate, 0.0);
				mapValues[i].put(CountFLT, 0.0);
				mapValues[i].put(Pax,0.0);
				mapValues[i].put(Seat, 0.0);
				mapValues[i].put(count, 0.0);
			}
			/*设置缺省航线名称 例如：国内中转(缺省)*/
			String routeHX = routeTypeCn+defaultCN;
			/*根据对应的顺序找到对应RouteType的值*/
			RouteType routeType = RouteType.values()[SYS_VARS.RouteTypeCn.indexOf(routeTypeCn)];
			/*查询所有符合条件的数据不等于航线名称且等于航线类型*/
			conditions.setExpresstion(new Object[]{BaseRoute.ROUTEHX,"<>",routeHX,"AND",BaseRoute.ROUTETYPE,"=",routeType});
			List<BaseRoute> routes = findByConditionAll(conditions);
			/*将所有符合条件的航线分每个月进行累加*/
			for (BaseRoute route:routes){
				for(BaseRouteMonth mRoute:route.getmRoutes()){
					if (mRoute.getRate() > 0){
						/*如果当月客座率大于零，说明有数据可进行累加*/
						int m = mRoute.getMonth()-1;
						mapValues[m].put(iRate,mapValues[m].get(iRate)+mRoute.getiRate());
						mapValues[m].put(oRate,mapValues[m].get(oRate)+mRoute.getoRate());
						mapValues[m].put(Rate,mapValues[m].get(Rate)+mRoute.getRate());
						mapValues[m].put(Seat,mapValues[m].get(Seat)+mRoute.getSeat());
						mapValues[m].put(count,mapValues[m].get(count)+1);
					}
				}
			}
			/*根据累加的数据更新缺省航线*/
			BaseRoute defaultRoute = findByFieldSingle(BaseRoute.ROUTEHX, routeHX);
			if (defaultRoute == null){
				defaultRoute = createRoute(routeHX);
				defaultRoute.setRouteType(routeType);
			}
			for(BaseRouteMonth mRoute:defaultRoute.getmRoutes()){
				int m = mRoute.getMonth()-1;
				if (mapValues[m].get(count) == 0){
					/*
					 * 如果count对应的值为零，说明此缺省航线该月的数据都为0
					 * 为了避免被零除的错误，复制count对应的值为1，被除数为0后面计算结果还是为0
					 */
					mapValues[m].put(count, 1.0);
				}
				Double mIRate = mapValues[m].get(iRate)/mapValues[m].get(count);
				Double mORate = mapValues[m].get(oRate)/mapValues[m].get(count);
				Double mRate  = mapValues[m].get(Rate)/mapValues[m].get(count);
				Double mSeat  = mapValues[m].get(Seat)/mapValues[m].get(count);
				/*
				 *  缺省的基准航班数据一般用在没有基准数据的预测航线上，
				 *  说明基准数据并没有以前的起降架次和人数，以零代替
				 *  其他数据以累加的平均值作为基准的数据
				 *  
				 */
				mRoute.setCountFLT(mapValues[m].get(CountFLT).intValue());
				mRoute.setPax(mapValues[m].get(Pax).intValue());
				mRoute.setSeat(mSeat.intValue());
				mRoute.setiRate(Double.parseDouble(df.format(mIRate)));
				mRoute.setoRate(Double.parseDouble(df.format(mORate)));
				mRoute.setRate(Double.parseDouble(df.format(mRate)));
			}
			update(defaultRoute);
		}
	}


	@Override
	public BaseRoute update(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof BaseRoute){
			/**
			 *  汇总所有的月份数据
			 */
			BaseRoute route = (BaseRoute) obj;
			Set<BaseRouteMonth> mRoutes = route.getmRoutes();
			int noZeroCount = 0;
			double inRate  = 0.0;
			double outRate = 0.0;
			int countFLT = 0;
            int seat = 0;					
			for(BaseRouteMonth mRoute:mRoutes){
				if (mRoute.getRate() != 0){
					noZeroCount++;
					inRate  += mRoute.getiRate();
					outRate += mRoute.getoRate();
					countFLT += mRoute.getCountFLT();
					seat += mRoute.getSeat();					
				}
				
			}
			route.setCountFLT(countFLT);
			if (noZeroCount !=0){
				route.setSeat(seat/noZeroCount);
				route.setiRate(Double.parseDouble(df.format(inRate/noZeroCount)));
				route.setoRate(Double.parseDouble(df.format(outRate/noZeroCount)));
				route.setRate(Double.parseDouble(df.format((inRate+outRate)/2/noZeroCount)));
			}else{
				route.setSeat(0);
				route.setiRate(0);
				route.setoRate(0);
				route.setiRate(0);
			}
			
			return super.update(route);
		}else{
			return null;
		}
	}


	@Override
	public void updateForReportList(List<Map<String, Object>> rList) {

		/*在Map中填入航线信息*/
		for(Map<String, Object> map:rList){
			HisFlight flight = flightService.findByFieldSingle(HisFlight.BIGFLIGHTNO,  map.get(Flight));
			if (flight != null){
				map.put(RouteHX, flight.getRouteHX());
				map.put(sRouteType,flightService.getRouteType(flight));
			}
		}
		
		
		/*合并报表mList*/
		List<Map<String, Object>> mList   = new ArrayList<Map<String,Object>>();
		/*
		 * 将相同的航线进行合并
		 */
		for(Map<String, Object> map:rList){
			Map<String, Object> mapFound =null;
			for(Map<String, Object> mapFLT:mList){
				if (map.get(RouteHX).equals(mapFLT.get(RouteHX))){
					/*如果找到对应的航线*/
					mapFound = mapFLT;
				}
			}
			
			Map<String, Object> mapAdd = createMap(map);
			
			if (mapFound == null){
				/*如果对应的航线没有找到，新建map添加进mList中*/
				mapAdd.put(RouteHX, map.get(RouteHX));
				mList.add(mapAdd);
			}else{
				/*如果找到对应的航线，合并对应的Map*/
				mergeMap(mapFound, mapAdd);
			}
		}
		
		/*计算各航线的客座率,座位数，飞行架次*/
		for(Map<String, Object> map:mList){
			for(String m:mNum){
				Double seat = 0.0;
				String seatSumKey = SeatSum+"."+m;
				String countFLTKey = CountFLT+"."+m;
				/*将架次除以2重新放入Map*/
				Double seatSum = Double.parseDouble(map.get(seatSumKey).toString());
				Double countFLT = Double.parseDouble(map.get(CountFLT+"."+m).toString());
				map.put(countFLTKey, countFLT);
				/*放入*/
				if (countFLT != 0){
					seat = Double.parseDouble(df.format(seatSum/countFLT));
					map.put(Seat+"."+m, seat);
				}else{
					map.put(Seat+"."+m, 0);
				}
				
				for(String suffix:suffixs){
					 String rateKey    = Rate+"."+m+"."+suffix;
					 String paxKey     = Pax+"."+m+"."+suffix;
					 seatSumKey        = SeatSum+"."+m+"."+suffix;
					 Double pax = Double.parseDouble(map.get(paxKey).toString());
					 seatSum = Double.parseDouble(map.get(seatSumKey).toString());
					 Double rate = 0.0;
					 if (seatSum != 0.0){
						 rate = Double.parseDouble(df.format(pax/seatSum*100));
						 map.put(rateKey, rate);
					 }else{
						 map.put(rateKey, 0);
					 }
				}
				
				
			}			
		}
		
		
		/*用mList里的数据更新数据库*/
		for(Map<String, Object> map:mList){
			BaseRoute route = findByFieldSingle(BaseRoute.ROUTEHX, map.get(RouteHX));
			if (route == null){
				route = createRoute(map.get(RouteHX).toString());
			}
			Set<BaseRouteMonth> mRoutes = route.getmRoutes();
			int countFLT = 0;
			for(BaseRouteMonth mRoute:mRoutes){
				for(String m:mNum){
					int int_m = Integer.valueOf(m);
					if (mRoute.getMonth()  == int_m){
						Double mSeat = Double.parseDouble(map.get(Seat+"."+m).toString());
						Double mCountFLT = Double.parseDouble(map.get(CountFLT+"."+m).toString());
					    Double miRate = Double.parseDouble(map.get(Rate+"."+m+"."+Arr).toString());
					    Double moRate = Double.parseDouble(map.get(Rate+"."+m+"."+Dep).toString());
					    Double mRate = Double.parseDouble(df.format((miRate+moRate)/2));
					    Double mpax  = Double.parseDouble(map.get(Pax+"."+m).toString()); 
					    if (mRate > 0){
					    	/*如果客座率大于0才进行更新*/
						    countFLT += mCountFLT.intValue();
						    mRoute.setiRate(miRate);
						    mRoute.setoRate(moRate);
						    mRoute.setRate(mRate);
						    mRoute.setCountFLT(mCountFLT.intValue());
						    mRoute.setSeat(mSeat.intValue());
						    mRoute.setPax(mpax.intValue());
					    }
					}
				}
			}
			if (countFLT >= LimitFLT){
				/*架次要超过指定的架次数才进行保存*/
				if (map.get(sRouteType) != null){
					route.setRouteType((RouteType) map.get(sRouteType));
				}
				update(route);
			}
		}
		updateDefaultRoute();
		
	}



	
	
	
}
