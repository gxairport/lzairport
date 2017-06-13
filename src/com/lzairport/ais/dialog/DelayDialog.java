package com.lzairport.ais.dialog;

import java.util.List;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.dialog.selectionAdapter.DelayCategorySelectionAdapter;
import com.lzairport.ais.models.aodb.DelayCategory;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.service.aodb.IDelayCategoryService;
import com.lzairport.ais.service.aodb.IDelayReasonService;



/**
 * 发布延误原因的对话框
 * @author ZhangYu
 * @since JDK1.6 
 * @version 0.9a 0.9a 12/07/14
 *
 */

public class DelayDialog extends Dialog {
	
	private String prompt;
	
	private Combo cmbCategory ;
	
	private Combo cmbReason;
	
	private DelayReason reason;
	

	private IDelayReasonService delayReasonService;

	private IDelayCategoryService delayCategoryService;
	
    private AbstractApplicationContext ctx;

	public DelayDialog(DelayReason reason,String prompt,
			AbstractApplicationContext ctx) {
		super(Display.getDefault().getActiveShell());
		this.reason = reason;
		this.prompt = prompt;
		this.ctx = ctx;
		this.delayCategoryService = (IDelayCategoryService) ctx.getBean("delayCategoryService");
		this.delayReasonService = (IDelayReasonService) ctx.getBean("delayReasonService");
		
	}
	
	

	


	/**
	 * @return the reason
	 */
	public DelayReason getReason() {
		return reason;
	}






	/**
	 * @param reason the reason to set
	 */
	public void setReason(DelayReason reason) {
		this.reason = reason;
	}






	/**
	 * 初始化Combo，给各Combo赋值
	 */
	private void initCmbItems(){
		List<DelayCategory> categories =  delayCategoryService.getAll();
		for(DelayCategory category:categories){
			cmbCategory.add(category.getCnShortName());
		}
		if (reason != null){
			//设置延误类别的Combo
			cmbCategory.setText(reason.getDelayCategory().getCnShortName());
			//取得该延误类别的所有的延误原因，加入延误原因的Combo 并填充Flight的延误原因
			List<DelayReason>  reasons = delayReasonService.findByFieldAll
					(DelayReason.DELAYCATEGORY,reason.getDelayCategory());
			
			for(DelayReason reason:reasons){
				cmbReason.add(reason.getCnShortName());
			}
			cmbReason.setText(reason.getCnShortName());
		}
	}
		
		
	/**
	 * 重写的方法，创建对话框区域的各元素
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		Group grpDelay = new Group(container, SWT.NONE);
		grpDelay.setText("\u5EF6\u8BEF");
		grpDelay.setLayout(null);
		grpDelay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label labPrompt = new Label(grpDelay, SWT.NONE);
		labPrompt.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8, SWT.NORMAL));
		labPrompt.setBounds(10, 20, 289, 27);
		labPrompt.setAlignment(SWT.CENTER);
		labPrompt.setText(prompt);
		
		cmbCategory = new Combo(grpDelay, SWT.NONE);
		cmbCategory.setBounds(99, 56, 183, 27);
		cmbReason = new Combo(grpDelay, SWT.NONE);
		cmbReason.setBounds(99, 117, 183, 27);

		cmbCategory.addSelectionListener(new DelayCategorySelectionAdapter(cmbReason,ctx));
		
		
		Label labCategory = new Label(grpDelay, SWT.NONE);
		labCategory.setBounds(36, 59, 45, 19);
		labCategory.setText("\u7C7B\u522B\uFF1A");
		
		Label labReason = new Label(grpDelay, SWT.NONE);
		labReason.setBounds(36, 120, 45, 19);
		labReason.setText("\u539F\u56E0\uFF1A");
		
		initCmbItems();
		return container;
	}





	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		reason = delayReasonService.findByFieldSingle(DelayReason.CNSHORTNAME, 
				cmbReason.getText());
		super.okPressed();
	}
	
	
	
	
}
