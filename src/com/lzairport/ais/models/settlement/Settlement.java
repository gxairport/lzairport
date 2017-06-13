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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.models.aodb.Aircraft;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.Airport;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.Carrier;
import com.lzairport.ais.models.aodb.CraftType;
import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.utils.AisBeanUtils;
import com.lzairport.ais.utils.SYS_VARS.OutIn;

/**
 * 
 * FileName      Settlement.java
 * @Description  TODO 结算数据
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月7日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年11月7日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Entity
public class Settlement extends DefaultEntity implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 各个数据字段名,用来直接点用字段名
	 */
	public static String ID = "id";
	public static String FLIGHT = "flight";
	public static String SETTLEMENTITEM = "settlementItem";
	public static String SETTLEMENTCATEGORY = "settlementCategory";
	public static String STARTTIME = "startTime";
	public static String ENDTIME = "endTime";
	public static String DISCOUNT = "discount";
	public static String AUOMNT = "auomnt";
	public static String NUMBER = "number";
	public static String PRICE  = "price";
	public static String EXECDATE ="execDate";
	public static String CREATETIME = "createTime";
	public static String FLIGHTNO = "flightNo";
	public static String CRAFTNO = "craftno";
	public static String DEPAIRPORT = "depAirport";
	public static String ARRAIRPORT = "arrAirport";
	public static String TASK = "task";
	public static String AIRLINES ="airlines";
	public static String ROUTEHX="routeHX";
	public static String CARRIER ="carrier";
	public static String CRAFTTYPE = "craftType";
	public static String ESTIMATE="estimate";
	public static String ATTRIBUTE ="attribute";
	public static String IOTime="ioTime";
	public static String CRAFTSELLEMENT = "craftSellement";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private  String flightNO;
	
	private  String craftno;
	
	private  String routeCode;
	
	private  String routeHX;
	
	/**
	 *  业载和起飞全重字段
	 */
	@Column(length=15)
	private  String craftSellement;
	
	private  boolean estimate;
	
	@Enumerated(EnumType.STRING)
	@Column(length=3)
	private OutIn isOutIn;
	
	@ManyToOne
	@JoinColumn(name="depAirport") 
	private  Airport depAirport;
	
	@ManyToOne
	@JoinColumn(name="arrAirport") 
	private  Airport arrAirport;
	
	@ManyToOne  
	@JoinColumn(name="task") 
	private  FlightTask task;

	@ManyToOne
	@JoinColumn(name="carftType") 
	public CraftType craftType;
	
	@ManyToOne
	@JoinColumn(name="airlines") 
	public Airlines airlines;

	@ManyToOne
	public Carrier  carrier;
	
	@ManyToOne
	public AreaAttribute attribute;
	
	@ManyToOne
	private SettlementItem settlementItem;
	
	@ManyToOne
	private SettlementCategory settlementCategory;
	
	private Date ioTime;
	
	
	/**
	 *   开始时间 
	 */
	private Date startTime;

	/**
	 *   结束时间
	 */
	private Date endTime;
	
	/**
	 *  折扣
	 */
	private int discount;
	
	/**
	 *  数量
	 */
	private Double number;
	
	/**
	 *  单价
	 */
	private Double price;
	
	/**
	 *  金额
	 */
	private Double auomnt;
	
	/**
	 * 执行日期
	 */
	private Date execDate;
	
	/**
	 * 生成时间
	 */
	private Date createTime;
	
	/**
	 *   Transient标注的字段用来与前端框架进行交互显示，不真正作为保存字段
	 */
	@Transient
	public String settlementItemName;
	
	@Transient
	public String settlementCategoryName;
	
	@Transient
	public String depAirportName;
	
	@Transient
	public String arrAirportName;
	
	@Transient
	public String taskName;

	@Transient
	public String craftTypeCode;
	
	@Transient
	public String airlinesName;
	
	@Transient
	public String carrierName;
	
	@Transient
	public String attribeName;
	
	

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
	 * @return the auomnt
	 */
	public Double getAuomnt() {
		return auomnt;
	}

	/**
	 * @param auomnt the auomnt to set
	 */
	public void setAuomnt(Double auomnt) {
		this.auomnt = auomnt;
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
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	/**
	 * @return the number
	 */
	public Double getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Double number) {
		this.number = number;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
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
	public void setFlightNO(String flightNO) {
		this.flightNO = flightNO;
	}

	/**
	 * @return the craftno
	 */
	public String getCraftno() {
		return craftno;
	}

	/**
	 * @param craftno the craftno to set
	 */
	public void setCraftno(String craftno) {
		this.craftno = craftno;
	}

	/**
	 * @return the depAirport
	 */
	public Airport getDepAirport() {
		return depAirport;
	}

	/**
	 * @param depAirport the depAirport to set
	 */
	public void setDepAirport(Airport depAirport) {
		this.depAirport = depAirport;
	}

	/**
	 * @return the arrAirport
	 */
	public Airport getArrAirport() {
		return arrAirport;
	}

	/**
	 * @param arrAirport the arrAirport to set
	 */
	public void setArrAirport(Airport arrAirport) {
		this.arrAirport = arrAirport;
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the craftType
	 */
	public CraftType getCraftType() {
		return craftType;
	}

	/**
	 * @param craftType the craftType to set
	 */
	public void setCraftType(CraftType craftType) {
		this.craftType = craftType;
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
	 * @return the routeHX
	 */
	public String getRouteCode() {
		return routeCode;
	}

	/**
	 * @param routeHX the routeHX to set
	 */
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
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
	 *   Transient标注的字段用来与前端框架进行交互显示，不真正作为保存字段
	 */


	/**
	 * @return the settlementCategory
	 */
	public SettlementCategory getSettlementCategory() {
		return settlementCategory;
	}

	/**
	 * @param settlementCategory the settlementCategory to set
	 */
	public void setSettlementCategory(SettlementCategory settlementCategory) {
		this.settlementCategory = settlementCategory;
	}

	/**
	 * @return the settlementItemName
	 */
	public String getSettlementItemName() {
		if (this.settlementItem != null){
			return this.settlementItem.getName();
		} else{
			return null;
		}
	}

	/**
	 * @param settlementItemName the settlementItemName to set
	 */
	public void setSettlementItemName(String settlementItemName) {
		this.settlementItemName = settlementItemName;
	}

	/**
	 * @return the depAirportName
	 */
	public String getDepAirportName() {
		if (this.depAirport != null){
			return this.depAirport.getCnName();
		}else{
			return null;
		}
	}

	/**
	 * @param depAirportName the depAirportName to set
	 */
	public void setDepAirportName(String depAirportName) {
		this.depAirportName = depAirportName;
	}

	/**
	 * @return the arrAirportName
	 */
	public String getArrAirportName() {
		if (this.arrAirport != null){
			return this.arrAirport.getCnName();
		}else{
			return null;
		}
	}

	/**
	 * @param arrAirportName the arrAirportName to set
	 */
	public void setArrAirportName(String arrAirportName) {
		this.arrAirportName = arrAirportName;
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

	/**
	 * @return the craftTypeCode
	 */
	public String getCraftTypeCode() {
		if (this.craftType != null){
			return this.craftType.getCode();
		}else{
			return null;
		}
	}

	/**
	 * @param craftTypeCode the craftTypeCode to set
	 */
	public void setCraftTypeCode(String craftTypeCode) {
		this.craftTypeCode = craftTypeCode;
	}

	/**
	 * @return the airlinesName
	 */
	public String getAirlinesName() {
		if (this.airlines != null){
			return this.airlines.getCnShortName();
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
		if (this.carrier != null){
			return this.carrier.getCnShortName();
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
	 * @return the settlementTypeName
	 */
	public String getSettlementCategoryName() {
		if (this.settlementCategory != null){
			return this.settlementCategory.getName();
		}else{
			return null;
		}
	}

	/**
	 * @param settlementTypeName the settlementTypeName to set
	 */
	public void setSettlementCategoryName(String settlementCategoryName) {
		this.settlementCategoryName = settlementCategoryName;
	}

	/**
	 * @return the estimate
	 */
	public boolean isEstimate() {
		return estimate;
	}

	/**
	 * @param estimate the estimate to set
	 */
	public void setEstimate(boolean estimate) {
		this.estimate = estimate;
	}

	/**
	 * @return the attribute
	 */
	public AreaAttribute getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(AreaAttribute attribute) {
		this.attribute = attribute;
	}

	/**
	 * @return the attribeName
	 */
	public String getAttribeName() {
		if (this.attribute != null){
			return this.attribute.getCnShortName();
		}else{
			return null;
		}
	}

	/**
	 * @param attribeName the attribeName to set
	 */
	public void setAttribeName(String attribeName) {
		this.attribeName = attribeName;
	}

	/**
	 * @return the ioTime
	 */
	public Date getIoTime() {
		return ioTime;
	}

	/**
	 * @param ioTime the ioTime to set
	 */
	public void setIoTime(Date ioTime) {
		this.ioTime = ioTime;
	}

	public String getCraftSellement() {
		return craftSellement;
	}

	public void setCraftSellement(String craftSellement) {
		this.craftSellement = craftSellement;
	}
	
	public void setFlightField(HisFlight flight) throws Exception {
		AisBeanUtils.copyProperties(this, flight);
		Aircraft aircraft = flight.getAircraft();
		String aircraftSettlement = flight.getAircraft().getCraftTypeCode().substring(0, 4)+"-"+
				String.valueOf(aircraft.getSettlementLoad())+","+
				String.valueOf(aircraft.getMaxWeight());
		if (flight.getAircraft() != null ){
			setCraftno(aircraft.getCraftno());
			setCarrier(aircraft.getCarrier());
			setCraftType(aircraft.getCraftType());
			setCraftSellement(aircraftSettlement);
		}
		
		if (OutIn.Dep.equals(flight.getIsOutIn())){
			setIoTime(flight.getActualTakeOffTime());
		}else{
			setIoTime(flight.getActualLandInTime());
		}
		
	}

}
