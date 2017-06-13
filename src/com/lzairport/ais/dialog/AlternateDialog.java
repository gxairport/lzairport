package com.lzairport.ais.dialog;


import java.util.List;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.dialog.selectionAdapter.DelayCategorySelectionAdapter;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.DelayCategory;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.service.aodb.IAirportService;
import com.lzairport.ais.service.aodb.IDelayCategoryService;
import com.lzairport.ais.service.aodb.IDelayReasonService;
import com.lzairport.ais.utils.Ais_String_Util;

/**
 * 备降对话框，用于确认备降场、备降原因
 * @author ZhangYu
 * @version 0.9a 29/12/14
 * @since JDK 1.6
 */

public class AlternateDialog extends Dialog {
	
	private String alternateInfo;
	
	private Combo cmbCategory ;
	
	private Combo cmbReason;
	
	private Combo cmbAlternateAirport;
	
	private DelayReason reason;	
	
	private Airport alternateAirport;
	
    private AbstractApplicationContext ctx;
	
	private   IDelayCategoryService delayCategoryService ;
	

	private  IDelayReasonService delayReasonService ;
	

	private  IAirportService airportService ;
	
	public AlternateDialog(){
		
		super(Display.getDefault().getActiveShell());
		
	}
	

	public AlternateDialog(DelayReason reason,String alternateInfo,
			AbstractApplicationContext ctx) {
		super(Display.getCurrent().getActiveShell());
		this.alternateInfo = alternateInfo;
		this.reason = reason;
		this.ctx = ctx;
		//初始化各个Service
		this.delayCategoryService = (IDelayCategoryService) ctx.getBean("delayCategoryService");
		this.delayReasonService = (IDelayReasonService) ctx.getBean("delayReasonService");
		this.airportService = (IAirportService) ctx.getBean("airportService");
	}


	/**
	 * @return the reason
	 */
	public DelayReason getReason() {
		return reason;
	}

	/**
	 * @return the alternateAirport
	 */
	public Airport getAlternateAirport() {
		return alternateAirport;
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
		
		if (alternateInfo != null){
			//如果备降信息没有，则加入全部机场供选择
			cmbAlternateAirport.setItems(Ais_String_Util.SplitField_Spc(alternateInfo)
					.toArray(new String[Ais_String_Util.SplitField_Spc(alternateInfo).size()]));
		}else{
			//如果备降信息存在，则加入在备降信息中的备降场
			List<Airport> airports =  airportService.getAll();
			for(Airport airport:airports){
				cmbAlternateAirport.add(airport.getFourCharCode());
			}
		}
	}	
	
	/**
	 * 重写的方法，创建对话框区域的各元素
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));
		
		Group group = new Group(container, SWT.NONE);
		GridData gd_group = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_group.heightHint = 180;
		group.setLayoutData(gd_group);
		group.setLayout(null);
		group.setText("\u5907\u964D");
		
		cmbCategory = new Combo(group, SWT.NONE);
		cmbCategory.setBounds(73, 38, 219, 27);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("\u5907\u964D\u573A\uFF1A");
		label_1.setBounds(10, 128, 60, 19);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("\u7C7B\u522B\uFF1A");
		label.setBounds(10, 41, 45, 19);
		
		cmbReason = new Combo(group, SWT.NONE);
		cmbReason.setBounds(73, 84, 219, 27);
		cmbCategory.addSelectionListener(new DelayCategorySelectionAdapter(cmbReason,ctx));
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u539F\u56E0\uFF1A");
		label_2.setBounds(10, 87, 45, 19);
		
		cmbAlternateAirport = new Combo(group, SWT.NONE);
		cmbAlternateAirport.setBounds(73, 125, 219, 27);
		
		initCmbItems();
		return container;
	}





	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		//保存延误信息和备降场，供调用
		reason = delayReasonService.findByFieldSingle(DelayReason.CNSHORTNAME, 
				cmbReason.getText());
		alternateAirport = airportService.findByFieldSingle(Airport.FOURCHARCODE, 
				cmbAlternateAirport.getText());
		
		super.okPressed();
	}
	
	
}
