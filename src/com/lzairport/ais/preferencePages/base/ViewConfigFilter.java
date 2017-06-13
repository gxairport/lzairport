package com.lzairport.ais.preferencePages.base;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import com.lzairport.ais.models.ViewConfig;

/**
 * 显示配置项分组的过滤器，用于显示配置项的分组显示
 * @author ZhangYu
 * @version 0.9a 27/06/14
 * @since JDK 1.6
 *
 */

public class ViewConfigFilter extends ViewerFilter {
	
	private String StrGrp;

	public ViewConfigFilter(String strGrp) {
		super();
		StrGrp = strGrp;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// TODO Auto-generated method stub
		if (element instanceof ViewConfig) {
			//如果是显示配置项，根据StrGrp分组名来确定返回的值是真还是假
			ViewConfig vc = (ViewConfig) element;
			return vc.getViewGroup().getName().equals(StrGrp);
		}
		return true;
	}

}
