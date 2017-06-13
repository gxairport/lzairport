package com.lzairport.ais.dialog;

import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.dialog.selectionAdapter.EnameSelectionAdapter;
import com.lzairport.ais.dialog.selectionAdapter.GroupSelectionAdapter;
import com.lzairport.ais.dialog.selectionAdapter.SubEnameSelectionAdapter;
import com.lzairport.ais.models.ViewConfig;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 显示配置对象的编辑对话框类
 * 用于编辑显示配置对象
 * @author ZhangYu
 * @version 0.9a 25/06/14
 * @since JDK 1.6
 */

public class ViewConfigEditDialog extends CommonEditDialog {
	
	/**
	 * 字段的Combo
	 */
	private Combo cmbEname;
	
	/**
	 * 子字段的Combo
	 */
	private Combo cmbSubEname;
	
	/**
	 *  分组字段的Combo
	 */
	private Combo cmbViewGroup ;
	
	
	/**
	 *  显示View类型的Combo
	 */	
	private Combo cmbViewItemType;
	
	/**
	 * 分组选择适配类，用于cmbViewGroup选择后的处理
	 */
	private GroupSelectionAdapter groupSelectionAdapter;
	
	/**
	 *  字段选择适配类，用于cmbEname选择后的处理
	 */
	private EnameSelectionAdapter enameSelectionAdapter;
	
	/**
	 * 子字段选择适配类，用于subCmbEname选择后的处理
	 */
	private SubEnameSelectionAdapter subEnameSelectionAdapter;
	
	private IService<Integer,? extends Object> cmbService;

	public ViewConfigEditDialog(IViewConfigService service,
			Object object,Object viewer,AbstractApplicationContext ctx) {
		super(service, object,viewer.getClass().getSimpleName(),ctx);
		
	}

	
	
	
	
	
	
	@Override
	protected void objToDialog(Object object) {
		// TODO Auto-generated method stub
		super.objToDialog(object);
		// 设置分组适配类最初字段，为了保留之用
		groupSelectionAdapter.setEnameTxt(cmbEname.getText());
		//设置分组适配类最初分组，为了保留之用
		groupSelectionAdapter.setViewGroupText(cmbViewGroup.getText());
	}


	@Override
	protected void dialogToObj(Object object) {
		Object Data = null;
		for (int i=0;i<fields.size();i++){
			String subEname = fields.get(i).getSubEname();
			String Ename = fields.get(i).getEname();
			Data = null;
			//取得字段的实际类型type
			Class<?> type = ObjectMethodUtil.getFieldType(clazz, fields.get(i).getEname());
			if (inputList.get(i) instanceof Text) {
				Data = ((Text)inputList.get(i)).getText();
			}else if (inputList.get(i) instanceof Combo){
				if (Ename.equals(ViewConfig.ENAME) || Ename.equals(ViewConfig.SUBENAME) || Ename.equals(ViewConfig.ITEMTYPE)){
					//如果控件为Combo，字段值为Ename、SubEname、ITEMTYPE，那此Data可以直接取Combo得文本,因为以上字段的类型就是为String
					Data = ((Combo)inputList.get(i)).getText();
				}else {
					//否则要用Service去根据SubEname的子字段的值去查找Data
					 cmbService = SYS_VARS.getFieldService(ctx,object.getClass(),Ename);
					Data =  cmbService.findByFieldSingle(subEname,((Combo)inputList.get(i)).getText());
				}
					
			}
			if ((Data != null)&&(fields.get(i).getEname()!="id")){
				ObjectMethodUtil.setFieldObject(object, Ename,Data,type);
			}
		}
	}







	@Override
	protected Composite createDialogComposite(Composite composite) {
		GridLayout gridLayout = (GridLayout) composite.getLayout();
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginWidth = 20;
		//确定显示的列数 -字段数整除定义每行所允许的控件数 *2因为需要有一个Label和数据编辑控件
		gridLayout.numColumns = fields.size()/SYS_VARS.RowMaxNum*2;
		if (fields.size() % SYS_VARS.RowMaxNum != 0){
			//如果字段数取余不为零 ，说明列数不够需要再加2
			gridLayout.numColumns +=2;
		}
		
		for (int i=0;i<fields.size();i++ ){
				String subEName = fields.get(i).getSubEname();
				String Ename = fields.get(i).getEname();
				labels.add(new Label(composite, SWT.NONE));
				labels.get(i).setText(fields.get(i).getName()+":");
				labels.get(i).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				if (Ename.equals(ViewConfig.ENAME)){
					//如果是Ename字段 控件类型为Combo
					inputList.add(new Combo(composite, SWT.NONE));
					cmbEname = (Combo) inputList.get(i);
				}else if (Ename.equals(ViewConfig.SUBENAME)){
					//如果是SubEname字段 控件类型为Combo
					inputList.add(new Combo(composite, SWT.NONE));
					cmbSubEname =  (Combo) inputList.get(i);
				}else if (Ename.equals(ViewConfig.ITEMTYPE)){
					inputList.add(new Combo(composite, SWT.NONE));
					cmbViewItemType =  (Combo) inputList.get(i);
					
					cmbViewItemType.setItems(SYS_VARS.ViewItemType);
					
				}else if (Ename.equals(ViewConfig.VIEWGROUP)){
					//如果是ViewGroup字段 控件类型为Combo，并将所有的分组名添加到Combo的Item里
						cmbService = SYS_VARS.getFieldService(ctx,object.getClass(),Ename);
						List<? extends Object> items = cmbService.getAll();
						inputList.add(new Combo(composite, SWT.NONE));
						for (Object item:items){
							((Combo)inputList.get(i)).add(ObjectMethodUtil.getFieldObject(item, subEName).toString());
						}
						cmbViewGroup =(Combo)inputList.get(i); 
				}else {
					inputList.add(new Text(composite, SWT.BORDER));
				}
				inputList.get(i).setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				}
		
		subEnameSelectionAdapter = new SubEnameSelectionAdapter();
		enameSelectionAdapter = new EnameSelectionAdapter(cmbSubEname,subEnameSelectionAdapter);
		groupSelectionAdapter = new GroupSelectionAdapter(cmbEname, ctx,enameSelectionAdapter);
		cmbViewGroup.addSelectionListener(groupSelectionAdapter);
		cmbEname.addSelectionListener(enameSelectionAdapter);
		cmbSubEname.addSelectionListener(subEnameSelectionAdapter);
		return composite;
	}

	
}
