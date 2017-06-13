package com.lzairport.ais.models.aodb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import com.lzairport.ais.models.DefaultEntity;


/**
 * 航班共享抽象类,动态，计划，历史都继承它
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */
@MappedSuperclass
public abstract class ShareFlight  extends DefaultEntity implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */			
	public static String ID = "id";
	public static String ORDERCODE = "orderCode";
	public static String SH_FLIGHTNO = "sh_FlightNO";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;			
	
	@Column(length=8) 
	private String sh_FlightNO;
	
	private int orderCode;
	
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
	 * @return the sh_FlightNO
	 */
	public String getSh_FlightNO() {
		return sh_FlightNO;
	}

	/**
	 * @param sh_FlightNO the sh_FlightNO to set
	 */
	public void setSh_FlightNO(String sh_FlightNO) {
		this.sh_FlightNO = sh_FlightNO;
	}

	/**
	 * @return the orderCode
	 */
	public int getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	
	
}
