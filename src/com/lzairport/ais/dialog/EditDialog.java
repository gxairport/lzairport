package com.lzairport.ais.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import com.lzairport.ais.service.IService;

/**
 * 通用的修改实体对象的窗口
 * 用于弹出一个修改数据对话框窗口来修改实体对象的各项数据
 * @author ZhangYu
 * @version 0.9a 25/06/14
 * @since JDK 1.6
 */


public abstract class EditDialog extends Dialog {
	
	/**
	 *  数据对象进行操作的Service对象
	 */


	protected  IService<Integer, ? extends Object> service;
	
	/**
	 *  修改的数据对象
	 */
	protected Object object;

	private boolean flagAdd = false;
	



	public EditDialog(IService<Integer, ? extends Object> service,Object object) {
		super(Display.getDefault().getActiveShell());
		this.service =  service;
		if (object == null && service != null){
			flagAdd = true;
			try {
				
				//取得Service所处理的数据类型
				String className = service.getModelClass().getName();
				//创建一个数据对象
				object =  Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}			
		}
		
		this.object = object;
	}
	
	
	/**
	 * 将需要处理的对象对修改对话框进行赋值
	 */
 
	protected abstract void objToDialog(Object object);
	
	
	/**
	 *  将修改对话框里各数据对需处理的数据object各成员变量进行赋值
	 * @throws Exception 
	 */
	protected abstract void dialogToObj(Object object) throws Exception;
	
	/**
	 * 根据所传入的条件创建Dialog对话框中的各元素
	 * @param composite 父类的控件
	 * @return composite  创建完毕有各元素的对话框
	 */
	protected abstract Composite createDialogComposite(Composite composite);
	
	
	/**
	 * 重写的方法，创建对话框区域的各元素
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		createDialogComposite(container);
		objToDialog(object);
		return container;
	}
	
	
	/**
	 * 创建确定 取消按钮
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "确定",
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", false);
	}

	
	/**
	 * 按下确定后的操作
	 */



	@Override
	protected void okPressed() {
		try {
			if (!flagAdd) {
				//数据更新操作
				dialogToObj(object);
				service.update(object);
			}else{
				dialogToObj(object);
				service.add(object);
			}
		} catch (Exception e) {

			MessageDialog.openError(Display.getDefault().getActiveShell(),
					"错误", "数据保存或更新错误，请联系系统管理员");
			e.printStackTrace();
		}
		super.okPressed();
	}
	
	

}
