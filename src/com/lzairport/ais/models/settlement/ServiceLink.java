package com.lzairport.ais.models.settlement;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.models.aodb.Carrier;
import com.lzairport.ais.utils.SYS_VARS.OutIn;

/**
 * 
 * FileName      ServiceLink.java
 * @Description  TODO 产生服务的数据
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月7日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class ServiceLink extends DefaultEntity implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来直接点用字段名
	 */
	public static String ID = "id";
	public static String FLIGHTNO ="flightNO";
	public static String ROUTEHX="routeHX";
	public static String EXECDATE="execDate";
	public static String ISOUTIN="isOutIn";
	public static String SETTLEMENTITEM = "settlementItem";
	public static String SETTLEMENTTYPE = "settlementType";
	public static String STARTTIME = "startTime";
	public static String ENDTIME = "endTime";
	public static String TIMES = "times";
	public static String CARRIER ="carrier";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private  String flightNO;
	
	private  String routeHX;
	
	@Enumerated(EnumType.STRING)
	@Column(length=3)
	private OutIn isOutIn;
	
	private Date execDate;
	
    

	@ManyToOne
	private SettlementItem settlementItem;
	
	@ManyToOne
	private  SettlementType settlementType;
	
	@ManyToOne
	private Carrier carrier;
	
	/**
	 *  开始时间
	 */
	private Date startTime;
	
	/**
	 *  结束时间
	 */
	private Date endTime;
	
	/**
	 *  次数
	 */
	private int times;
	
	/**
	 *   Transient标注的字段用来与前端框架进行交互显示，不真正作为保存字段
	 */
	
	@Transient
	public String settlementTypeName;
	
	@Transient
	public String settlementItemName;
	
	public String carrierName;


	@Override
	public Object getId() {
		return this.id;
	}

	@Override
	public void setId(Object id) {
		this.id = (Integer) id;
	}


	/**
	 * @return the settlementItem
	 */
	public SettlementItem getSettlementItem() {
		return settlementItem;
	}

	/**
	 * @param settlementItem the settlementItem to set
	 */
	public void setSettlementItem(SettlementItem settlementItem) {
		this.settlementItem = settlementItem;
	}

	/**
	 * @return the startTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the times
	 */
	public int getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(int times) {
		this.times = times;
	}

	/**
	 * @return the flightNo
	 */
	public String getFlightNO() {
		return flightNO;
	}

	/**
	 * @param flightNo the flightNo to set
	 */
	public void setFlightNO(String flightNo) {
		this.flightNO = flightNo;
	}

	/**
	 * @return the execDate
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
	public Date getExecDate() {
		return execDate;
	}

	/**
	 * @param execDate the execDate to set
	 */
	public void setExecDate(Date execDate) {
		this.execDate = execDate;
	}



	/**
	 * @return the settlementType
	 */
	public SettlementType getSettlementType() {
		return settlementType;
	}

	/**
	 * @param settlementType the settlementType to set
	 */
	public void setSettlementType(SettlementType settlementType) {
		this.settlementType = settlementType;
	}
	
	
	

	/**
	 * @return the isOutIn
	 */
	public OutIn getIsOutIn() {
		return isOutIn;
	}

	/**
	 * @param isOutIn the isOutIn to set
	 */
	public void setIsOutIn(OutIn isOutIn) {
		this.isOutIn = isOutIn;
	}
	
	
	

	/**
	 * @return the routeHX
	 */
	public String getRouteHX() {
		return routeHX;
	}

	/**
	 * @param routeHX the routeHX to set
	 */
	public void setRouteHX(String routeHX) {
		this.routeHX = routeHX;
	}
	
	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	/**
	 *   Transient标注的字段用来与前端框架进行交互显示，不真正作为保存字段
	 */
	/**
	 * @return the settlementTypeName
	 */
	public String getSettlementTypeName() {
		if (this.settlementType != null){
			return this.settlementType.getName();
		}else{
			return null;
		}
	}

	/**
	 * @param settlementTypeName the settlementTypeName to set
	 */
	public void setSettlementTypeName(String settlementTypeName) {
		this.settlementTypeName = settlementTypeName;
	}

	/**
	 * @return the settlementItemName
	 */
	public String getSettlementItemName() {
		if (this.settlementItem != null){
			return this.settlementItem.getName();
		}else{
			return null;
		}
	}

	/**
	 * @param settlementItemName the settlementItemName to set
	 */
	public void setSettlementItemName(String settlementItemName) {
		this.settlementItemName = settlementItemName;
	}

	public String getCarrierName() {
		if (this.carrier != null){
			return this.carrier.getCnShortName();
		}else{
			return null;
		}
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}




	

}
