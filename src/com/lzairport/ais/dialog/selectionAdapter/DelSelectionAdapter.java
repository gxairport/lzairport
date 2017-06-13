package com.lzairport.ais.dialog.selectionAdapter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import com.lzairport.ais.service.IService;

/**
 * 通用删除选择的适配器类
 * @author ZhangYu
 * @version 0.9a 25/06/24
 * @since JDK 1.6
 */


public class DelSelectionAdapter extends SelectionAdapter {
	
	/**
	 * 处理数据对象的Service
	 */
	private IService<Integer,? extends Object> service;
	
	/**
	 * 需要处理的数据对象
	 */
	private Object object;

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
	
	public DelSelectionAdapter(IService<Integer,? extends Object> service) {
		super();
		this.service = service;
	}

	
	/**
	 * 弹出删除记录对话框，在确认后，删除数据
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		if (MessageDialog.openQuestion(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), "删除记录", "是否要删除此记录")) {
			try {
				service.remove(object);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				MessageDialog.openError(Display.getDefault().getActiveShell(), 
						"错误", "无法删除该记录，有可能是该记录有关联信息，请联系系统管理员");
			}
		}		
		super.widgetSelected(e);
	}
	
	

}
