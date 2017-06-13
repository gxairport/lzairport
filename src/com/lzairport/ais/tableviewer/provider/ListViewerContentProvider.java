package com.lzairport.ais.tableviewer.provider;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Display;

/**
 * ListViewer的ContentProvider实现类，联系实体，如果实体发生变化，则自动改变内容
 * @author ZhangYu
 * version 0.9a 27/06/14
 * @since JDK 1.6
 * 
 */

public class ListViewerContentProvider  extends ViewerContentProvider {
	
	private ListViewer viewer;
	
	

	public ListViewerContentProvider(ListViewer viewer) {
		super();
		this.viewer = viewer;
	}

	@Override
	protected void add(final Object entity) {
		
		viewer.getList().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewer.add(entity);
				
			}
		});
		
	}

	@Override
	protected void remove(final Object entity) {
		viewer.getList().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewer.remove(entity);
			}
		});
	}

	@Override
	protected void update(final Object entity) {
		viewer.getList().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewer.update(entity, null);
				
			}
		});
	}

	@Override
	protected void refresh() {
		
		viewer.getList().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewer.refresh();
			}
		});
	}

	@Override
	protected void showErrMsg(final String msg) {
		// TODO Auto-generated method stub
		viewer.getList().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openError(Display.getCurrent().getActiveShell(),"错误",msg);
			}
		});		
	}
}
