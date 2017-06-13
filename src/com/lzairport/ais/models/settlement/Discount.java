package com.lzairport.ais.models.settlement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.Carrier;
import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.utils.SYS_VARS.DiscountStyle;



/**
 * 
 * FileName      Discount.java
 * @Description  TODO 结算折扣实体类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月25日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月25日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class Discount extends DefaultEntity implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	public static String ID = "id";
	public static String ROUTEHX ="routeHX"; 
	public static String FLIGHTNO = "flightNO";
	public static String AIRLINES = "airlines";
	public static String CARRIER ="carrier";
	public static String SETTLEMENTITEM = "settlementItem";
	public static String TASK ="task";
	public static String STYLE = "style";
	public static String PERCENTAGE = "percentage";
	public static String PRICE = "price";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String routeHX;
	
	private String flightNO;
	
	@ManyToOne
	private Airlines airlines;
	
	@ManyToOne
	private Carrier carrier;
	
	@ManyToOne
	private FlightTask task;
	
	@ManyToOne
	private SettlementItem settlementItem;
	
	@Enumerated(EnumType.STRING)
	@Column(length=6)
	private DiscountStyle style;
	
	/**
	 * 额度 比如90代表9折
	 */
	private int percentage;
	
	/**
	 *  直接优惠金额 项目直接的金额 
	 */
	private int price;
	
	
	/**
	 *   Transient标注的字段用来与前端框架进行交互显示，不真正作为保存字段
	 */
	
	@Transient
	public String settlementItemName;
	
	@Transient
	public String airlinesName;

	@Transient
	public String carrierName;
	
	@Transient
	public String taskName;
	

	@Override
	public Object getId() {
		return this.id;
	}

	@Override
	public void setId(Object id) {
		this.id = (Integer) id;
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

	/**
	 * @return the flightNO
	 */
	public String getFlightNO() {
		return flightNO;
	}

	/**
	 * @param flightNO the flightNO to set
	 */
	public void setFlightNO(String flightNO) {
		this.flightNO = flightNO;
	}

	/**
	 * @return the airlines
	 */
	public Airlines getAirlines() {
		return airlines;
	}

	/**
	 * @param airlines the airlines to set
	 */
	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

	/**
	 * @return the carrier
	 */
	public Carrier getCarrier() {
		return carrier;
	}

	/**
	 * @param carrier the carrier to set
	 */
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
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
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}



	/**
	 * @return the style
	 */
	public DiscountStyle getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(DiscountStyle style) {
		this.style = style;
	}

	/**
	 * @return the settlementTypeName
	 */
	public String getSettlementItemName() {
		if (this.getSettlementItem() != null){
			return this.getSettlementItem().getName();
		}else{
			return null;
		}
	}

	/**
	 * @param settlementTypeName the settlementTypeName to set
	 */
	public void setSettlementItemName(String settlementItemName) {
		this.settlementItemName = settlementItemName;
	}

	/**
	 * @return the airlinesName
	 */
	public String getAirlinesName() {
		if (this.getAirlines() != null){
			return this.getAirlines().getCnShortName();
		}else{
			return null;
		}
	}

	/**
	 * @param airlinesName the airlinesName to set
	 */
	public void setAirlinesName(String airlinesName) {
		this.airlinesName = airlinesName;
	}

	/**
	 * @return the carrierName
	 */
	public String getCarrierName() {
		if (this.getCarrier() != null){
			return this.getCarrier().getCnShortName();
		}else{
			return null;
		}
	}

	/**
	 * @param carrierName the carrierName to set
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	/**
	 * @return the percentage
	 */
	public int getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the task
	 */
	public FlightTask getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(FlightTask task) {
		this.task = task;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		if (this.task != null){
			return this.task.getCnShortName();
		}else{
			return null;
		}
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	
	
	
}
