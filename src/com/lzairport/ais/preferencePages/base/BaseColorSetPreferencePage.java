package com.lzairport.ais.preferencePages.base;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.lzairport.ais.models.AisRGB;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.tableviewer.header.HeaderItem;
import com.lzairport.ais.tableviewer.provider.ListViewerContentProvider;
import com.lzairport.ais.tableviewer.provider.TableViewerLabelProvider;
import com.lzairport.ais.utils.SYS_VARS;

/**
 * 用于设置各种数据表显示颜色的首选项页的抽象类<p>
 * 实现类实现3个抽象方法setGroupCaption() 设置标题 
 * colorSetFlag() 设置颜色方案
 * getService() 设置输入数据对象的Service
 * @author ZhangYu
 * version 0.9a 26/06/14
 * @since JDK 1.6
 * 
 */


public abstract class BaseColorSetPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, ISelectionChangedListener {
	public BaseColorSetPreferencePage() {
	}
	/**
	 *  背景颜色编辑器
	 */
	private ColorFieldEditor bkCFESet;
	
	/**
	 * 前景颜色编辑器
	 */
	private ColorFieldEditor frCFESet;
	
	/**
	 * 单元格颜色编辑器
	 */
	private ColorFieldEditor cellfrCFESet;
	
	private Group grpBaseSet;
	
	/**
	 *	 基本的ListViewer
	 */
	private ListViewer lvBaseSet;
	
	/**
	 *  颜色对象
	 */
	private AisRGB aisRgb=null;
	
	
	/**
	 * 设置Group的显示名字
	 * @return 显示名字
	 */
	protected abstract String setGroupCaption();
	
	/**
	 * 设置需要在LIST中显示的字段名
	 * @return
	 */
	protected  abstract java.util.List<HeaderItem> setFields();

	/**
	 * 颜色设置方案 详细见SYS_VARS_Util里的设定
	 * @return 颜色设置方案 brfrColorSet cellColorSet
	 */
	protected  abstract int colorSetFlag();
	
	/**
	 * 返回操作的Service，用于给ListViewer的设置input
	 * @return
	 */
	protected abstract TableViewerService getViewerService(); 
	
	
	/**
	 * 设置自身的颜色配置方案
	 * @param colorSetFlag
	 */
	private void CFEenable(int colorSetFlag){
		switch (colorSetFlag) {
		case SYS_VARS.brfrColorSet:
			cellfrCFESet.getColorSelector().setEnabled(false);
			break;

		case  SYS_VARS.cellColorSet:
			frCFESet.getColorSelector().setEnabled(false);
			bkCFESet.getColorSelector().setEnabled(false);
			break;
		}
	}
	 

	

	/**
	 *   根据选中的数据来设置首选项页的 bkCFESet、frCFESet、 cellfrCFESet颜色
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		 IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		 //初始化各颜色编辑器
		 bkCFESet.getColorSelector().setColorValue(new RGB(255,255,255));
		 frCFESet.getColorSelector().setColorValue(new RGB(0,0,0));
		 cellfrCFESet.getColorSelector().setColorValue(new RGB(0,0,0));
		 
		 //根据颜色给各颜色编辑器赋值
		 if (selection.getFirstElement() instanceof AisRGB){
			 aisRgb = (AisRGB) selection.getFirstElement();
			 if (aisRgb.getBkRGB() != null){
				 bkCFESet.getColorSelector().setColorValue(aisRgb.getBkRGB());
			 }
			 if (aisRgb.getFrRGB() != null){
				 frCFESet.getColorSelector().setColorValue(aisRgb.getFrRGB());
			 }
			 if (aisRgb.getCellRGB() != null){
				 cellfrCFESet.getColorSelector().setColorValue(aisRgb.getCellRGB());
			 }
		 }
	}
	

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	
	/**
	 *  创建首选项页面的各元素
	 */
	@Override
	protected Control createContents(Composite parent) {
		//初始化页面
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		grpBaseSet = new Group(container, SWT.NONE);
		grpBaseSet.setLayout(new GridLayout(1, false));
		grpBaseSet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpBaseSet.setText(setGroupCaption());
		lvBaseSet = new ListViewer(grpBaseSet, SWT.BORDER | SWT.V_SCROLL);
		List lsBaseSet =  lvBaseSet.getList();
		GridData gd_lsBaseSet = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_lsBaseSet.heightHint = 450;
		lsBaseSet.setLayoutData(gd_lsBaseSet);
		Composite colorCompostie = new Composite(grpBaseSet, SWT.NONE);
		colorCompostie.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_compostiecon = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_compostiecon.heightHint = 91;
		gd_compostiecon.widthHint = 314;
		colorCompostie.setLayoutData(gd_compostiecon);
		//创建各颜色编辑器
		bkCFESet = new ColorFieldEditor("","背景颜色：   ", colorCompostie);
		frCFESet = new ColorFieldEditor("","字体颜色：   ", colorCompostie);
		cellfrCFESet = new ColorFieldEditor("","单元格字体颜色：", colorCompostie);
		//创建ListViewer 并设置其内容提供，标签提供和数据对象及处理方式
    	lvBaseSet.setContentProvider(new ListViewerContentProvider(lvBaseSet));
		lvBaseSet.setLabelProvider(new TableViewerLabelProvider(setFields()));
		lvBaseSet.setInput(getViewerService());
		lvBaseSet.addSelectionChangedListener(this);
		//根据实现类选择选择颜色设置方案
		CFEenable(colorSetFlag());
		return container;
	}


	/**
	 * 存储设定的RGB的对象
	 */
	@Override
	protected void performApply() {
		// TODO Auto-generated method stub
		aisRgb.setBkRGB(bkCFESet.getColorSelector().getColorValue());
		aisRgb.setFrRGB(frCFESet.getColorSelector().getColorValue());
		aisRgb.setCellRGB(cellfrCFESet.getColorSelector().getColorValue());
		getViewerService().getService().update(aisRgb);
		super.performApply();
		
	}
}


