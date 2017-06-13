package com.lzairport.ais.dialog.selectionAdapter;

import java.lang.reflect.Field;
import java.util.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.models.ViewGroup;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.service.IViewGroupService;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 分组的选择适配器类
 * 用于ViewConfig分组Combo选择后的处理
 * @author ZhangYu
 * @version 0.9a 25/06/24
 * @since JDK 1.6
 */


public class GroupSelectionAdapter extends SelectionAdapter {
	
	/**
	 *  字段的Combo
	 */
	private Combo cmbEname;
	
	/**
	 * 分组的Combo
	 */
	private Combo cmbViewGroup;
	
	/**
	 *  显示配置的Service
	 */
	
	private IViewConfigService viewConfigService;
	
	/**
	 *  显示分组的Service
	 */
	
	private IViewGroupService viewGroupService;
	
	/**
	 *  最初的分组Combo中选择的数据
	 */
	private String ViewGroupText;
	
	/**
	 *  最初的字段Combo中选择的数据
	 */
	private String EnameTxt;
	
	
	
	private EnameSelectionAdapter adapter ;

	public GroupSelectionAdapter(Combo cmbEname, AbstractApplicationContext ctx,EnameSelectionAdapter adapter) {
		super();
		this.cmbEname = cmbEname;
		this.adapter = adapter;
		this.viewConfigService = (IViewConfigService) ctx.getBean("viewConfigService");
		this.viewGroupService = (IViewGroupService) ctx.getBean("viewGroupService");
		
	}

	/**
	 * @return the viewGroupText
	 */
	public String getViewGroupText() {
		return ViewGroupText;
	}

	/**
	 * @param viewGroupText the viewGroupText to set
	 */
	public void setViewGroupText(String viewGroupText) {
		ViewGroupText = viewGroupText;
	}

	/**
	 * @return the enameTxt
	 */
	public String getEnameTxt() {
		return EnameTxt;
	}

	/**
	 * @param enameTxt the enameTxt to set
	 */
	public void setEnameTxt(String enameTxt) {
		EnameTxt = enameTxt;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() instanceof Combo) {
			cmbViewGroup = (Combo) e.getSource();
			if (cmbViewGroup.getText()!=null){
				//取得当前选择的分组
				ViewGroup group = viewGroupService.findByFieldSingle(ViewGroup.NAME, cmbViewGroup.getText());
				//取得当前选择的分组所对应的实体类名
				String ModelClassName = group.getModelClassName();
				try {
					cmbEname.removeAll();
					//取得实体类名所对应的所有成员变量 
					Object obj =  Class.forName(ModelClassName).newInstance();
					Field[]	fields =ObjectMethodUtil.getModelField(obj.getClass());
					//取得此分组所对应的所有显示配置项
				    List<ViewConfig> vcs = viewConfigService.findByFieldAll(ViewConfig.VIEWGROUP, group);
				    for (Field field:fields){
				    	boolean contains = false;
				    	for (ViewConfig vc:vcs){
				    		if (field.getName().equals(vc.getEname())){
				    			contains = true;
				    		}
				    	}
				    	
				    	if ((!contains)&&ObjectMethodUtil.isField(obj.getClass(), field.getName())) {
				    		//如果是字段并且在对应的显示配置项中没有，则加入字段的Combo中
				    		cmbEname.add(field.getName());
				    	}else if (cmbViewGroup.getText().equals(ViewGroupText)&&(field.getName().equals(EnameTxt))){
				    		//如果是最初的设定，则也加入字段的Combo中
				    		cmbEname.add(field.getName());
				    	}else{
				    		//如果是关联表，也加入字段Combo中
				    		if (ObjectMethodUtil.isField(obj.getClass(), field.getName())){
				    			Class<?> clazz = ObjectMethodUtil.getFieldType(obj.getClass(), field.getName());
								if (clazz.getName().indexOf(SYS_VARS.ModelClassHeader) != -1){
						    		cmbEname.add(field.getName());
								}
				    		}
				    	}
				    }
				//将新产生的实体对象传给EnameSelectionAdapter
				adapter.setObj(obj);
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IllegalAccessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}

		}		
		super.widgetSelected(e);
	}



	
	

}
