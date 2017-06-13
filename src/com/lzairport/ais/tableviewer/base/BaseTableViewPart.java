package com.lzairport.ais.tableviewer.base;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.lzairport.ais.contentchange.IContentChangeProvider;
import com.lzairport.ais.tableviewer.base.BaseTableViewer;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.TableViewerContentProvider;
import com.lzairport.ais.tableviewer.provider.ViewerContentProvider;



/**
 * 基础数据表格的抽象类，用表格的方式来显示数据，定义了操作表格的基本方法。
 * 实现类需要实现抽象类的抽象方法即可实现数据在表格中显示
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */

public abstract class BaseTableViewPart extends ViewPart  {
	
	
	protected BaseTableViewer viewer;
	
	protected TableViewerService viewerService;  
	
	/**
	 * 用于更新视图的内容改变提供者，JMS消息
	 */
	@Resource(name="JMSMessage")
	protected IContentChangeProvider provider;
	
	
	protected ViewerContentProvider tableViewerContentProvider;


	/**
	 *返回一个TableViewer的实例对象抽象方法  
	 * @param parent 父类
	 * @return TableViewer的实例对象可以为null
	 */
	public abstract BaseTableViewer getTableView(Composite parent);
	
	
	/**
	 * 返回View上半部分的Composite控件抽象方法 
	 * @param parent 父类
	 */
	public abstract void setTopComposite(Composite parent);
	
	/**
	 * 返回View下半部分的Composite控件抽象方法 
	 * @param parent 父类
	 */
	public abstract void setBottomComposite(Composite parent);
	
	
	/**
	 * 设置TableViewer表头需要显示的HeaderItems传入参数
	 * @return 所需的HeaderItems
	 */
	public abstract List<HeaderItem> getHeaderItems();
	
	/**
	 * 设置与TableViewer表头显示相对应的LabelFields
	 * @return 所需的LabelFields
	 */
	public abstract LabelProvider setViewLabelProvider(); 
	
	
	/**
	 * 获取InputData对象
	 * @return Service 对象
	 */
	public abstract TableViewerService getViewerService();
	

	
	public abstract void initTableAttribute();
		
	
	public BaseTableViewPart() {
	}
	
	
	/**
	 * 产生View中的各个控件。
	 */
	@Override
	public void createPartControl(Composite parent) {

		setTopComposite(parent);
		viewer = getTableView(parent);
		tableViewerContentProvider = new TableViewerContentProvider(viewer);
		//
		provider.addContentChangeListener(tableViewerContentProvider);
		viewer.init(getHeaderItems(), tableViewerContentProvider, setViewLabelProvider(), getViewerService());
		viewer.setViewerConfigName(this.getClass().getSimpleName());
		viewer.setViewerService(getViewerService());
		setBottomComposite(parent);
		initTableAttribute();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}



	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
	

}
