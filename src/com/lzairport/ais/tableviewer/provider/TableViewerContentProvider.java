package com.lzairport.ais.tableviewer.provider;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;

/**
 * 表格的ContentProvider实现类，联系实体，如果实体发生变化，则自动改变内容
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */

public class TableViewerContentProvider extends ViewerContentProvider   {
	
	
	


	
	private TableViewer viewer; 
	
	public TableViewerContentProvider(TableViewer viewer) {
		super();
		this.viewer = viewer;
	}

	
	
	@Override
	protected void add(final Object entity) {
		
		viewer.getTable().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewer.add(entity);
				
			}
		});
		
	}


	@Override
	protected void remove(final Object entity) {
		viewer.getTable().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewer.remove(entity);
			}
		});
	}


	@Override
	protected void update(final Object entity) {
		viewer.getTable().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewer.update(entity, null);
				
			}
		});
	}


	@Override
	protected void refresh() {
		
		viewer.getTable().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewer.refresh();
			}
		});
	}



	@Override
	protected void showErrMsg(final String msg) {

		viewer.getTable().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openError(Display.getCurrent().getActiveShell(),"错误",msg);
			}
		});
		
	}

}
