package com.lzairport.ais.tableviewer.celldata;

import java.util.Date;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;

/**
 * ICell的航班单元格的实现类 ，用来显示航班数据的数据，单例模式
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */
public class FlightStaticCell implements ICell {

	private static StaticCell staticCell = StaticCell.getInstance();
	
	private static FlightStaticCell instance = new FlightStaticCell();
	
	private FlightStaticCell(){
		
	}
	
	public static FlightStaticCell getInstance() {
		return instance;
	}	
	
	@Override
	public Object getData(Object element, HeaderItem field) {

		Object result = staticCell.getData(element, field);
		
		//日期显示的处理  如果是时间
		if (result instanceof Date){
			if ((field.getEname().equals(Flight.EXECDATE))||
			   	(field.getEname().equals(ScheduleFlight.STARTTIME))||
			   	(field.getEname().equals(ScheduleFlight.ENDTIME))){
				//如果是日期 YYYY-MM-DD
				result = DateTimeUtil.dateToYYYYMMDD((Date) result);
			}else{
				//如果是时间则是 HHMM+D
				result = DateTimeUtil.dateToHHMM((Flight)element,(Date)result);
			}
			
		}
		return result;
	}

	@Override
	public void setData(Object element, HeaderItem field, Object parm,Class<?> parmClass) {
		// TODO Auto-generated method stub
		ObjectMethodUtil.setFieldObject(element, field.getEname(),parm, parmClass);
	}



}
