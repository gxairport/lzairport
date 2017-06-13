package com.lzairport.ais.models.aodb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.utils.SYS_VARS.DisPatchItemType;
import com.lzairport.ais.utils.SYS_VARS.OutIn;


/**
 * 航空服务项目的实体类，没有实际记录时间的字段
 * @author ZhangYu
 * @version 0.9a 09/11/14
 * @since JDK 1.6
 *
 */

@Entity
public class FlightDisPatchItem extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static  String  NAME = "name";
	public static  String ISOFFIN = "isOffIn";
	public static  String TYPE= "type";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		
	
	@Column(length=32) 
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(length=4)
	private OutIn isOffIn;
	
	@Enumerated(EnumType.STRING)
	@Column(length=20)
	private DisPatchItemType type;

	
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
	 * @return the isOffIn
	 */
	public OutIn getIsOffIn() {
		return isOffIn;
	}

	/**
	 * @param isOffIn the isOffIn to set
	 */
	public void setIsOffIn(OutIn isOffIn) {
		this.isOffIn = isOffIn;
	}

	/**
	 * @return the type
	 */
	public DisPatchItemType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(DisPatchItemType type) {
		this.type = type;
	}

	
	
	
}
