package com.lzairport.ais.service;

import java.util.List;

import javax.ejb.Remote;

import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.header.HeaderItem;

/**
 * 显示配置ViewConfig的Service层接口
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IViewConfigService extends IService<Integer, ViewConfig> {
	
	
	/**
	 * 返回指定Viewer所对应的HeaderItem
	 * @param viewer 对应的表名
	 * @param viewerService 对应表的服务类
	 * @return 指定Viewer所对应的HeaderItem
	 */
	
	public List<HeaderItem>  getVCHeaderItems(String viewer,TableViewerService viewerService);

}
