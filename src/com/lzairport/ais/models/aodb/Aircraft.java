package com.lzairport.ais.models.aodb;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 飞机的实体类
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class Aircraft extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	
	public static String  ID = "id";
	public static String  AVAILABLESEAT ="availableSeat";
	public static String  MAXSEAT = "maxSeat";
	public static String  CRAFTNO = "craftno";
	public static String  CRAFTTYPE =  "craftType";
	public static String  MAXLOAD = "maxLoad";
	public static String  CARRIER = "carrier";
	public static String  AVAILABLELOAD  = "availableLoad";
	public static String  SETTLEMENTLOAD = "settlementLoad";
	public static String  MAXWEIGHT = "MaxWeight";	
	public static String  ASPTYPE = "aspType";
	public static String  FPLTYPE = "fplType";
	public static String  EQUIPMENT ="equipment";
	public static String  SEL = "sel";
	public static String  TRACTOR = "tractor";
	public static String  GUIDE   = "guide";
	public static String  FERRYP  = "ferryP";
	public static String  LADDER  = "ladder";
	public static String  PERMIT  = "permit";
	public static String  ROUTINE = "routine";
	public static String  BRIDGE  = "bridge";
	public static String  DISP    = "disp";
	public static String  ASPD    = "aspd";
	public static String  STARTTIME = "startTime";
	public static String  ENDTIME   = "endTime";
	
	@Column(name="id")
	private int id;
	
	@Id
	@Column(length=8)
	private String	craftno;
	
	@ManyToOne
	@JoinColumn(name="craftType")
	private CraftType craftType;
	
	@ManyToOne
	@JoinColumn(name="carrier")
	private Carrier carrier;
	
	private int MaxWeight;
	
	private int maxLoad;
	
	private int maxSeat;
	
	/**
	 * 结算所用到的业载
	 */
	private int settlementLoad;
	
	private int availableLoad;
	
	private int availableSeat;
	
	/**
	 * 统计系统的航班类型
	 */
	@Column(length=5)
	private String aspType;
	
	/**
	 *  FPL飞机的类型，带有尾流信息
	 */
	@Column(length=8)
	private String fplType;
	
	/**
	 *   机载设备及能力
	 */
	@Column(length=20)
	private String equipment;
	
	
	/**
	 *  选择呼叫编码	
	 */
	@Column(length=8)
	private String sel;
	
	/**
	 *  结算收入标志，机务例行检查
	 */
	private boolean routine;
	
	/**
	 *  结算收入标志，机务放行
	 */
	private boolean permit;
	
	/**
	 *  结算收入标志，牵引车
	 */
	private boolean tractor;
	
	/**
	 *  结算收入标志，引导车
	 */
	private boolean guide;
	
	/**
	 *  结算收入标志，摆渡车
	 */
	private boolean ferryP;
	
	
	/**
	 * 结算收入标志，客梯车
	 */
	private boolean ladder;
	
	/**
	 * 结算收入标志，客桥
	 */
	private boolean bridge;
	
	/**
	 *  结算收入标志，签派
	 */
	private boolean disp;
	
	/**
	 *  结算收入标志，上机服务
	 */
	private boolean aspd;
	
	/**
	 *  开始时间 用于清算飞机数据更新
	 */
	private Date startTime;
	
	/**
	 * 结束时间 用于清算飞行数据更新
	 */
	private Date endTime;
	
	
	/**
	 *   Transient标注的字段用来与前端框架进行交互显示，不真正作为保存字段
	 */
	
	@Transient
	public String carrierName;
	
	@Transient
	public String craftTypeCode; 
	
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
	 * @return the maxWeight
	 */
	public int getMaxWeight() {
		return MaxWeight;
	}

	/**
	 * @param maxWeight the maxWeight to set
	 */
	public void setMaxWeight(int maxWeight) {
		MaxWeight = maxWeight;
	}

	/**
	 * @return the maxLoad
	 */
	public int getMaxLoad() {
		return maxLoad;
	}

	/**
	 * @param maxLoad the maxLoad to set
	 */
	public void setMaxLoad(int maxLoad) {
		this.maxLoad = maxLoad;
	}

	/**
	 * @return the maxSeat
	 */
	public int getMaxSeat() {
		return maxSeat;
	}

	/**
	 * @param maxSeat the maxSeat to set
	 */
	public void setMaxSeat(int maxSeat) {
		this.maxSeat = maxSeat;
	}

	/**
	 * @return the availableLoad
	 */
	public int getAvailableLoad() {
		return availableLoad;
	}

	/**
	 * @param availableLoad the availableLoad to set
	 */
	public void setAvailableLoad(int availableLoad) {
		this.availableLoad = availableLoad;
	}

	/**
	 * @return the availableSeat
	 */
	public int getAvailableSeat() {
		return availableSeat;
	}

	/**
	 * @param availableSeat the availableSeat to set
	 */
	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	/**
	 * @return the aspType
	 */
	public String getAspType() {
		return aspType;
	}

	/**
	 * @param aspType the aspType to set
	 */
	public void setAspType(String aspType) {
		this.aspType = aspType;
	}

	/**
	 * @return the fplType
	 */
	public String getFplType() {
		return fplType;
	}

	/**
	 * @param fplType the fplType to set
	 */
	public void setFplType(String fplType) {
		this.fplType = fplType;
	}

	/**
	 * @return the equipment
	 */
	public String getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment the equipment to set
	 */
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	/**
	 * @return the sel
	 */
	public String getSel() {
		return sel;
	}

	/**
	 * @param sel the sel to set
	 */
	public void setSel(String sel) {
		this.sel = sel;
	}

	/**
	 * @return the tractor
	 */
	public boolean isTractor() {
		return tractor;
	}

	/**
	 * @param tractor the tractor to set
	 */
	public void setTractor(boolean tractor) {
		this.tractor = tractor;
	}

	/**
	 * @return the guide
	 */
	public boolean isGuide() {
		return guide;
	}

	/**
	 * @param guide the guide to set
	 */
	public void setGuide(boolean guide) {
		this.guide = guide;
	}

	/**
	 * @return the ferryP
	 */
	public boolean isFerryP() {
		return ferryP;
	}

	/**
	 * @param ferryP the ferryP to set
	 */
	public void setFerryP(boolean ferryP) {
		this.ferryP = ferryP;
	}

	/**
	 * @return the ladder
	 */
	public boolean isLadder() {
		return ladder;
	}

	/**
	 * @param ladder the ladder to set
	 */
	public void setLadder(boolean ladder) {
		this.ladder = ladder;
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
	 * @return the type
	 */
	public String getCraftTypeCode() {
		if (this.craftType != null){
			return this.craftType.getCode();
		}else{
			return null;
		}
	}

	/**
	 * @param craftTypeCode the type to set
	 */
	public void setCraftTypeCode(String craftTypeCode) {
		this.craftTypeCode = craftTypeCode;
	}

	/**
	 * @return the routine
	 */
	public boolean isRoutine() {
		return routine;
	}

	/**
	 * @param routine the routine to set
	 */
	public void setRoutine(boolean routine) {
		this.routine = routine;
	}

	/**
	 * @return the permit
	 */
	public boolean isPermit() {
		return permit;
	}

	/**
	 * @param permit the permit to set
	 */
	public void setPermit(boolean permit) {
		this.permit = permit;
	}

	/**
	 * @return the startTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
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
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
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
	 * @return the bridge
	 */
	public boolean isBridge() {
		return bridge;
	}

	/**
	 * @param bridge the bridge to set
	 */
	public void setBridge(boolean bridge) {
		this.bridge = bridge;
	}

	/**
	 * @return the disp
	 */
	public boolean isDisp() {
		return disp;
	}

	/**
	 * @param disp the disp to set
	 */
	public void setDisp(boolean disp) {
		this.disp = disp;
	}

	/**
	 * @return the aspd
	 */
	public boolean isAspd() {
		return aspd;
	}

	/**
	 * @param aspd the aspd to set
	 */
	public void setAspd(boolean aspd) {
		this.aspd = aspd;
	}

	/**
	 * 
	 * @Description: TODO 重写getIdValue方法
	 * @return
	 */
	@Override
	public Object getIdValue() {
		// TODO Auto-generated method stub
		return this.getCraftno();
	}

	/**
	 * @return the settlementLoad
	 */
	public int getSettlementLoad() {
		return settlementLoad;
	}

	/**
	 * @param settlementLoad the settlementLoad to set
	 */
	public void setSettlementLoad(int settlementLoad) {
		this.settlementLoad = settlementLoad;
	}

	
	
}
