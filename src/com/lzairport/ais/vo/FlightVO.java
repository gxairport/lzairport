package com.lzairport.ais.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * FileName      FlightDTO.java
 * @Description  航班实体的值对象
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015年10月2日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015年10月2日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

public class FlightVO implements Serializable {
	
	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String flightNO;
	
	private String airlines_Id;
	
	private String airlines_Name;
	
	private int  dlyReason_Id;
	
	private String dlyReason_Name;
	
	private int state_Id;
	
	private String state_Name;
	
	private String depAP_Id;
	
	private String depAP_Name;
	
	private String arrAP_Id;
	
	private String arrAP_Name;
	
	private String task_Id;
	
	private String task_Name;
	
	private int attribute_Id;
	
	private String attribute_Name;
	
	private String route;
	
	private Date planTakeOffTime;
	
	private Date planLandInTime;
	
	private Date alteraTeakeOffTime;

	private Date actualTakeOffTime;

	private Date alterateLandinTime;
	
	private Date actualLandInTime;
	
	private String linkFlight;
	
	private String aircraft_NO;
	
	private String carrier_Name;

	private int avb_SeatNum;
	
	private int max_SeatNum;
	
	private int avb_Load;
	
	private int max_Load;
	
	private String aspType;
	
	private Date execDate;
	
	private Boolean approve;
	
	private int guestRate;
	
	private int loc_Adult;
	
	private int loc_Chd;
	
	private int loc_Inf;
	
	private int loc_Goods;
	
	private int loc_Mail;
	
	private int loc_Luggage;	
	
	private int tra_Adult;
	
	private int tra_Chd;
	
	private int tra_Inf;
	
	private int tra_Goods;
	
	private int tra_Mail;
	
	private int tra_Luggage;
	
	
	
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

	private List<FlightLoadVO> loadsVO;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the airlines_Id
	 */
	public String getAirlines_Id() {
		return airlines_Id;
	}

	/**
	 * @param airlines_Id the airlines_Id to set
	 */
	public void setAirlines_Id(String airlines_Id) {
		this.airlines_Id = airlines_Id;
	}

	/**
	 * @return the airlines_Name
	 */
	public String getAirlines_Name() {
		return airlines_Name;
	}

	/**
	 * @param airlines_Name the airlines_Name to set
	 */
	public void setAirlines_Name(String airlines_Name) {
		this.airlines_Name = airlines_Name;
	}

	/**
	 * @return the dlyReason_Id
	 */
	public int getDlyReason_Id() {
		return dlyReason_Id;
	}

	/**
	 * @param dlyReason_Id the dlyReason_Id to set
	 */
	public void setDlyReason_Id(int dlyReason_Id) {
		this.dlyReason_Id = dlyReason_Id;
	}

	/**
	 * @return the dlyReason_Name
	 */
	public String getDlyReason_Name() {
		return dlyReason_Name;
	}

	/**
	 * @param dlyReason_Name the dlyReason_Name to set
	 */
	public void setDlyReason_Name(String dlyReason_Name) {
		this.dlyReason_Name = dlyReason_Name;
	}

	/**
	 * @return the state_Id
	 */
	public int getState_Id() {
		return state_Id;
	}

	/**
	 * @param state_Id the state_Id to set
	 */
	public void setState_Id(int state_Id) {
		this.state_Id = state_Id;
	}

	/**
	 * @return the state_Name
	 */
	public String getState_Name() {
		return state_Name;
	}

	/**
	 * @param state_Name the state_Name to set
	 */
	public void setState_Name(String state_Name) {
		this.state_Name = state_Name;
	}

	/**
	 * @return the depAP_Id
	 */
	public String getDepAP_Id() {
		return depAP_Id;
	}

	/**
	 * @param depAP_Id the depAP_Id to set
	 */
	public void setDepAP_Id(String depAP_Id) {
		this.depAP_Id = depAP_Id;
	}

	/**
	 * @return the depAP_Name
	 */
	public String getDepAP_Name() {
		return depAP_Name;
	}

	/**
	 * @param depAP_Name the depAP_Name to set
	 */
	public void setDepAP_Name(String depAP_Name) {
		this.depAP_Name = depAP_Name;
	}

	/**
	 * @return the arrAP_Id
	 */
	public String getArrAP_Id() {
		return arrAP_Id;
	}

	/**
	 * @param arrAP_Id the arrAP_Id to set
	 */
	public void setArrAP_Id(String arrAP_Id) {
		this.arrAP_Id = arrAP_Id;
	}

	/**
	 * @return the arrAP_Name
	 */
	public String getArrAP_Name() {
		return arrAP_Name;
	}

	/**
	 * @param arrAP_Name the arrAP_Name to set
	 */
	public void setArrAP_Name(String arrAP_Name) {
		this.arrAP_Name = arrAP_Name;
	}

