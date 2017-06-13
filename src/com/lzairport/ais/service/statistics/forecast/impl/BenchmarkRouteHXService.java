package com.lzairport.ais.service.statistics.forecast.impl;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.statistics.forecast.IBenchmarkRouteHXDao;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.RouteHX;
import com.lzairport.ais.models.statistics.forecast.BenchmarkMonth;
import com.lzairport.ais.models.statistics.forecast.BenchmarkRouteHX;
import com.lzairport.ais.service.aodb.IAirlinesService;
import com.lzairport.ais.service.aodb.IRouteHXService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.forecast.IBenchmarkMonthService;
import com.lzairport.ais.service.statistics.forecast.IBenchmarkRouteHXService;

/**
 * FileName      BenchmarkRouteHXService.java
 * @Description  TODO 预测航班的基准数据Service接口的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月18日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月18日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class BenchmarkRouteHXService extends Service<Integer, BenchmarkRouteHX> implements IBenchmarkRouteHXService {
	
	@EJB
	private IBenchmarkMonthService bmService;
	
	@EJB
	private IRouteHXService routeHXService;
	
	@EJB
	private IAirlinesService airlinesService;
	
	/**
	 *  map中各key值的定义
	 */
	private static String kAirlines  = "AIRLINESGROUP";
	private static String kRouteHX   = "ROUTEHXGROUP";
	private static String kOutIn     = "OUTINGROUP";
	private static String kMonth     = "MONTHGROUP";
	private static String kCraftCode = "CRAFTCODEGROUP";
	private static String kRate      = "RATEAVG";
	private static String kCountFlt  = "COUNTFLT";
	private static String kSeat      = "AVGSEAT";
	private static String kGoods     = "GOODS";
	private static String kPax       = "PAX";
	private static String kLocalPax  = "LOCALPAX";
	
	
	private static DecimalFormat df = new DecimalFormat("#.00");
	

	@EJB
	public void setBenchmarkRouteHXDao(IBenchmarkRouteHXDao dao ){
		setDao(dao);
	}
	
	private BenchmarkRouteHX createBrHX(String routeHX){
		RouteHX rHX = routeHXService.findByFieldSingle(RouteHX.CNNAME, routeHX);
		BenchmarkRouteHX brHX = new BenchmarkRouteHX();
		brHX.setRouteHX(routeHX);
		brHX.setbMonths(new HashSet<BenchmarkMonth>());
		if (rHX != null){
			brHX.setAttribute(rHX.getAttribute());
			brHX.setTransfer(rHX.isTransfer());
		}
		return brHX;
	}
	
	@Override
	public void updateForReportList(List<Map<String, Object>> rList,String year) {
		
		for(Map<String, Object> map:rList){
			String routeHX = map.get(kRouteHX).toString();
			System.out.println(routeHX);
			BenchmarkRouteHX brHX = findByFieldSingle(BenchmarkRouteHX.ROUTEHX, routeHX);
			if (brHX == null){
				brHX = createBrHX(routeHX);
			}
			
			Airlines airlines = airlinesService.findByFieldSingle(Airlines.CNSHORTNAME,map.get(kAirlines));
			
			int month    = 0;
			int countFlt = 0;
			int seat     = 0;
			int avgGoods = 0;
			int pax = 0;
			int localPax = 0;
			Double rate  = 0.0;
			Double localRate = 0.0;
			String craftCode  = null;
			String outIn      = null;
			
			if (map.get(kMonth) != null){
				month = Integer.valueOf(map.get(kMonth).toString());
			}
			if (map.get(kCountFlt) != null){
				countFlt = Integer.valueOf(map.get(kCountFlt).toString());
			}
			if (map.get(kGoods) != null && countFlt != 0){
				avgGoods = Integer.valueOf(map.get(kGoods).toString())/countFlt;
			}
			if (map.get(kRate) != null){
				rate = Double.valueOf(map.get(kRate).toString());
				rate = Double.valueOf(df.format(rate));
			}
			if (map.get(kSeat) != null){
				seat = Double.valueOf(map.get(kSeat).toString()).intValue();
			}
			if (map.get(kCraftCode) != null){
				craftCode = map.get(kCraftCode).toString();
			}
			if (map.get(kOutIn) != null){
				outIn = map.get(kOutIn).toString();
			}
			if (map.get(kOutIn) != null){
				pax = Integer.valueOf(map.get(kPax).toString());
			}
			if (map.get(kOutIn) != null){
				localPax = Integer.valueOf(map.get(kLocalPax).toString());
			}
			
			/*
			 * 查找符合条件的基准月信息
			 * 需要匹配航空公司，月份，年份，机型代码，航线
			 */
			
			Set<BenchmarkMonth> bMonths = brHX.getbMonths();
			BenchmarkMonth bMonth = null;
			for (BenchmarkMonth benchmarkMonth:bMonths){
				if (benchmarkMonth.getAirlines() != null && benchmarkMonth.getCraftCode() != null &&
						benchmarkMonth.getAirlines().equals(airlines) && benchmarkMonth.getMonth()== month &&
						benchmarkMonth.getCraftCode().equals(craftCode) && benchmarkMonth.getYear().equals(year)){
					bMonth = benchmarkMonth;
					break;
				}
			}
			
			
			
			if (bMonth == null){
				bMonth = new BenchmarkMonth();
				bMonth.setAirlines(airlines);
				bMonth.setCraftCode(craftCode);
				bMonth.setMonth(month);
				bMonth.setYear(year);
				bMonths.add(bMonth);
			}
			bMonth.setAvgSeat(seat);
			bMonth.setCountFlt(countFlt);
			if ("Dep".equals(outIn)){
				if (pax != 0){
					localRate = rate / pax * localPax;
					localRate = Double.valueOf(df.format(localRate));
				}
				bMonth.setOutRate(rate);
				bMonth.setAvgOutCargo(avgGoods);
				bMonth.setLocalRate(localRate);
				
			}else if ("Arr".equals(outIn)){
				bMonth.setInRate(rate);
				bMonth.setAvgInCargo(avgGoods);
			}
			update(brHX);
		}
	}
	
}
