package com.lzairport.ais.preferencePages.base;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.springframework.context.support.AbstractApplicationContext;

import com.lzairport.ais.tableviewer.base.BaseTableViewer;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.TableViewerContentProvider;
import com.lzairport.ais.contentchange.IContentChangeProvider;
import com.lzairport.ais.dialog.selectionAdapter.DelSelectionAdapter;
import com.lzairport.ais.dialog.selectionAdapter.EditSelectionAdapter;
import com.lzairport.ais.dialog.selectionAdapter.FindSelectionAdapter;

/**
 * 用于修改各种数据表的首选项的抽象类<p>
 * 实现类实现5个抽象方法来使用此抽象类
 * @author ZhangYu
 * @version 0.9a 26/06/14
 * @since JDK 1.6
 *
 */

public abstract class BaseTablePreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage,ISelectionChangedListener {
	
	

	private  EditSelectionAdapter  addSelectionAdapter;
	

	private  EditSelectionAdapter  changeSelectionAdapter;
	

	private  DelSelectionAdapter   delSelectionAdapter;
	

	private FindSelectionAdapter  findSelectionAdapter; 
	
	
	private  TableViewerContentProvider viewerContentProvider;
	
	/**
	 * 用于更新视图的内容改变提供者，JMS消息
	 */
	@Resource(name="JMSMessage")
	protected IContentChangeProvider provider;
	
	
    
	/**
	 * 基本的TableViewer
	 */
	protected BaseTableViewer viewer;
    
	protected Composite containerBtn;
    
	
	

	public BaseTablePreferencePage() {
	}

	
	@Override
	public void init(IWorkbench arg0) {
	}

	
	/**
	 * 设置TableViewer表头需要显示的HeaderItems传入参数的抽象方法
	 * @return 所需的HeaderItems
	 */
	public abstract List<HeaderItem> getHeaderItems();
	
	

	/**
	 * 设置与TableViewer表头显示相对应的LabelFields的抽象方法
	 * @return 所需的LabelFields
	 */
	public abstract LabelProvider setViewLabelProvider();
	
	/**
	 * 取得TableViewer中所需要的数据的抽象方法;
	 * @return TableViewerService类型的对象
	 */

	public abstract TableViewerService getViewerService();
	
	/**
	 * 获取Spring容器的抽象方法
	 * @return Spring的容器
	 */
	public abstract   AbstractApplicationContext getCtx();
	
	
	/**
	 * 返回首选项的标题的抽象方法
	 * @return 标题
	 */
	public abstract String getPageTitle();
	
	

	
	/**
	 *  创建首选项页面的各元素
	 */
	@Override
	protected Control createContents(Composite parent) {
		//初始化页面
	    Composite containerBase = new Composite(parent, SWT.NONE);
	    containerBase.setLayout(new GridLayout(2, false));
	    Composite containerTabel = new Composite(containerBase, SWT.NONE);
	    containerTabel.setLayout(new GridLayout(1, false));
	    GridData gd_containerTabel = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
	    gd_containerTabel.heightHint = 700;
	    gd_containerTabel.widthHint = 900;
	    containerTabel.setLayoutData(gd_containerTabel);
	    
	    Label LabBase = new Label(containerTabel, SWT.NONE);
	    LabBase.setText(getPageTitle());
	    
	    viewer = new BaseTableViewer(containerTabel, SWT.BORDER | SWT.FULL_SELECTION);
	    viewerContentProvider = new TableViewerContentProvider(viewer);
	    provider.addContentChangeListener(viewerContentProvider);
		viewer.init(getHeaderItems(), viewerContentProvider, setViewLabelProvider(), getViewerService());
	    viewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	    viewer.setViewerConfigName(this.getClass().getSimpleName());
	    viewer.setViewerService(getViewerService());
	    
	    containerBtn = new Composite(containerBase, SWT.NONE);
	    GridData gd_containerBtn = new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1);
	    gd_containerBtn.widthHint = 120;
	    containerBtn.setLayoutData(gd_containerBtn);
	    
	    
	    //创建各功能按钮及处理方式
	    Button btnAdd = new Button(containerBtn, SWT.NONE);
	    btnAdd.setBounds(10, 27, 98, 29);
	    btnAdd.setText("新增...");
	    
	    Button btnDel = new Button(containerBtn, SWT.NONE);
	    btnDel.setBounds(10, 114, 98, 29);
	    btnDel.setText("删除");
	    
	    Button btnFind = new Button(containerBtn, SWT.NONE);
	    btnFind.setBounds(10, 158, 98, 29);
	    btnFind.setText("查找");

	    
	    Button btnChange = new Button(containerBtn, SWT.NONE);
	    btnChange.setBounds(10, 70, 98, 29);
	    btnChange.setText("修改");
	    
	    addSelectionAdapter = new EditSelectionAdapter(getViewerService().getService(),getCtx(),this);
	    changeSelectionAdapter = new EditSelectionAdapter(getViewerService().getService(),getCtx(),this);
	    delSelectionAdapter = new DelSelectionAdapter(getViewerService().getService());
	    findSelectionAdapter = new FindSelectionAdapter(getViewerService().getService(),
	    		this.viewer,getCtx());
	    
	    btnAdd.addSelectionListener(addSelectionAdapter);
	    btnChange.addSelectionListener(changeSelectionAdapter);
	    btnDel.addSelectionListener(delSelectionAdapter);
	    btnFind.addSelectionListener(findSelectionAdapter);
	    viewer.addPostSelectionChangedListener(this);
   		return containerBase;
	}


	
	


	@Override
	public void dispose() {
		provider.removeContentChangeListener(viewerContentProvider);
		super.dispose();
	}


	/**
	 * 选择发生更改 传递需要处理的数据对象给所需的选择适配器的处理Obj
	 */

	@Override
	public void selectionChanged(SelectionChangedEvent evt) {
		 IStructuredSelection selection = (IStructuredSelection) evt.getSelection();
		 changeSelectionAdapter.setObject(selection.getFirstElement());
		 delSelectionAdapter.setObject(selection.getFirstElement());
	}
}
