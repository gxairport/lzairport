package com.lzairport.ais.tableviewer.header;

import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.utils.SYS_VARS;

/**
 *  VcToHeader的建造工厂
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */


public class VcToHeaderFactory {
	
	
	public static IVcToHeader createVcToHeader(ViewConfig vc){
		
		if (vc.getItemType().equals(SYS_VARS.ViewItemType_Dyniamic) && vc.getEname().equals(Flight.FLIGHTDISPATCHS)) {
			return VCDisPatchToHeader.getInstance();
		}else {
			return StaticToHeader.getInstance();
		}
		
		
	}
}
