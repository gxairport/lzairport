package com.lzairport.ais.dialog.selectionAdapter;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.dialog.EditDialog;
import com.lzairport.ais.dialog.EditDialogFactory;
import com.lzairport.ais.service.IService;


/**
 * 通用的编辑选择适配器
 * @author ZhangYu
 * @version 0.9a 24/06/24
 * @since JDK 1.6
 */


public class EditSelectionAdapter extends SelectionAdapter {
	
	/**
	 * 处理数据对象的Service
	 */
	private IService<Integer,? extends Object> service;
	
	
	/**
	 * 需要处理的数据对象
	 */

	private Object object;
	
	
	/**
	 *  Spring的容器
	 */
	private AbstractApplicationContext ctx;
	
	/**
	 *  视图Viewer主要是用来提取与视图匹配的字段
	 */
	private Object viewer;

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	
	public EditSelectionAdapter(IService<Integer,? extends Object> service,AbstractApplicationContext ctx,Object viewer) {
		super();
		this.service = service;
		this.ctx = ctx;
		this.viewer = viewer;
	}
	
	
	/**
	 * 选择后调用EditDialogFactory工厂类根据不同的Viewer创建不同的EditDialog
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		EditDialog dialog = EditDialogFactory.createEditDialog(service, object,ctx,viewer);
		dialog.open();
		super.widgetSelected(e);
	}

	
	

}
