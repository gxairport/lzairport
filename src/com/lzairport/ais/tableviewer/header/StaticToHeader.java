package com.lzairport.ais.tableviewer.header;

import java.util.ArrayList;
import java.util.List;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.tableviewer.base.TableViewerService;


/**
 * IVcToHeader对应的静态Vc的实现类
 * @author ZhangYu
 * version 0.9a 06/11/14
 * @since JDK 1.6
 */

public class StaticToHeader implements IVcToHeader {

	private static StaticToHeader instance = new StaticToHeader();
	
	
	
	private  StaticToHeader() {
	}

	public static StaticToHeader getInstance (){
		return instance;
	}


	@Override
	public List<HeaderItem> getHeaderItem(TableViewerService viewerService,ViewConfig vc) {
		List<HeaderItem> HeaderItems = new ArrayList<HeaderItem>();
		HeaderItem headerItem = new HeaderItem();
		headerItem.setName(vc.getCname());
		headerItem.setWidth(vc.getColWidth());
		headerItem.setEname(vc.getEname());
		headerItem.setSubEname(vc.getSubEname());	
		headerItem.setItemType(vc.getItemType());
		headerItem.setFindNum(vc.getFindNum());
		HeaderItems.add(headerItem);
		return HeaderItems;
	}

}
