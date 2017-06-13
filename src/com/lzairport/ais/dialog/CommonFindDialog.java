package com.lzairport.ais.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.service.IViewConfigService;
import com.lzairport.ais.tableviewer.base.BaseTableViewer;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.celldata.CellFactory;
import com.lzairport.ais.tableviewer.celldata.EnumCell;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.utils.Ais_String_Util;
import com.lzairport.ais.utils.ObjectMethodUtil;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 通用的查找实体对象的窗口的实现类
 * @author ZhangYu
 * @version 0.9a 07/01/15
 * @since JDK 1.6
 */


public class CommonFindDialog extends Dialog {


	private IService<Integer,? extends Object> cmbService;
	private IService<Integer,? extends Object> service;
	private List<Control> inputList  = new ArrayList<Control>();
	private List<Combo> cmbOpers  = new ArrayList<Combo>();
	private List<Button> btns =  new ArrayList<Button>();
	private List<HeaderItem> fields;
	private Class<?> clazz;
	private AbstractApplicationContext ctx;
	private BaseTableViewer viewer;
	private Object object;

	
	public CommonFindDialog(IService<Integer,? extends Object> service,
			BaseTableViewer viewer,AbstractApplicationContext ctx) {
		super(Display.getDefault().getActiveShell());
		this.ctx = ctx;
		this.service = service;
		clazz = service.getModelClass();
		this.viewer = viewer;
		IViewConfigService viewConfigService = (IViewConfigService) ctx.getBean("viewConfigService");
		fields = viewConfigService.getVCHeaderItems(viewer.getViewerConfigName(),null);
		//创建一个数据对象
		String className = service.getModelClass().getName();
		try {
			object = Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fields = createFindFields(fields);
	}

	/**
	 * 
	 * @param fields
	 * @return
	 */
	private List<HeaderItem> createFindFields(List<HeaderItem> fields){
		
		List<HeaderItem> findFields = new ArrayList<HeaderItem>();
		
		for(HeaderItem field:fields){
			if ((!field.getItemType().equals(SYS_VARS.ViewItemType_Dyniamic)&&
				!field.getItemType().equals(SYS_VARS.ViewItemType_Num))&&
				 ((field.getSubEname() == null)||
					((field.getSubEname()!= null)&&(field.getSubEname().indexOf("/")==-1)))){
					//根据设置的查询次数，设置field字段
					for (int i=0;i<field.getFindNum();i++){
						findFields.add(field);
					
				}
				
				/**
					Class<?> filedClass = ObjectMethodUtil.getFieldType(object.getClass(), field.getEname());
					if (filedClass.equals(Date.class)){
						findFields.add(field);
					}
				 * 
				 */
			}
		}
		
		return findFields;
		
	}
	
	
	/**
	 * 根据选择设置对象的属性
	 * @return  赋值好的实体类
	 */
	private Object dialogToObj (){
		
		for (int i=0;i<fields.size();i++){
			String subEName = fields.get(i).getSubEname();
			String Ename = fields.get(i).getEname();
			String fieldType = fields.get(i).getItemType();
			Object Data = null;
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
			if ((Data != null)&&(!Ename.equals("id")&&btns.get(i).getSelection())){
				//对对象各成员变量进行赋值
				CellFactory.createCell(object, fields.get(i)).setData(object, fields.get(i), Data, type);
			}
		}
		return object;
	}
	
	
	/**
	 * 根据显示的属性，生成需要选择的各控件
	 * @param composite
	 * @return
	 */

	private Composite createDialogComposite(Composite composite){
		
		GridLayout gridLayout = (GridLayout) composite.getLayout();
		gridLayout.horizontalSpacing = 10;
		gridLayout.marginWidth = 20;
		//确定显示的列数 -字段数整除定义每行所允许的控件数 *3因为需要有一个Label和数据编辑控件、选择按钮
		gridLayout.numColumns = fields.size()/SYS_VARS.RowMaxNum*3;
		
		if (fields.size() % SYS_VARS.RowMaxNum != 0){
			//如果字段数取余不为零 ，说明列数不够需要再加2
			gridLayout.numColumns +=3;
		}
		for (int i=0;i<fields.size();i++ ){
				String subEname = fields.get(i).getSubEname();
				String Ename = fields.get(i).getEname();
				String fieldType = fields.get(i).getItemType();
				
				btns.add(new Button(composite, SWT.CHECK));
				btns.get(i).setText(fields.get(i).getName());
				btns.get(i).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				
				cmbOpers.add(new Combo(composite, SWT.READ_ONLY | SWT.CENTER));
				cmbOpers.get(i).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
				cmbOpers.get(i).setItems(SYS_VARS.OperationsCN.toArray(
						new String[SYS_VARS.OperationsCN.size()]));
				cmbOpers.get(i).setText("等于");
				if  (fieldType.equals(SYS_VARS.ViewItemType_Bool)){
					//如果是布尔型，将true,false添加进对应的Combo
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					((Combo)inputList.get(i)).setItems(new String[]{"true","false"});
					
				}else if (fieldType.equals(SYS_VARS.ViewItemType_Enum)){
					//如果是枚举类型，将枚举类型的所有显示字符串添加进对应的Combo
					inputList.add(new Combo(composite, SWT.READ_ONLY));
					((Combo)inputList.get(i)).setItems(
						EnumCell.getInstance().enumCnText(object, fields.get(i)));
					
				}else if ((subEname != null) && (!subEname.trim().isEmpty())){ //如果SubEname不为空，说明有子字段，则控件类型为Combo
					
					Class<?> clazz = null;
					//如果subEnames大于2，说明是嵌套字段，取最后一个为subEname,倒数第二个为Ename
					List<String> subEnames = Ais_String_Util.SplitSubEname(subEname); 
					if (subEnames.size() >= 2){
						clazz = ObjectMethodUtil.getFieldType(object.getClass(), Ename);
						Ename = subEnames.get(subEnames.size()-2);
						subEname = subEnames.get(subEnames.size()-1);
						
					}else{
						clazz = object.getClass();
					}
					
					//用Service取得的所有子字段所对应的数据对象的集合对Combo的Item进行赋值
					cmbService = SYS_VARS.getFieldService(ctx,clazz,Ename);
					List<? extends Object> items =  cmbService.getAll();
					inputList.add(new Combo(composite, SWT.NONE));
					for (Object item:items){
						((Combo)inputList.get(i)).add(ObjectMethodUtil.getFieldObject(item, subEname).toString());
					}
					
				} else {
					//否则控件类型为Text
					inputList.add(new Text(composite, SWT.BORDER));
					
				}
				inputList.get(i).setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				
				
				
					
		}
		return composite;
	}
	
	
	
	
	/**
	 * 重写的方法，创建对话框区域的各元素
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		createDialogComposite(container);
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
	 *   按照选择生成查询条件
	 */
	@Override
	protected void okPressed() {
		
		Object obj = dialogToObj();
		
		List<Object> expresstion = new ArrayList<Object>();
		int chkCount =0;
		
		
		TableViewerService viewerService = viewer.getViewerService();
		QueryConditions conditions = new QueryConditions();
		
		for (int i=0;i<fields.size();i++ ){
			if (btns.get(i).getSelection()){
				chkCount++;
				if (chkCount != 1){
					//如果不是第一个条件，需要加上And逻辑运算符
					expresstion.add(SYS_VARS.LinkSqlAnd);
				}
				expresstion.add(fields.get(i).getEname());
				int operIndex = SYS_VARS.OperationsCN.indexOf(cmbOpers.get(i).getText());
				expresstion.add(SYS_VARS.Operations.get(operIndex));
				if (cmbOpers.get(i).getText().trim().equals("包含")){
					expresstion.add("%"+ObjectMethodUtil.getFieldObject(obj, fields.get(i).getEname())+"%");
				}else{
					expresstion.add(ObjectMethodUtil.getFieldObject(obj, fields.get(i).getEname()));
				}
			}
		}

		conditions.setExpresstion(expresstion.toArray());
		if (viewerService.getConditions() != null){
			conditions.setFetchManyToOne(viewerService.getConditions().getFetchManyToOne());
			conditions.setFetchOneToMany(viewerService.getConditions().getFetchOneToMany());
		}
		viewerService.setConditions(conditions);
		viewerService.setService(service);
		viewer.setInput(viewerService);
		super.okPressed();
	}
	
	


}
