package com.lzairport.ais.dialog.selectionAdapter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.ui.PlatformUI;

import com.lzairport.ais.dialog.SubEnameDialog;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ViewConfig子字段的选择适配器
 * 用于ViewConfig子字段Combo选择后的处理
 * @author ZhangYu
 * @version 0.9a 06/08/14
 * @since JDK 1.6
 */

public class SubEnameSelectionAdapter extends SelectionAdapter {
	
	/**
	 * 子字段的Combo
	 */
	private Combo cmbSubEname;
	
	/**
	 * 字段选择所代表的数据对象obj,由EnameSelectionAdapter传入
	 */

	private Class<?> clazz;
	
	
	/**
	 * 子字段的对象，用于查找子字段的显示字段
	 */

	private Class<?> subClazz;

	/**
	 * 子字段的显示名字
	 */
	private String Ename;
	
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

	/**
	 * @return the ename
	 */
	public String getEname() {
		return Ename;
	}

	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		Ename = ename;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		cmbSubEname = (Combo) e.getSource();
		Ename = cmbSubEname.getText();
		subClazz = clazz;
		//查找的是实体类就一直查找所对应的显示字段
		while (ObjectMethodUtil.isField(subClazz, Ename)){
			Class<?> clazz = ObjectMethodUtil.getFieldType(subClazz, Ename);
			//如果是实体表则继续找显示的子字段
			if (clazz.getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
				subClazz = clazz;
				if (MessageDialog.openQuestion(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), "询问", "此子字为"+clazz.getSimpleName()
						+"实体是否继续指定显示字段")) {
					
					SubEnameDialog dialog = new SubEnameDialog(this);
					dialog.setClazz(subClazz);
					if (dialog.open() == 0) {
						//如果选择确定则加上显示字段名
						cmbSubEname.setText(cmbSubEname.getText()+"/"+Ename);
					}
				}else{
					//如果选择不指定，则退出，就用对象的toString方法来显示数据
					break;
				}
			}else{
				//如果不是实体类，则跳出，就用该属性来显示数据
				break;
			}
			
		}
		
		super.widgetSelected(e);
	}
	
	
}
