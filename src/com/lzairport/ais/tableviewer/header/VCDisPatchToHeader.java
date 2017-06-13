package com.lzairport.ais.tableviewer.header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.models.aodb.FlightDisPatch;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.tableviewer.base.TableViewerService;
/**
 * IVcToHeader对应航班调度环节的实现类
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */
public class VCDisPatchToHeader implements IVcToHeader {

private static  VCDisPatchToHeader instance = new  VCDisPatchToHeader();
	
	private   VCDisPatchToHeader() {
	}

	public static  VCDisPatchToHeader getInstance (){
		return instance;
	}
	
	
	/**
	 * 
	 * @return 根据类型获取显示调度环节的字段
	 */
	public List<String> getDispFields(FlightDisPatch disPatch){
		
		switch (disPatch.getDisPatchItem().getType()) {
		case End: 
			return  Arrays.asList(FlightDisPatch.ENDREALTIME);
		case Start_End: 
			return  Arrays.asList(FlightDisPatch.STARTREALTIME,FlightDisPatch.ENDREALTIME);

		case Start_End_Times: 
			return  Arrays.asList(FlightDisPatch.STARTREALTIME,FlightDisPatch.ENDREALTIME);
		default:
			return null; 
		}
		
	}
	
	
	


	@SuppressWarnings("unchecked")
	@Override
	public  List<HeaderItem> getHeaderItem(TableViewerService viewerService,ViewConfig vc) {
		
		List<HeaderItem> headerItems = new ArrayList<HeaderItem>(); 
		
		if (viewerService != null){
			//如果viewerService不为空
			boolean noFounded;
			List<Flight> flights = ((IService<Integer,Flight>) viewerService.getService())  
					.findByConditionAll(viewerService.getConditions());
			for (Flight flight:flights){
				Set<? extends FlightDisPatch> disPatchs = flight.getFlightDisPatchs();
				for (FlightDisPatch disPatch:disPatchs){
					//查找需要加入的字段
					for(int i=0;i < FlightDisPatch.FieldCnames.size();i++){
						String cSubName = FlightDisPatch.FieldCnames.get(i);
						String eSubname = FlightDisPatch.FieldEnames.get(i);
						if (getDispFields(disPatch).contains(eSubname)){
							String disPatchName = disPatch.getDisPatchItem().getName()+cSubName;
							noFounded = true;
							for (HeaderItem item : headerItems){
								if (item.getName().equals(disPatchName)){
									noFounded = false;
									break;
								}
							}
							//如果没有找到
							if (noFounded) {
								HeaderItem headerItem = new HeaderItem();
								headerItem.setName(disPatchName);
								headerItem.setWidth(vc.getColWidth());
								headerItem.setEname(Flight.FLIGHTDISPATCHS);
								headerItem.setSubEname(cSubName+"/"+eSubname);	
								headerItem.setItemType(vc.getItemType());
								headerItems.add(headerItem);
							}
						}
					}
				}
			}
			
		}
		return headerItems;
	}



}
