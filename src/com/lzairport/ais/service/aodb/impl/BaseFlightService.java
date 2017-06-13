package com.lzairport.ais.service.aodb.impl;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.StopFlight;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.aodb.IBaseFlightService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.utils.Ais_String_Util;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;
import com.lzairport.ais.utils.SYS_VARS.OutIn;
import com.lzairport.ais.utils.SYS_VARS.RouteType;


/**
 * 基本航班Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 09/12/14
 * @param <E>
 * @since JDK 1.6
 *
 */

public abstract class BaseFlightService<K,E extends BaseFlight>  extends Service<K,E> implements IBaseFlightService<K,E> {
	
	private static	String strTransit = "-"+SYS_VARS.LocalAirportCn+"-";	

	@EJB
	protected IAreaAttributeService attributeService;

	@Override
	@SuppressWarnings("unchecked")
	public Set<? extends StopFlight> createStopFlights(BaseFlight flight, Set<? extends StopFlight> scrStops) {

		//获取经停航班的实际类型
		
		Class<StopFlight> stopClass = (Class<StopFlight>) ObjectMethodUtil.getFieldGenericType(flight, BaseFlight.STOPFLIGHTS, 0);
		
		
		//将经停航班列表写入航班
		Set<StopFlight> destStops = new HashSet<StopFlight>();
		for (StopFlight scrStop:scrStops){
			try {
				StopFlight destStop = stopClass.newInstance();
				destStop.setOrderCode(scrStop.getOrderCode());
				destStop.setStopAirport(scrStop.getStopAirport());
				destStop.setPlanLandInTime(scrStop.getPlanLandInTime());
				destStop.setPlanTakeOffTime(scrStop.getPlanTakeOffTime());
				destStops.add(destStop);
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flight.setStopFlights(destStops);
		
		return destStops;
	}


	
	@Override
	public RouteType getRouteType(BaseFlight flight) {
		
		boolean bTransit = false;
		/* 
		 *   判断航班是否为中转航班 
		 *   判断依据"深圳-柳州-成都"字符串中的"-柳州-" 
		 */
		if (flight.getRouteHX() != null  && flight.getRouteHX().indexOf(strTransit) != -1){
			bTransit = true;
		}
		
		if(flight.getAttribute() != null){
			/*如果是类型是国内并且是中转航班返回国内中转*/
			if (attributeService.getDomAttribute().equals(flight.getAttribute())){
				if (bTransit){
					return RouteType.Dom_Tra;
				}else{
					return RouteType.Dom;
				}
			}if (attributeService.getIntAttribute().equals(flight.getAttribute())){
				if (bTransit){
					return RouteType.Int_Tra;
				}else{
					return RouteType.Int;
				}
			}
		}
		
		return null;
	}

	
	@Override
	public String getBigFlightNo(BaseFlight baseFlight) {
		
		String bigFlightNo = null;
		
		if (baseFlight.getFlightNO() != null){
			/*
			 *   取得航班号除去航空航空公司代码的部分
			 *   例如：CA1860 获取1860
			 */
			String subFlt = baseFlight.getFlightNO().substring(2);
			String lnkFlightNo = null;

			/*
			 *  获取航班起始机场和落地机场的三字代码 
			 */
			String startCode = null ;
			String endCode = null ;
			if (baseFlight.getStartAirport() != null && baseFlight.getTerminalAirport() != null){
				startCode = baseFlight.getStartAirport().getThreeCharCode();
				endCode = baseFlight.getTerminalAirport().getThreeCharCode();
			}

			
			if (Ais_String_Util.isNumeric(subFlt)){
				/*
				 *  如果航班最后一位是数字 起始机场、落计机场是本场
				 *  或者起始机场、落计机场为空(说明航线不完整)
				 */
				
				
				int fltNum = Integer.valueOf(subFlt);
				if (SYS_VARS.LocalAirportThreeCode.equals(startCode) || 
					SYS_VARS.LocalAirportThreeCode.equals(endCode) ||
					startCode == null || endCode == null){

					
					/*
					 *  取得+1或者-1的航班号
					 *  例如：CA1860 +1 CA1861 -1 CA1859
					 */
					String fltNo_0 = baseFlight.getFlightNO().substring(0,2)+(fltNum+1);
					String fltNo_1 = baseFlight.getFlightNO().substring(0, 2)+(fltNum-1); 
					/*
					 * 查找+1 -1航班号起飞、落地机场对调的航班
					 * 例如： CA1860 LZH PEK  +1 CA1861 PEK LZH -1 CA1859 PEK LZH
					 */
					QueryConditions conditions = new QueryConditions();
					conditions.setExpresstion(new Object[]{BaseFlight.FLIGHTNO,"=",fltNo_0,
							SYS_VARS.LinkSqlAnd,BaseFlight.DEPAIRPORT,"=",baseFlight.getArrAirport(),
							SYS_VARS.LinkSqlAnd,BaseFlight.ARRAIRPORT,"=",baseFlight.getDepAirport()});
					BaseFlight lnkBaseFlight = findByConditionSingle(conditions);
					if (lnkBaseFlight != null){
						lnkFlightNo = lnkBaseFlight.getFlightNO();
					}else{
						conditions.setExpresstion(new Object[]{BaseFlight.FLIGHTNO,"=",fltNo_1,
								SYS_VARS.LinkSqlAnd,BaseFlight.DEPAIRPORT,"=",baseFlight.getArrAirport(),
								SYS_VARS.LinkSqlAnd,BaseFlight.ARRAIRPORT,"=",baseFlight.getDepAirport()});
						lnkBaseFlight = findByConditionSingle(conditions);
						if (lnkBaseFlight != null){
							lnkFlightNo = lnkBaseFlight.getFlightNO();
						}
					}
					
				}
			}
			

			
			if (lnkFlightNo != null){
				/*
				 *  如果找到对应的航班号
				 *  BigFlightNo规则  进港航班号/出港航班号
				 *  例如:CA1860 LZH PEK CA 1859 PEK LZH BigFlghtNo CA1859/CA1860
				 */
				if (OutIn.Dep.equals(baseFlight.getIsOutIn())){
					/*
					 *  如果是出港
					 */
					bigFlightNo = lnkFlightNo+"/"+baseFlight.getFlightNO();
				}else{
					/* 
					 * 如果是进港
					 */
					bigFlightNo = baseFlight.getFlightNO()+"/"+lnkFlightNo;
				}
			}else{
				/*
				 *  如果没有关联的航班号，则本身航班号作为BigFlightNo
				 */
				bigFlightNo = baseFlight.getFlightNO();
			}
			
		}
		return bigFlightNo; 
	}
	
	

}
