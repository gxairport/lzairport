package com.lzairport.ais.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * FileName      IdEntity.java
 * @Description  TODO  id为int类型实体类父类 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月19日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月19日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@MappedSuperclass
public abstract class IntIdEntity extends DefaultEntity  implements Serializable {
	
	protected static final long serialVersionUID = 1L;
	
	public static String ID           = "id";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;

	@Override
	public Object getId() {
		return this.id;
	}

	@Override
	public void setId(Object id) {
		this.id = (int) id;
	}

}
