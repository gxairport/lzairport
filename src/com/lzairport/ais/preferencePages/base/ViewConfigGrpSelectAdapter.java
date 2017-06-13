package com.lzairport.ais.preferencePages.base;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

/**
 * 显示配置项分组的选择设置器，用于触发使用过滤器
 * @author ZhangYu
 * @version 0.9a 27/06/14
 * @since JDK 1.6
 *
 */

public class ViewConfigGrpSelectAdapter extends SelectionAdapter {
	
	private TableViewer viewer;

	public ViewConfigGrpSelectAdapter(TableViewer viewer) {
		super();
		this.viewer = viewer;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof Combo){
			String grpStr = ((Combo)e.getSource()).getText();
			if (grpStr.equals("全部")){
				//如果选择的是全部，取消过滤器
				viewer.resetFilters();
			}else{
				//否则，根据Combo中的选择结果进行过滤
				viewer.resetFilters();
				viewer.addFilter(new ViewConfigFilter(grpStr));
			}
		}
		super.widgetSelected(e);
	}
	
	

}
