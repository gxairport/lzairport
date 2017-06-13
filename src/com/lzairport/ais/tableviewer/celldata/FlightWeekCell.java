package com.lzairport.ais.tableviewer.celldata;

import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.SYS_VARS.Week;


/**
 * ICell的航班执行周期的实现类 ，用来显示航班执行周期的数据，单例模式
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public class FlightWeekCell implements ICell {

	private static FlightWeekCell instance = new FlightWeekCell();
	
	private FlightWeekCell() {
		
	}
	
	public static  FlightWeekCell getInstance() {
		return instance;
	}
	
	@Override
	public Object getData(Object element, HeaderItem field) {
		// TODO Auto-generated method stub
		ScheduleFlight flight = (ScheduleFlight) element;
		String execWeek = "";
		
		Week[]  weeks = Week.values();
		
		for(Week week:weeks){
			if (flight.getExecWeek().contains(week)){
				execWeek += String.valueOf(week.ordinal()+1);
			}else{
				execWeek +=".";
			}
		}
		return execWeek;
	}

	@Override
	public void setData(Object element, HeaderItem field, Object parm,Class<?> parmClass) {
		// TODO Auto-generated method stub
		
	}

}
