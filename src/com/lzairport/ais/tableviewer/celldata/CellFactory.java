package com.lzairport.ais.tableviewer.celldata;

import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.SYS_VARS;


/**
 * Cell的建造工厂，用处返回TableViwer的各单元格的显示方式
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public class CellFactory {

	public static ICell createCell(Object element,HeaderItem field){
		if (field.getItemType().equals(SYS_VARS.ViewItemType_Static) || field.getItemType().equals(SYS_VARS.ViewItemType_Bool)){
			//如果是静态字段
			if (element instanceof BaseFlight){
				//如果是航班信息实体，返回航班信息静态Cell
				return FlightStaticCell.getInstance();
			}else{
				//否则返回通用Cell
				return StaticCell.getInstance();
			}
		}else if (field.getItemType().equals(SYS_VARS.ViewItemType_Dyniamic) && field.getEname().equals(Flight.FLIGHTDISPATCHS)) {
			//如果是动态的航班服务字段，返回航班服务Cell
			return FlightDisPatchCell.getInstance();
		}else if (field.getItemType().equals(SYS_VARS.ViewItemType_Dyniamic) && field.getEname().equals(ScheduleFlight.EXECWEEK)) {
			//如果是执行频率字段，返回执行频率单元格
			return FlightWeekCell.getInstance();
		}else if (field.getItemType().equals(SYS_VARS.ViewItemType_Enum)){
			//如果是枚举类型，返回枚举类型单元格
			return  EnumCell.getInstance();
		}else {
			return null;
		}
		
		
	}
	
}
