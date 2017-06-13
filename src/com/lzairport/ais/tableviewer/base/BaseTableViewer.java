package com.lzairport.ais.tableviewer.base;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.RowNumberLabelProvider;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 基础数据表格的实现类，封装了基本的表格实现方法
 * 调用init方法，建立表头，设置内容
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */

public class BaseTableViewer extends TableViewer {
	
	private String viewerConfigName;
	
	private TableViewerColumn numberColumn; 
	
	private TableViewerService viewerService;

	public BaseTableViewer(Composite parent, int style) {
		super(parent, style);
		this.getTable().setHeaderVisible(true);
		this.getTable().setLinesVisible(true);
	}
	
	
	
	
	/**
	 * @return the viewerConfigName
	 */
	public String getViewerConfigName() {
		return viewerConfigName;
	}

	/**
	 * @param viewerConfigName the viewerConfigName to set
	 */
	public void setViewerConfigName(String viewerConfigName) {
		this.viewerConfigName = viewerConfigName;
	}

	/**
	 * @return the viewerService
	 */
	public TableViewerService getViewerService() {
		return viewerService;
	}




	/**
	 * @param viewerService the viewerService to set
	 */
	public void setViewerService(TableViewerService viewerService) {
		this.viewerService = viewerService;
	}




	/**
	 * 创建TableViewer的表头,并设置排序
	 *
	 */
	public  void createTableHeader(List<HeaderItem> headerItems){
		
		if (headerItems != null && !headerItems.isEmpty()){
			for (final HeaderItem headerItem:headerItems){
				//创建表头并且设置
				
				
				if (SYS_VARS.ViewItemType_Num.equals(headerItem.getItemType())){
					numberColumn = new TableViewerColumn(this, SWT.LEFT);
					numberColumn.getColumn().setText(headerItem.getName());
					numberColumn.getColumn().setWidth(headerItem.getWidth());
				}else{
					TableColumn tcItem = new TableColumn(this.getTable(),SWT.CENTER|SWT.MULTI);
					final TableViewer viewer = this;
					tcItem.setText(headerItem.getName());
					tcItem.setWidth(headerItem.getWidth());
					tcItem.setMoveable(true);
					//设置排序方式
					tcItem.addSelectionListener(new SelectionAdapter() {
						private boolean asc = true;
						public void widgetSelected(SelectionEvent e) {
								viewer.setSorter(new CommViewSorter(headerItem,asc));
								asc = !asc;
							}
						
					});
				}
			}
		}
		
	}
	
	
	/**
	 * 清除TableViewer的表头
	 */
	public void clearTableHeader(){
		TableColumn[] tcItems = this.getTable().getColumns();
		for (TableColumn tcItem:tcItems){
			tcItem.dispose();
		}
		
	}
	/**
	 * 初始化TableViewer，创建表格，设置表格的ContentProvider，LabelProvider，Input
	 * @param headerItems 表格头集合
	 * @param contentProvider 内容提供者
	 * @param labelProvider 标签提供者
	 * @param inputData 输入数据
	 */
	public void init(List<HeaderItem> headerItems,IStructuredContentProvider contentProvider,
			LabelProvider labelProvider,Object inputData){
		this.createTableHeader(headerItems);
		this.setContentProvider(contentProvider);
		this.setLabelProvider(labelProvider);
		if (numberColumn != null){
			numberColumn.setLabelProvider(new RowNumberLabelProvider());
		}
		if (inputData != null ){
			this.setInput(inputData);
		}

	}
	
		



}
