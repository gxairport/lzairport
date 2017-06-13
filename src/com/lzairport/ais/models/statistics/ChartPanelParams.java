package com.lzairport.ais.models.statistics;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * FileName      ChartPanelParams.java
 * @Description  TODO  图标面板的参数
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年8月1日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年8月1日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class ChartPanelParams extends ChartParams implements Serializable{

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	public static String NAME="name";
	public static String PROJECTNAME="projectName";
	public static String WIDTH="width";
	public static String HEIGHT="height";
	public static String ID = "id";
	public static String STARTDATE = "startDate";
	public static String ENDDATE="endDate";
	public static String CHARTTYPE="chartType";
	public static String STATISTICSTYPE="statisticsType";
	public static String GROUP_1 ="group_1";
	public static String GROUP_2 ="group_2";
	public static String GROUP_3="group_3";
	public static String LIMITVALUE="limitValue";
	public static String COMPARE="compare";
	public static String FORECAST="forecast";
	public static String CONDISTIONS ="condistions";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 *  名字 
	 */
	private String name;
	
	
	/** 
	 *  项目名字
	 */
	private String projectName;
	
	
	/**
	 *  宽度
	 */
	private int width;
	
	
	/**
	 *  高度
	 */
	private int height;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	
}
