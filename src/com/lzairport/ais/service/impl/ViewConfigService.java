package com.lzairport.ais.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.IViewConfigDao;
import com.lzairport.ais.dao.IViewRelationDao;
import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.models.ViewGroup;
import com.lzairport.ais.models.ViewRelation;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.header.IVcToHeader;
import com.lzairport.ais.tableviewer.header.VcToHeaderFactory;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 显示配置ViewConfig的Service层实现类
 * @author ZhangYu
 * version 0.9a 24/06/14
 * @since JDK 1.6
 */

@Stateless
public class ViewConfigService  extends Service<Integer, ViewConfig> implements IViewConfigService{
	
	
	//关联类ViewRelation的Dao类，用于取得显示分组
	@EJB
	private IViewRelationDao viewRelationDao;

	@EJB
	public void setViewConfigDao(IViewConfigDao viewConfigDao){
		setDao(viewConfigDao);
	}
	
	
	/**
	 * 通过传入的对象查找所对应视图分组
	 * @param obj 一般是Viewer
	 * @return 分组类ViewGroup
	 */
	
	private ViewGroup getViewGroup(String viewID){
		//对应的RELATIONVIEWCLASS有无obj的类型
		ViewRelation vr = viewRelationDao.findByFieldSingle(ViewRelation.RELATIONVIEWCLASS,viewID);
		
		if (vr != null) {
			//返回ViewGroup
			return  vr.getViewGroup();

		}else {
			return null;
		}
		
	}
	


	@Override
	public List<HeaderItem>  getVCHeaderItems(String viewer,TableViewerService viewerService){
		List<HeaderItem> HeaderItems = new ArrayList<HeaderItem>();
		List<ViewConfig> vcs = new ArrayList<ViewConfig>();
		if (dao != null){
			
			QueryConditions conditions = new QueryConditions();
			conditions.setExpresstion(new Object[]{ViewConfig.VIEWGROUP,"=",getViewGroup(viewer)});
			AisOrder order = new AisOrder();
			order.setName(ViewConfig.SORT);
			order.setSortMode(SYS_VARS.AscSORT);
			conditions.setOrders(new AisOrder[]{order});
			vcs =  dao.findByConditionAll(conditions);
			for (ViewConfig vc:vcs){
				IVcToHeader vcToHeader = VcToHeaderFactory.createVcToHeader(vc);
				if (vcToHeader != null){
					HeaderItems.addAll(vcToHeader.getHeaderItem(viewerService,vc));
				}
			}
		}
		return HeaderItems;
		
	}

}
