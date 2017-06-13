package com.lzairport.ais.tableviewer.base;

import org.eclipse.jface.viewers.Viewer;
/**
 * 用于排序表哥的排序类
 * @author ZhangYu
 * @version 0.9b 12/03/12
 * @since JDK 1.6
 *
 */
import org.eclipse.jface.viewers.ViewerSorter;

import com.lzairport.ais.tableviewer.celldata.CellFactory;
import com.lzairport.ais.tableviewer.celldata.ICell;
import com.lzairport.ais.tableviewer.header.HeaderItem;


/**
 * 通用的排序器<p>
 * 点一下表头为升序，再点一下为降序
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */

public class CommViewSorter extends ViewerSorter {
	
	/**
	 *  对应的列
	 */
	private HeaderItem field;

	/**
	 *  升降序标志
	 */
	private boolean asc;
	
	public CommViewSorter(HeaderItem field,boolean asc) {
		super();
		this.field = field;
		this.asc = asc;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		// TODO Auto-generated method stub
		//取得比较的两个数据所对应列的数据
		int result = 0;
		Object obj1 = null;
		Object obj2 = null;
		
		
		ICell cell = CellFactory.createCell(e1,field);
		if (cell != null){
			obj1 = cell.getData(e1, field);
			obj2 = cell.getData(e2, field);
		}
		
		
		
		//如果想等结果为0，e1小于e2为-1，e1大于e2为1
		if ((obj1 == null) &&(obj2 == null)){
			result = 0;
		}else if (obj1 == null) {
			result = -1;
		}else if (obj2 == null){
			result = 1;
		}else if (obj1 instanceof Comparable){
			result =  ((Comparable) obj1).compareTo(obj2);
		}
		
		//如果不是升序则进行反转
		if (!asc) {
			result = -result;
		}
		
		return result;
	}
	
	

}
