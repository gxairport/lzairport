package com.lzairport.ais.dialog;

import java.util.Date;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import com.lzairport.ais.utils.DateTimeUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;

/**
 * 发布自定义调度环节的时间的对话框
 * @author ZhangYu
 * @since JDK1.6 
 * @version 0.9a 0.9a 12/07/14
 *
 */

public class TimeDialog extends Dialog {
	
	private Text txtTime;
	
	private Date date;

	private String prompt;

	public TimeDialog(String prompt,Date date) {
		super(Display.getDefault().getActiveShell());
		this.date = date;
		this.prompt = prompt;
	
	}
	
	

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		Label labPrompt = new Label(container, SWT.HORIZONTAL | SWT.CENTER);
		GridData gd_labPrompt = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_labPrompt.heightHint = 60;
		labPrompt.setLayoutData(gd_labPrompt);
		labPrompt.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		labPrompt.setAlignment(SWT.CENTER);
		labPrompt.setText(prompt);
		
		txtTime = new Text(container, SWT.BORDER | SWT.CENTER);
		txtTime.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		txtTime.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 16, SWT.NORMAL));
		txtTime.setText(DateTimeUtil.DateToStr(date));
		return container;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	@Override
	protected void okPressed() {

		date = DateTimeUtil.strToDate(txtTime.getText());
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {

		date = null;
		super.cancelPressed();
	}
	
	
}
