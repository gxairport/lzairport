package com.lzairport.ais.dialog;

import java.lang.reflect.Field;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import com.lzairport.ais.dialog.selectionAdapter.SubEnameSelectionAdapter;
import com.lzairport.ais.utils.ObjectMethodUtil;



/**
 * SubEname显示子字段名的对话框
 * 用于选择对应子字段是实体的显示字段
 * @author ZhangYu
 * @version 0.9a 06/08/14
 * @since JDK 1.6
 */

public class SubEnameDialog extends Dialog {

	/**
	 * 子字段选择适配器
	 * 用于交互数据
	 */
	private SubEnameSelectionAdapter adapter;
	
	/**
	 * 需查找显示字段的实体对象
	 */
	private Class<?> clazz;
	
	
	/**
	 * 显示实体对象的所有字段的Combo
	 */
	private Combo cmbLkSubEname;
	
	public SubEnameDialog(SubEnameSelectionAdapter adapter) {
		super(Display.getDefault().getActiveShell());
		// TODO Auto-generated constructor stub
		this.adapter = adapter;
	}

	

	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}


	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}







	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label labLkSubEname = new Label(container, SWT.NONE);
		labLkSubEname.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		labLkSubEname.setText("\u663E\u793A\u7684\u5B50\u5B57\u6BB5\uFF1A");
		
		cmbLkSubEname = new Combo(container, SWT.NONE);
		cmbLkSubEname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Field[]	fields = clazz.getDeclaredFields();
		if (clazz != null){
			for (Field field:fields){
				if (ObjectMethodUtil.isField(clazz, field.getName())){
					//如果是字段就将它放入SubEname的Combo中
					cmbLkSubEname.add(field.getName());
				}
			}
		}
		
		
		return container;
	}



	@Override
	protected void okPressed() {

		adapter.setEname(cmbLkSubEname.getText());
		super.okPressed();
	}
	
	
	
}
