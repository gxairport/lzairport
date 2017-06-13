package com.lzairport.ais.dialog;

import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.ScheduleFlight;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.service.aodb.IScheduleFlightService;

/**
 * 编辑对话框的建造工厂类
 * 根据不同的参数选择创造不同的编辑对话框
 * @author ZhangYu
 * @version 0.9a 25/06/14
 * @since JDK 1.6
 */

public class EditDialogFactory {

	/**
	 * 
	 * @param service
	 * @param object
	 * @param ctx
	 * @param viewer
	 * @return
	 */
	public  static  EditDialog  createEditDialog(IService<Integer,? extends Object> service, Object object,
			AbstractApplicationContext ctx,Object viewer){
		EditDialog dialog = null;
		
		
		if (service.getModelClass().getName().equals(ViewConfig.class.getName())){
		
			dialog = new ViewConfigEditDialog((IViewConfigService)service, object,viewer,ctx);
			
		}else if (Flight.class.isAssignableFrom(service.getModelClass())){

			dialog = new FlightEditDialog(service,object, ctx);
			
		}else if (service.getModelClass().getName().equals(ScheduleFlight.class.getName())){
			
			dialog = new ScheduleFlightEditDialog((IScheduleFlightService)service, object, ctx);
			
		}else {
			
			dialog = new CommonEditDialog(service, object,viewer.getClass().getSimpleName(),ctx);
			
		}
		return dialog;
		
	}

}
