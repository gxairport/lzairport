package com.lzairport.ais.models;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import org.eclipse.swt.graphics.RGB;

/**
 * 背景颜色和字体颜色父类，用于表格显示，各实体类继承此类以实现表格颜色
 * @author ZhangYu
 * @version 0.9a 27/06/14
 * @since JDK 1.6
 *
 */

@MappedSuperclass
public abstract class AisRGB  extends DefaultEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected RGB bkRGB;
	protected RGB frRGB;
	protected RGB cellRGB;
	/**
	 * @return the cellRGB
	 */
	public RGB getCellRGB() {
		return cellRGB;
	}
	/**
	 * @param cellRGB the cellRGB to set
	 */
	public void setCellRGB(RGB cellRGB) {
		this.cellRGB = cellRGB;
	}
	/**
	 * @return the bkRGB
	 */
	public RGB getBkRGB() {
		return bkRGB;
	}
	/**
	 * @param bkRGB the bkRGB to set
	 */
	public void setBkRGB(RGB bkRGB) {
		this.bkRGB = bkRGB;
	}
	/**
	 * @return the frRGB
	 */
	public RGB getFrRGB() {
		return frRGB;
	}
	/**
	 * @param frRGB the frRGB to set
	 */
	public void setFrRGB(RGB frRGB) {
		this.frRGB = frRGB;
	}


	
}
