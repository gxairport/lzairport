package com.lzairport.ais.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 显示表格的实体类
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */


@Entity
public class ViewConfig  extends DefaultEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID="id";
	
	public static String CNAME="Cname";
	
	public static String ENAME="Ename";
	
	public static String SUBENAME="subEname";
	
	public static String COLWIDTH="colWidth";
	
	public static String VIEWGROUP="viewGroup";
	
	public static String SORT="sort";
	
	public static String ITEMTYPE="itemType";
	
	public static String FINDNUM ="findNum";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		
	
	private String Cname;
	
	private String Ename;
	
	private String subEname;
	
	private int colWidth;
	
	@ManyToOne
	private ViewGroup viewGroup;
	
	private int sort;
	
	
	@Column(length=10)
	private String itemType;
	
	
	/**
	 * 查询次数，一般用在Date字段
	 */
	private int findNum;
	
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		this.id = (Integer) id;
	}
	
	/**
	 * @return the cname
	 */
	public String getCname() {
		return Cname;
	}
	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		Cname = cname;
	}
	/**
	 * @return the ename
	 */
	public String getEname() {
		return Ename;
	}
	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		Ename = ename;
	}
	/**
	 * @return the colWidth
	 */
	public int getColWidth() {
		return colWidth;
	}
	/**
	 * @param colWidth the colWidth to set
	 */
	public void setColWidth(int colWidth) {
		this.colWidth = colWidth;
	}

	/**
	 * @return the subEname
	 */
	public String getSubEname() {
		return subEname;
	}
	/**
	 * @param subEname the subEname to set
	 */
	public void setSubEname(String subEname) {
		this.subEname = subEname;
	}
	public ViewGroup getViewGroup() {
		return viewGroup;
	}
	public void setViewGroup(ViewGroup viewGroup) {
		this.viewGroup = viewGroup;
	}
	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the findNum
	 */
	public int getFindNum() {
		return findNum;
	}

	/**
	 * @param findNum the findNum to set
	 */
	public void setFindNum(int findNum) {
		this.findNum = findNum;
	}

	
	
	

}
