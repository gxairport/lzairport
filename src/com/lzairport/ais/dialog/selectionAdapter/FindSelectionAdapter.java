package com.lzairport.ais.dialog.selectionAdapter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.springframework.context.support.AbstractApplicationContext;

import com.lzairport.ais.dialog.CommonFindDialog;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.tableviewer.base.BaseTableViewer;


/**
 * 通用的查找选择适配器
 * @author ZhangYu
 * @version 0.9a 03/01/14
 * @since JDK 1.6
 */

public class FindSelectionAdapter extends SelectionAdapter {
	/**
	 * 处理数据对象的Service
	 */
	private IService<Integer,? extends Object> service;
	
	/**
	 *  视图Viewer主要是用来提取与视图匹配的字段
	 */
	private BaseTableViewer viewer;

	private AbstractApplicationContext ctx;
	

	public FindSelectionAdapter(IService<Integer,? extends Object> service,
			BaseTableViewer viewer,AbstractApplicationContext ctx) {
		super();
		this.service = service;
		this.viewer = viewer;
		this.ctx = ctx;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {

		Dialog dialog = new CommonFindDialog(service, viewer,ctx);
		dialog.open();
		super.widgetSelected(e);
	}

	

}
