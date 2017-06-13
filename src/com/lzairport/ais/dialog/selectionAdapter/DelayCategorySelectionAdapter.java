package com.lzairport.ais.dialog.selectionAdapter;

import java.util.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.springframework.context.support.AbstractApplicationContext;
import com.lzairport.ais.models.aodb.DelayCategory;
import com.lzairport.ais.models.aodb.DelayReason;
import com.lzairport.ais.service.aodb.IDelayCategoryService;
import com.lzairport.ais.service.aodb.IDelayReasonService;


/**
 * 延误类别选择的适配器类
 * @author ZhangYu
 * @version 0.9a 25/06/24
 * @since JDK 1.6
 */

public class DelayCategorySelectionAdapter extends SelectionAdapter {

	private Combo cmbReason;
	
	private IDelayCategoryService delayCategoryService;
	
	private IDelayReasonService delayReasonService;

	public DelayCategorySelectionAdapter(Combo cmbReason,AbstractApplicationContext ctx) {
		super();
		this.cmbReason = cmbReason;
		
		delayCategoryService = (IDelayCategoryService) ctx.getBean("delayCategoryService");
		delayReasonService = (IDelayReasonService) ctx.getBean("delayReasonService");
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		
		DelayCategory category =delayCategoryService.
				findByFieldSingle(DelayCategory.CNSHORTNAME,((Combo) e.getSource()).getText());
		
		List<DelayReason> reasons  = delayReasonService.findByFieldAll(DelayReason.DELAYCATEGORY,category);
		
		cmbReason.removeAll();
		
		for(DelayReason reason:reasons){
			cmbReason.add(reason.getCnShortName());
		}
		
		super.widgetSelected(e);
	}
	
	
	
}
