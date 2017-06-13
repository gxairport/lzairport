package com.lzairport.ais.tableviewer.celldata;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.Ais_String_Util;
import com.lzairport.ais.utils.DateTimeUtil;
import com.lzairport.ais.utils.ObjectMethodUtil;

/**
 * ICell的调度环节单元格的实现类 ，用来显示调度环节的数据，单例模式
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public class FlightDisPatchCell implements ICell {

	private static FlightDisPatchCell instance = new FlightDisPatchCell();

	private FlightDisPatchCell() {

	}

	public static FlightDisPatchCell getInstance() {
		return instance;
	}

	@Override
	public Object getData(Object element, HeaderItem field) {
		// TODO Auto-generated method stub
		Flight flight = (Flight) element;
		//取得该航班的所有调度环节
		Set<? extends FlightDisPatch> disPatchs = flight.getFlightDisPatchs();
		//field.getSubEname()的格式  例如：(开始)/FlightDisPatch.STARTREALTIME
		List<String> subEnames = Ais_String_Util.SplitSubEname(field.getSubEname());
        String eSubName =  subEnames.get(1);
        String cSubName =  subEnames.get(0);
        Object cellData = null;
        
		boolean noFound = true;
		for (FlightDisPatch disPatch :disPatchs){
			String disPatchName = disPatch.getDisPatchItem().getName()+cSubName;
			//用该航班所有调度环节与字段相比较
			if (field.getName().equals(disPatchName)){
				//如果字段一致，取出数据
				cellData = ObjectMethodUtil.getFieldObject(disPatch, eSubName); 
				noFound = false;
				break;
			}
		}
		
		if (noFound){
			//如果没有该调度环节
			return "X";
		}else {
			if (cellData instanceof Date){
				return DateTimeUtil.dateToHHMM(flight,(Date)cellData);
			}else{
				return cellData;
			}
		}
	}

	@Override
	public void setData(Object element, HeaderItem field, Object data,Class<?> parmClass) {
		// TODO Auto-generated method stub
		Flight flight = (Flight) element;
		//取得该航班的所有调度环节

		Set<? extends FlightDisPatch> disPatchs = flight.getFlightDisPatchs();
		//field.getSubEname()的格式  例如：(开始)/FlightDisPatch.STARTREALTIME
		List<String> subEnames = Ais_String_Util.SplitSubEname(field.getSubEname());
        String eSubName =  subEnames.get(1);
        String cSubName =  subEnames.get(0);
        for (FlightDisPatch disPatch :disPatchs){
			String disPatchName = disPatch.getDisPatchItem().getName()+cSubName;
			//用该航班所有调度环节与字段想比较
			if (field.getName().equals(disPatchName)){
				Class<?> type = ObjectMethodUtil.getFieldType(FlightDisPatch.class, eSubName);
				ObjectMethodUtil.setFieldObject(disPatch, eSubName, data,type);
				break;
			}
		}		
	}



	
	
}
