package com.lzairport.ais.tableviewer.provider;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public class RowNumberLabelProvider extends CellLabelProvider {
	
	private static Color  FixRowColor = new Color(null, new RGB(244,244,244));
	
	private TableViewer viewer;

	@Override
	  protected void initialize(ColumnViewer viewer, ViewerColumn column) {
	    super.initialize(viewer, column);
	    this.viewer = null;
	    if (viewer instanceof TableViewer) {
	      this.viewer = (TableViewer) viewer;
	    }
	  }

	  public void update(ViewerCell cell) {
	    if (viewer != null) {
	      int index = Arrays.asList(viewer.getTable().getItems()).indexOf(cell.getItem());
	      
	      cell.setBackground(FixRowColor);
	      cell.setText("" + (index + 1));
	    }
	    
	  }

}
