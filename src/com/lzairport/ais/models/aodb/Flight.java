package com.lzairport.ais.models.aodb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 运输航班的抽象类
 * @author ZhangYu
 * @version 0.9a 08/09/14
 * @since JDK 1.6
 *
 */

@MappedSuperclass
public abstract class Flight extends BaseFlight {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	public static String LOADS = "loads";
	public static String ALTERNATEALALTERALANDINTIME = "alternateAlalteraLandinTime";
	public static String LOADSTATE ="loadstate";
	public static String STATE = "state";
	public static String INTERNALDELAYREASON ="internalDelayReason";
	public static String ALTERATEAKEOFFTIME="alteraTeakeOffTime";
	public static String ALTERNATEPLANTAKEOFFTIME ="alternatePlanTakeOffTime";
	public static String SHAREFLIGHTS = "shareFlights";
	public static String TAKEOFFLANDINCOUNT = "takeOffLandInCount";
	public static String ACTUALTAKEOFFTIME = "actualTakeOffTime";
	public static String ALTERNATEPLANLANDINTIME = "alternatePlanLandInTime";
	public static String ACTUALLANDINTIME ="actualLandInTime";
	public static String ALTERATEACTUALTAKEOFFTIME = "alterateActualTakeOffTime";
	public static String EXECDATE = "execDate";
	public static String AIRCRAFT = "aircraft";
	public static String ALTERATELANDINTIME = "alterateLandinTime";
	public static String GUESTRATE = "guestRate";
	public static String ALTERNATEACTUALTAKEOFFTIME = "alternateActualTakeOffTime";
	public static String ALTERNATEALTERATEAKEOFFTIME = "alternateAlteraTeakeOffTime";
	public static String PAYINGPASSENGERS = "payingPassengers";
	public static String FLIGHTDISPATCHS="flightDisPatchs";
	public static String ALTERNATEACTUALLANDINTIME="alternateActualLandInTime";
	public static String NORMAL ="normal";
	public static String EQUIPMENTINFO = "equipmentInfo";
	public static String ROUTEINFO = "routeInfo";
	public static String ALTERNATEINFO = "alternateInfo";
	public static String OTHERINFO = "otherInfo";
	public static String TELEXS = "telexs";
	public static String SSR = "ssr";
	public static String CLEARANCEDELAYREASON = "clearanceDelayReason";
	public static String CLEARANCENORMAL      = "clearanceNormal;"; 
	
	
	

	private Date execDate;
	
	@ManyToOne
	@JoinColumn(name="craftno")
	private Aircraft aircraft;

	private int guestRate;		
	
	private Date alteraTeakeOffTime;


	private Date actualTakeOffTime;


	private Date alterateLandinTime;

	
	private Date actualLandInTime;

	
	private Date alternateAlteraTeakeOffTime;


	private Date alternatePlanTakeOffTime;
	

	private Date alternateActualTakeOffTime;

	
	private Date alternateAlalteraLandinTime;
	
	
	private Date alternatePlanLandInTime;
	
	
	private Date alternateActualLandInTime;
	

	private Date planTakeOffTime;
	
	private Date planLandInTime;

	@Column(length=32)
	private String linkFlight; 
	
	
	private String equipmentInfo;
	
	private String routeInfo;
	
	private String alternateInfo;
	
	private String otherInfo;
	
	private int takeOffLandInCount;
	
	private boolean normal;
	
	/**
	 *  放行
	 */
	private boolean clearanceNormal;
	
	private String telexs;
	
	/**
	 *  应答机编码
	 */
	@Column(length=6)
	private String ssr;
	
	
	
	
	@ManyToOne
	@JoinColumn(name="state")
	private FlightState state;
	
	@Column(length=10)
	private String loadstate;
	
	@ManyToOne
	private DelayReason internalDelayReason;
	
	
	/**
	 * 放行不正常原因
	 */
	@ManyToOne
	private DelayReason clearanceDelayReason;
	

	
	/**
	 * 取得航班载量数据
	 * @return 航班载量数据集合
	 */

	public abstract Set<? extends FlightLoad> getLoads();


	/**
	 * 设置航班载量数据方法
	 * @param loads 载量集合
	 */

