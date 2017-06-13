package com.lzairport.ais.models.aodb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.lzairport.ais.models.DefaultEntity;




/**
 * 机型的实体类
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class CraftType extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */		
	public static  String ID = "id";
	public static  String ICAOCODE = "icaoCode";
	public static  String SIZETYPE = "sizeType";
	public static  String CODE = "code";
	public static  String NEARBRIDGEFLAG = "nearBridgeFlag";	
	public static  String SEATNUM = "seatNum";
	public static  String CLEARANCESECOND ="clearanceSecond";
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	
	@Id
	@Column(length=16)
	private String code;
	
	@Column(length=8)
	private String icaoCode;
	
	@Column(length=8)
	private String sizeType;
	
	private int seatNum;
	
	
	private boolean nearBridgeFlag;
	
	/** 
	 *  放行正常的毫秒数
	 */
	private long clearanceSecond;
	
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the icaoCode
	 */
	public String getIcaoCode() {
		return icaoCode;
	}

	/**
	 * @param icaoCode the icaoCode to set
	 */
	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}

	/**
	 * @return the sizeType
	 */
	public String getSizeType() {
		return sizeType;
	}

	/**
	 * @param sizeType the sizeType to set
	 */
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	/**
	 * @return the nearBridgeFlag
	 */
	public boolean isNearBridgeFlag() {
		return nearBridgeFlag;
	}

	/**
	 * @param nearBridgeFlag the nearBridgeFlag to set
	 */
	public void setNearBridgeFlag(boolean nearBridgeFlag) {
		this.nearBridgeFlag = nearBridgeFlag;
	}

	/**
	 * @return the seatNum
	 */
	public int getSeatNum() {
		return seatNum;
	}

	/**
	 * @param seatNum the seatNum to set
	 */
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	/**
	 * @return the clearanceSecond
	 */
	public long getClearanceSecond() {
		return clearanceSecond;
	}

	/**
	 * @param clearanceSecond the clearanceSecond to set
	 */
	public void setClearanceSecond(long clearanceSecond) {
		this.clearanceSecond = clearanceSecond;
	}


	@Override
	public Object getIdValue() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.code;
	}

	
	
}
