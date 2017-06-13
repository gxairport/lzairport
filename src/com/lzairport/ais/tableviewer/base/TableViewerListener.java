package com.lzairport.ais.tableviewer.base;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class TableViewerListener implements Listener {

	private TableViewer viewer;
	
	private static Color SelectRowColor = new Color(null,new RGB(0,128,192));
	
	
	
	public TableViewerListener(TableViewer viewer) {
		super();
		this.viewer = viewer;
	}


	@Override
	public void handleEvent(Event event) {
		GC gc = event.gc;
		Rectangle rect=event.getBounds();
		Object obj = event.item.getData();
		if (viewer.getTable().getSelectionIndex()!= -1){
			Object selectItem =viewer.getTable().getItem(viewer.getTable().getSelectionIndex()).getData();
			if (selectItem.equals(obj)){
				gc.setBackground(SelectRowColor);
			}
		}
		gc.fillRectangle(rect);
		
	}

}