	public abstract void setLoads(Set<? extends FlightLoad> loads); 

	
	/**
	 * 取得航班付费旅客的方法
	 * @return 航班付费旅客集合
	 */

	public abstract Set<? extends PayingPassenger> getPayingPassengers();
	
	/**
	 * 设置航班付费旅客的方法
	 * @param loads 航班付费旅客
	 */

	public abstract  void setPayingPassengers(Set<? extends PayingPassenger> payingPassengers);
	
	/**
	 * 取得服务调度的方法
	 * @return 服务调度集合
	 */

	public abstract Set<? extends FlightDisPatch> getFlightDisPatchs();
	
	
	/**
	 * 设置服务调度的方法
	 * @param flightServices 服务调度
	 */

	public abstract void setFlightDisPatchs(Set<? extends FlightDisPatch> flightDisPatch);
	
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
	 * @return the alalterateLandinTime
	 */
	public Date getAlterateLandinTime() {
		return alterateLandinTime;
	}


	/**
	 * @param alalterateLandinTime the alalterateLandinTime to set
	 */
	public void setAlterateLandinTime(Date alterateLandinTime) {
		this.alterateLandinTime = alterateLandinTime;
	}


	/**
	 * @return the actualLandInTime
	 */
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
	 * @return the alternateAlteraTeakeOffTime
	 */
	public Date getAlternateAlteraTeakeOffTime() {
		return alternateAlteraTeakeOffTime;
	}


	/**
	 * @param alternateAlteraTeakeOffTime the alternateAlteraTeakeOffTime to set
	 */
	public void setAlternateAlteraTeakeOffTime(Date alternateAlteraTeakeOffTime) {
		this.alternateAlteraTeakeOffTime = alternateAlteraTeakeOffTime;
	}


	/**
	 * @return the alternatePlanTakeOffTime
	 */
	public Date getAlternatePlanTakeOffTime() {
		return alternatePlanTakeOffTime;
	}


	/**
	 * @param alternatePlanTakeOffTime the alternatePlanTakeOffTime to set
	 */
	public void setAlternatePlanTakeOffTime(Date alternatePlanTakeOffTime) {
		this.alternatePlanTakeOffTime = alternatePlanTakeOffTime;
	}


	/**
	 * @return the alternateActualTakeOffTime
	 */
	public Date getAlternateActualTakeOffTime() {
		return alternateActualTakeOffTime;
	}


	/**
	 * @param alternateActualTakeOffTime the alternateActualTakeOffTime to set
	 */
	public void setAlternateActualTakeOffTime(Date alternateActualTakeOffTime) {
		this.alternateActualTakeOffTime = alternateActualTakeOffTime;
	}


	/**
	 * @return the alternateAlalteraLandinTime
	 */
	public Date getAlternateAlalteraLandinTime() {
		return alternateAlalteraLandinTime;
	}


	/**
	 * @param alternateAlalteraLandinTime the alternateAlalteraLandinTime to set
	 */
	public void setAlternateAlalteraLandinTime(Date alternateAlalteraLandinTime) {
		this.alternateAlalteraLandinTime = alternateAlalteraLandinTime;
	}


	/**
	 * @return the alternatePlanLandInTime
	 */
	public Date getAlternatePlanLandInTime() {
		return alternatePlanLandInTime;
	}


	/**
	 * @param alternatePlanLandInTime the alternatePlanLandInTime to set
	 */
	public void setAlternatePlanLandInTime(Date alternatePlanLandInTime) {
		this.alternatePlanLandInTime = alternatePlanLandInTime;
	}


	/**
	 * @return the alternateActualLandInTime
	 */
	public Date getAlternateActualLandInTime() {
		return alternateActualLandInTime;
	}


	/**
	 * @param alternateActualLandInTime the alternateActualLandInTime to set
	 */
	public void setAlternateActualLandInTime(Date alternateActualLandInTime) {
		this.alternateActualLandInTime = alternateActualLandInTime;
	}


	/**
	 * @return the takeOffLandInCount
	 */
	public int getTakeOffLandInCount() {
		return takeOffLandInCount;
	}



