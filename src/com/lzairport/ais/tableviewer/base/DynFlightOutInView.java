package com.lzairport.ais.tableviewer.base;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.service.aodb.IDynFlightService;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.TableViewerLabelProvider;



/**
 * 根据进出港的不同更新相应的Viewer显示
 * 删除已经不用显示的航班Action
 */ 
class DispConditionAction extends Action{
	
	private DynFlightOutInView view;

	public DispConditionAction(DynFlightOutInView view) {
		super("不显示已经结束的航班");
		setHoverImageDescriptor(ImageDescriptor.createFromFile(this.getClass(),"/icons/DispCon.gif"));
		this.view = view;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		view.dispCondition();
		super.run();
	}
	
}


/**
 * 根据进出港的ViewerService里的Condition定义的条件
 * 重新刷新ViewerAction
 * 
 */
class DispAllAction extends Action{
	private DynFlightOutInView view;

	public DispAllAction(DynFlightOutInView view) {
		super("显示所有的航班");
		setHoverImageDescriptor(ImageDescriptor.createFromFile(this.getClass(),"/icons/DispAll.gif"));
		this.view = view;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		view.refresh();
		super.run();
	}
	
}




/**
 * 用于显示航班进港或者出港的View的抽象类
 * @author ZhangYu
 * @version 0.9b 04/08/15
 * @since JDK 1.6
 *
 */

public abstract class DynFlightOutInView extends BaseTableViewPart  implements MouseListener {

	@Resource
	protected IViewConfigService viewConfigService;
	
	@Resource
	protected IDynFlightService dynFlightService;
	

	protected List<HeaderItem> fields;
	
	
	protected abstract void initializePopupMenu() ;
	
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new DispConditionAction(this));
		toolbarManager.add(new DispAllAction(this));
	}
	
	

	@Override
	public BaseTableViewer getTableView(Composite parent) {
		// TODO Auto-generated method stub
		viewer = new BaseTableViewer(parent, SWT.NO_BACKGROUND | SWT.FULL_SELECTION);
		GridData grdFlight= new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		grdFlight.heightHint =600;
		viewer.getTable().setLayoutData(grdFlight);
		viewer.getTable().setLinesVisible(false);
		this.getSite().setSelectionProvider(viewer);
		return viewer;
	}
	

	

	@Override
	public void setTopComposite(Composite parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBottomComposite(Composite parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<HeaderItem> getHeaderItems() {
		// TODO Auto-generated method stub
		fields = viewConfigService.getVCHeaderItems(this.getClass().getSimpleName(),getViewerService()); 
		return fields;
	}

	@Override
	public LabelProvider setViewLabelProvider() {
		// TODO Auto-generated method stub
		return new TableViewerLabelProvider(fields);
	}



	@Override
	public void initTableAttribute() {
		// TODO Auto-generated method stub
		initializePopupMenu();
		initializeToolBar();
		viewer.getTable().addMouseListener(this);
		viewer.getTable().setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		viewer.getTable().addListener(SWT.EraseItem ,new TableViewerListener(viewer));
	}

	
	/**
	 * 根据进出港的ViewerService里的Condition定义的条件
	 * 重新刷新Viewer
	 */
	public void refresh(){
		viewer.refresh();
	}
	
	/**
	 * 根据进出港的不同更新相应的Viewer显示，删除已经不用显示的航班
	 */
	public abstract void dispCondition();
	
	
	
}
