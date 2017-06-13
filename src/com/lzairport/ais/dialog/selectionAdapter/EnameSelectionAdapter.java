package com.lzairport.ais.dialog.selectionAdapter;

import java.lang.reflect.Field;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * ViewConfig字段的选择适配器
 * 用于ViewConfig字段Combo选择后的处理
 * @author ZhangYu
 * @version 0.9a 25/06/24
 * @since JDK 1.6
 */



public class EnameSelectionAdapter extends SelectionAdapter {
	
	/**
	 *  字段的Combo
	 */
	private Combo cmbEname;
	
	/**
	 *  子字段的Combo
	 */
	private Combo cmbSubEname;
	
	
	/**
	 * 数据对象,根据GroupSelection的选择
	 * 
	 */
	private Object obj;
	
	private SubEnameSelectionAdapter adapter;

	public EnameSelectionAdapter(Combo cmbSubEname,SubEnameSelectionAdapter adapter) {
		super();
		this.cmbSubEname = cmbSubEname;
		this.adapter = adapter;
	}
	
	
	

	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}




	/**
	 * @param obj the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}




	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() instanceof Combo) {
			cmbEname = (Combo) e.getSource();
			if (cmbEname.getText() != null){
				//取得字段Combo的数值
				String Ename = cmbEname.getText();
				cmbSubEname.removeAll();
				if (ObjectMethodUtil.isField(obj.getClass(), Ename)){
					Class<?> clazz = ObjectMethodUtil.getFieldType(obj.getClass(), Ename);
					//如果是实体表
					if (clazz.getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
						try {
							//清除SubEname的Items
							//Model的所有的成员变量，并把他们加入子字段的Combo中
							Field[]	fields =ObjectMethodUtil.getModelField(clazz);
							for (Field field:fields){
								if (ObjectMethodUtil.isField(clazz, field.getName())){
									//如果是字段就将它放入SubEname的Combo中
									cmbSubEname.add(field.getName());
								}
							}
							//将新产生的实体对象传给SubEnameSelectionAdapter
							adapter.setClazz(clazz);
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
						}	
					}					
					
				}
				//取得字段的属性
				
				
			}
		}
		super.widgetSelected(e);
	}
	
	

}