	/**
	 * @param takeOffLandInCount the takeOffLandInCount to set
	 */
	public void setTakeOffLandInCount(int takeOffLandInCount) {
		this.takeOffLandInCount = takeOffLandInCount;
	}



	/**
	 * @return the state
	 */
	public FlightState getState() {
		return state;
	}



	/**
	 * @param state the state to set
	 */
	public void setState(FlightState state) {
		this.state = state;
	}



	/**
	 * @return the internalDelayReason
	 */
	public DelayReason getInternalDelayReason() {
		return internalDelayReason;
	}



	/**
	 * @param internalDelayReason the internalDelayReason to set
	 */
	public void setInternalDelayReason(DelayReason internalDelayReason) {
		this.internalDelayReason = internalDelayReason;
	}

	/**
	 * @return the execDate
	 */
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
	 * @return the aircraft
	 */
	public Aircraft getAircraft() {
		return aircraft;
	}



	/**
	 * @param aircraft the aircraft to set
	 */
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
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
	 * @return the loadstate
	 */
	public String getLoadstate() {
		return loadstate;
	}



	/**
	 * @param loadstate the loadstate to set
	 */
	public void setLoadstate(String loadstate) {
		this.loadstate = loadstate;
	}


	/**
	 * @return the normal
	 */
	public boolean isNormal() {
		return normal;
	}


	/**
	 * @param normal the normal to set
	 */
	public void setNormal(boolean normal) {
		this.normal = normal;
	}


	/**
	 * @return the equipmentInfo
	 */
	public String getEquipmentInfo() {
		return equipmentInfo;
	}


	/**
	 * @param equipmentInfo the equipmentInfo to set
	 */
	public void setEquipmentInfo(String equipmentInfo) {
		this.equipmentInfo = equipmentInfo;
	}


	/**
	 * @return the routeInfo
	 */
	public String getRouteInfo() {
		return routeInfo;
	}


	/**
	 * @param routeInfo the routeInfo to set
	 */
	public void setRouteInfo(String routeInfo) {
		this.routeInfo = routeInfo;
	}


	/**
	 * @return the alternateInfo
	 */
	public String getAlternateInfo() {
		return alternateInfo;
	}


	/**
	 * @param alternateInfo the alternateInfo to set
	 */
	public void setAlternateInfo(String alternateInfo) {
		this.alternateInfo = alternateInfo;
	}


	/**
	 * @return the otherInfo
	 */
	public String getOtherInfo() {
		return otherInfo;
	}


	/**
	 * @param otherInfo the otherInfo to set
	 */
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
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
	 * @return the telexs
	 */
	public String getTelexs() {
		return telexs;
	}


	/**
	 * @param telexs the telexs to set
	 */
	public void setTelexs(String telexs) {
		this.telexs = telexs;
	}






	/**
	 * @return the ssr
	 */
	public String getSsr() {
		return ssr;
	}


	/**
	 * @param ssr the ssr to set
	 */
	public void setSsr(String ssr) {
		this.ssr = ssr;
	}
	

	/**
	 * @return the clearanceNormal
	 */
	public boolean isClearanceNormal() {
		return clearanceNormal;
	}


	/**
	 * @param clearanceNormal the clearanceNormal to set
	 */
	public void setClearanceNormal(boolean clearanceNormal) {
		this.clearanceNormal = clearanceNormal;
	}


	/**
	 * @return the clearanceDelayReason
	 */
	public DelayReason getClearanceDelayReason() {
		return clearanceDelayReason;
	}


	/**
	 * @param clearanceDelayReason the clearanceDelayReason to set
	 */
	public void setClearanceDelayReason(DelayReason clearanceDelayReason) {
		this.clearanceDelayReason = clearanceDelayReason;
	}


	/**
	 * 
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = this.getFlightNO();
		
		if (this.getDepAirport() != null){
			str += "/"+this.getDepAirport().getThreeCharCode();
		}
		
		if (this.getArrAirport() != null){
			str += "-"+this.getArrAirport().getThreeCharCode();
		}
		
		if (this.getExecDate() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			str += "/"+sdf.format(this.getExecDate());
		}
		
		return str;
	}



	
}
