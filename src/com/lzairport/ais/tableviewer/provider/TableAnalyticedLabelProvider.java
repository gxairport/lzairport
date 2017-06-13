package com.lzairport.ais.tableviewer.provider;

import java.util.List;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.lzairport.ais.utils.Ais_String_Util;

/**
 * 返回内容是字符串的表格的LabelProvider。
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */


public class TableAnalyticedLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if (element instanceof String){
			List<String> strs = Ais_String_Util.SplitField((String)element);    
			return  strs.get(columnIndex);
		}else{
			return  null;
		}
	}

}