	/**
	 * @return the task_Id
	 */
	public String getTask_Id() {
		return task_Id;
	}

	/**
	 * @param task_Id the task_Id to set
	 */
	public void setTask_Id(String task_Id) {
		this.task_Id = task_Id;
	}

	/**
	 * @return the task_Name
	 */
	public String getTask_Name() {
		return task_Name;
	}

	/**
	 * @param task_Name the task_Name to set
	 */
	public void setTask_Name(String task_Name) {
		this.task_Name = task_Name;
	}

	/**
	 * @return the attribute_Id
	 */
	public int getAttribute_Id() {
		return attribute_Id;
	}

	/**
	 * @param attribute_Id the attribute_Id to set
	 */
	public void setAttribute_Id(int attribute_Id) {
		this.attribute_Id = attribute_Id;
	}

	/**
	 * @return the attribute_Name
	 */
	public String getAttribute_Name() {
		return attribute_Name;
	}

	/**
	 * @param attribute_Name the attribute_Name to set
	 */
	public void setAttribute_Name(String attribute_Name) {
		this.attribute_Name = attribute_Name;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * @return the planTakeOffTime
	 */
	public Date getPlanTakeOffTime() {
		return planTakeOffTime;
	}

	/**
	 * @param planTakeOffTime the planTakeOffTime to set
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  	
	public void setPlanTakeOffTime(Date planTakeOffTime) {
		this.planTakeOffTime = planTakeOffTime;
	}

	/**
	 * @return the planLandInTime
	 */
	public Date getPlanLandInTime() {
		return planLandInTime;
	}

	/**
	 * @param planLandInTime the planLandInTime to set
	 */
	public void setPlanLandInTime(Date planLandInTime) {
		this.planLandInTime = planLandInTime;
	}

	/**
	 * @return the alteraTeakeOffTime
	 */
	public Date getAlteraTeakeOffTime() {
		return alteraTeakeOffTime;
	}

	/**
	 * @param alteraTeakeOffTime the alteraTeakeOffTime to set
	 */
	public void setAlteraTeakeOffTime(Date alteraTeakeOffTime) {
		this.alteraTeakeOffTime = alteraTeakeOffTime;
	}

	/**
	 * @return the actualTakeOffTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  	
	public Date getActualTakeOffTime() {
		return actualTakeOffTime;
	}

	/**
	 * @param actualTakeOffTime the actualTakeOffTime to set
	 */
	public void setActualTakeOffTime(Date actualTakeOffTime) {
		this.actualTakeOffTime = actualTakeOffTime;
	}

	/**
	 * @return the alterateLandinTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 	
	public Date getAlterateLandinTime() {
		return alterateLandinTime;
	}

	/**
	 * @param alterateLandinTime the alterateLandinTime to set
	 */
	public void setAlterateLandinTime(Date alterateLandinTime) {
		this.alterateLandinTime = alterateLandinTime;
	}

	/**
	 * @return the actualLandInTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getActualLandInTime() {
		return actualLandInTime;
	}

	/**
	 * @param actualLandInTime the actualLandInTime to set
	 */
	public void setActualLandInTime(Date actualLandInTime) {
		this.actualLandInTime = actualLandInTime;
	}

	/**
	 * @return the aircraft_NO
	 */
	public String getAircraft_NO() {
		return aircraft_NO;
	}

	/**
	 * @param aircraft_NO the aircraft_NO to set
	 */
	public void setAircraft_NO(String aircraft_NO) {
		this.aircraft_NO = aircraft_NO;
	}

	/**
	 * @return the carrier_Name
	 */
	public String getCarrier_Name() {
		return carrier_Name;
	}

	/**
	 * @param carrier_Name the carrier_Name to set
	 */
	public void setCarrier_Name(String carrier_Name) {
		this.carrier_Name = carrier_Name;
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
	 * @return the approve
	 */
	public Boolean getApprove() {
		return approve;
	}

	/**
	 * @param approve the approve to set
	 */
	public void setApprove(Boolean approve) {
		this.approve = approve;
	}

	/**
	 * @return the guestRate
	 */
	public int getGuestRate() {
		return guestRate;
	}

	/**
	 * @param guestRate the guestRate to set
	 */
	public void setGuestRate(int guestRate) {
		this.guestRate = guestRate;
	}



	/**
	 * @return the loc_Adult
	 */
	public int getLoc_Adult() {
		return loc_Adult;
	}

	/**
	 * @param loc_Adult the loc_Adult to set
	 */
	public void setLoc_Adult(int loc_Adult) {
		this.loc_Adult = loc_Adult;
	}

	/**
	 * @return the loc_Chd
	 */
	public int getLoc_Chd() {
		return loc_Chd;
	}

	/**
	 * @param loc_Chd the loc_Chd to set
	 */
	public void setLoc_Chd(int loc_Chd) {
		this.loc_Chd = loc_Chd;
	}

	/**
	 * @return the loc_Inf
	 */
	public int getLoc_Inf() {
		return loc_Inf;
	}

	/**
	 * @param loc_Inf the loc_Inf to set
	 */
	public void setLoc_Inf(int loc_Inf) {
		this.loc_Inf = loc_Inf;
	}

	/**
	 * @return the loc_Goods
	 */
	public int getLoc_Goods() {
		return loc_Goods;
	}

	/**
	 * @param loc_Goods the loc_Goods to set
	 */
	public void setLoc_Goods(int loc_Goods) {
		this.loc_Goods = loc_Goods;
	}

	/**
	 * @return the loc_Mail
	 */
	public int getLoc_Mail() {
		return loc_Mail;
	}

	/**
	 * @param loc_Mail the loc_Mail to set
	 */
	public void setLoc_Mail(int loc_Mail) {
		this.loc_Mail = loc_Mail;
	}

	/**
	 * @return the loc_Luggage
	 */
	public int getLoc_Luggage() {
		return loc_Luggage;
	}

	/**
	 * @param loc_Luggage the loc_Luggage to set
	 */
	public void setLoc_Luggage(int loc_Luggage) {
		this.loc_Luggage = loc_Luggage;
	}

	/**
	 * @return the tra_Adult
	 */
	public int getTra_Adult() {
		return tra_Adult;
	}

	/**
	 * @param tra_Adult the tra_Adult to set
	 */
	public void setTra_Adult(int tra_Adult) {
		this.tra_Adult = tra_Adult;
	}

	/**
	 * @return the tra_Chd
	 */
	public int getTra_Chd() {
		return tra_Chd;
	}

	/**
	 * @param tra_Chd the tra_Chd to set
	 */
	public void setTra_Chd(int tra_Chd) {
		this.tra_Chd = tra_Chd;
	}

	/**
	 * @return the tra_Inf
	 */
	public int getTra_Inf() {
		return tra_Inf;
	}

	/**
	 * @param tra_Inf the tra_Inf to set
	 */
	public void setTra_Inf(int tra_Inf) {
		this.tra_Inf = tra_Inf;
	}

	/**
	 * @return the tra_Goods
	 */
	public int getTra_Goods() {
		return tra_Goods;
	}

	/**
	 * @param tra_Goods the tra_Goods to set
	 */
	public void setTra_Goods(int tra_Goods) {
		this.tra_Goods = tra_Goods;
	}

	/**
	 * @return the tra_Mail
	 */
	public int getTra_Mail() {
		return tra_Mail;
	}

	/**
	 * @param tra_Mail the tra_Mail to set
	 */
	public void setTra_Mail(int tra_Mail) {
		this.tra_Mail = tra_Mail;
	}

	/**
	 * @return the tra_Luggage
	 */
	public int getTra_Luggage() {
		return tra_Luggage;
	}

	/**
	 * @param tra_Luggage the tra_Luggage to set
	 */
	public void setTra_Luggage(int tra_Luggage) {
		this.tra_Luggage = tra_Luggage;
	}

	/**
	 * @return the loads
	 */
	public List<FlightLoadVO> getLoadsVO() {
		return loadsVO;
	}

	/**
	 * @param loads the loads to set
	 */
	public void setLoadsVO(List<FlightLoadVO> loadsVO) {
		this.loadsVO = loadsVO;
	}

	/**
	 * @return the avb_SeatNum
	 */
	public int getAvb_SeatNum() {
		return avb_SeatNum;
	}

	/**
	 * @param avb_SeatNum the avb_SeatNum to set
	 */
	public void setAvb_SeatNum(int avb_SeatNum) {
		this.avb_SeatNum = avb_SeatNum;
	}

	/**
	 * @return the max_SeatNum
	 */
	public int getMax_SeatNum() {
		return max_SeatNum;
	}

	/**
	 * @param max_SeatNum the max_SeatNum to set
	 */
	public void setMax_SeatNum(int max_SeatNum) {
		this.max_SeatNum = max_SeatNum;
	}

	/**
	 * @return the avb_Load
	 */
	public int getAvb_Load() {
		return avb_Load;
	}

	/**
	 * @param avb_Load the avb_Load to set
	 */
	public void setAvb_Load(int avb_Load) {
		this.avb_Load = avb_Load;
	}

	/**
	 * @return the max_load
	 */
	public int getMax_Load() {
		return max_Load;
	}

	/**
	 * @param max_load the max_load to set
	 */
	public void setMax_Load(int max_Load) {
		this.max_Load = max_Load;
	}

	/**
	 * @return the linkFlight
	 */
	public String getLinkFlight() {
		return linkFlight;
	}

	/**
	 * @param linkFlight the linkFlight to set
	 */
	public void setLinkFlight(String linkFlight) {
		this.linkFlight = linkFlight;
	}

	
	
	

}
