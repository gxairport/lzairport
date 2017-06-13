package com.lzairport.ais.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 * 显示关联的实体类
 * 只有注册在显示关联类中的类才能使用ViewConfig
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */
@Entity 
public class ViewRelation extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID="id";
	
	public static String RELATIONVIEWCLASS="relationViewClass";
	
	public static String VIEWGROUP="viewGroup";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	
	private String relationViewClass;
	
	@ManyToOne
	private ViewGroup viewGroup;

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

	public String getRelationViewClass() {
		return relationViewClass;
	}

	public void setRelationViewClass(String relationViewClass) {
		this.relationViewClass = relationViewClass;
	}

	public ViewGroup getViewGroup() {
		return viewGroup;
	}

	public void setViewGroup(ViewGroup viewGroup) {
		this.viewGroup = viewGroup;
	}

	
}
