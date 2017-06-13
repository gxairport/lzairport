package com.lzairport.ais.dialog;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.tableviewer.celldata.CellFactory;
import com.lzairport.ais.tableviewer.celldata.EnumCell;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 通用的查找修改对象的窗口的实现类
 * @author ZhangYu
 * @version 0.9a 03/01/15
 * @since JDK 1.6
 */


public class CommonEditDialog extends EditDialog {
	

	private IService<Integer,? extends Object> cmbService;
	
	protected List<Control> inputList  = new ArrayList<Control>();
	protected List<Label> labels =  new ArrayList<Label>();
	protected List<HeaderItem> fields;
	protected Class<?> clazz;
	protected AbstractApplicationContext ctx;
	
	

	/**
	 * 传入修改数据所需要的各种对象
	 * @param service 数据对象的Service层的对象，用来处理数据
	 * @param object 需要处理的数据对象
	 * @param viewerName 用来获取Field列表
	 * @param ctx Spring容器
	 */
	public CommonEditDialog( IService<Integer,? extends Object> service, Object object,
			String  viewerName,AbstractApplicationContext ctx) {
		super(service, object);
		this.ctx = ctx;
		clazz = service.getModelClass();
		IViewConfigService viewConfigService = (IViewConfigService) ctx.getBean("viewConfigService");
		fields = viewConfigService.getVCHeaderItems(viewerName,null);
	}
	
	@Override
	protected void objToDialog(Object object) {
		
		Object Data = null;
		
		for (int i=0;i<fields.size();i++){
			//获取对应的数据对象成员变量的数据Data
			Data = CellFactory.createCell(object, fields.get(i)).getData(object, fields.get(i));
			//根据各自的属性进行赋值
			if (Data != null){
				if (inputList.get(i) instanceof Text) {
					//如果是Text，用Text方式进行赋值
					((Text)inputList.get(i)).setText(Data.toString());
				}else if (inputList.get(i) instanceof Combo){
					((Combo)inputList.get(i)).setText(Data.toString());
				}
			}
		}
		
	}
	
	
	

	@Override
	protected void dialogToObj(Object object) {
		Object Data = null;
		for (int i=0;i<fields.size();i++){
			String subEName = fields.get(i).getSubEname();
			String Ename = fields.get(i).getEname();
			String fieldType = fields.get(i).getItemType();
			Data = null;
			//获取数据对象指定Field字段的实际类型
			Class<?> type = ObjectMethodUtil.getFieldType(clazz, fields.get(i).getEname());
			if (inputList.get(i) instanceof Text) {
				//如果是Text对象赋值给Data
				Data = ((Text)inputList.get(i)).getText();
			}else if (inputList.get(i) instanceof Combo){
				
				 if (fieldType.equals(SYS_VARS.ViewItemType_Enum) || fieldType.equals(SYS_VARS.ViewItemType_Bool)){
					 Data = ((Combo)inputList.get(i)).getText();
				 }else {
					//根据Field字段所对应的Service
					 cmbService = SYS_VARS.getFieldService(ctx,object.getClass(),Ename);
					//根据子字段调用Service取得所对应的数据并赋值给Data
					Data =  cmbService.findByFieldSingle(subEName,((Combo)inputList.get(i)).getText());
				 }
			}
			if ((Data != null)&&(!Ename.equals("id"))){
				//对对象各成员变量进行赋值
				CellFactory.createCell(object, fields.get(i)).setData(object, fields.get(i), Data, type);
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
				String fieldType = fields.get(i).getItemType();
				labels.add(new Label(composite, SWT.NONE));
				labels.get(i).setText(fields.get(i).getName()+":");
				labels.get(i).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				
				if  (fieldType.equals(SYS_VARS.ViewItemType_Bool)){
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					((Combo)inputList.get(i)).setItems(new String[]{"true","false"});
					
				}else if (fieldType.equals(SYS_VARS.ViewItemType_Enum)){
					//如果是枚举类型，将枚举类型的所有显示字符串添加进对应的Combo
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					((Combo)inputList.get(i)).setItems(
						EnumCell.getInstance().enumCnText(object, fields.get(i)));
					
				}else if ((subEName != null) && (!subEName.trim().isEmpty())){
					//如果SubEname不为空，说明有子字段，则控件类型为Combo
					cmbService = SYS_VARS.getFieldService(ctx,object.getClass(),Ename);
					List<? extends Object> items =  cmbService.getAll();
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					//用Service取得的所有子字段所对应的数据对象的集合对Combo的Item进行赋值
					for (Object item:items){
						((Combo)inputList.get(i)).add(ObjectMethodUtil.getFieldObject(item, subEName).toString());
					}
					
				}else {
					//否则控件类型为Text
					Text text = new Text(composite, SWT.BORDER);
					
					inputList.add(text);
				}
				inputList.get(i).setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
					
				}
		
		return composite;
	}

}
