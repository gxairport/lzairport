package com.lzairport.ais.models.aodb;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.lzairport.ais.utils.SYS_VARS.Quarter;
import com.lzairport.ais.utils.SYS_VARS.Week;

/**
 * 航班班期计划
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class ScheduleFlight extends BaseFlight {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	public static String SHAREFLIGHTS = "shareFlights";
	public static String STOPFLIGHTS = "stopFlights";
	public static String CRAFTTYPE = "craftType";
	public static String EXECWEEK = "execWeek";
	public static String QUARTER = "quarter";	
	public static String STARTTIME = "startTime";
	public static String ENDTIME = "endTime"; 
	public static String ROUTEHX ="routeHX";
	public static String SEATNUM = "seatNum";

	@Column(length=6)
	private String planTakeOffTime;
	
	@Column(length=6)
	private String planLandInTime;

	private Date startTime;
	
	private Date endTime;
	
	
	
	@ManyToOne
	@JoinColumn(name="craftType")
	private CraftType craftType;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(length=12)
	private Set<Week> execWeek;
	
	@Enumerated(EnumType.STRING)
	@Column(length=15)
	private Quarter quarter;
	

	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="ScheduleFlight_id")
	private Set<ScheduleShareFlight> shareFlights ;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="ScheduleFlight_id")
	private	Set<ScheduleStopFlight> stopFlights; 
	
	
	
	/**
	 *  座位数
	 */
	private int seatNum;
	
	
	@Override
	public Set<ScheduleStopFlight> getStopFlights() {
		// TODO Auto-generated method stub
		return this.stopFlights;
	}


	@Override
	public Set<ScheduleShareFlight> getShareFlights() {
		// TODO Auto-generated method stub
		return this.shareFlights;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setStopFlights(Set stopFlights) {
		// TODO Auto-generated method stub
		this.stopFlights = stopFlights;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setShareFlights(Set shareFlights) {
		// TODO Auto-generated method stub
		this.shareFlights = shareFlights;
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
	 * @return the execWeek
	 */
	public Set<Week> getExecWeek() {
		return execWeek;
	}


	/**
	 * @param execWeek the execWeek to set
	 */
	public void setExecWeek(Set<Week> execWeek) {
		this.execWeek = execWeek;
	}


	/**
	 * @return the quarter
	 */
	public Quarter getQuarter() {
		return quarter;
	}


	/**
	 * @param quarter the quarter to set
	 */
	public void setQuarter(Quarter quarter) {
		this.quarter = quarter;
	}


	

	/**
	 * @return the planTakeOffTime
	 */
	public String getPlanTakeOffTime() {
		return planTakeOffTime;
	}


	/**
	 * @param planTakeOffTime the planTakeOffTime to set
	 */
	public void setPlanTakeOffTime(String planTakeOffTime) {
		this.planTakeOffTime = planTakeOffTime;
	}


	/**
	 * @return the planLandInTime
	 */
	public String getPlanLandInTime() {
		return planLandInTime;
	}


	/**
	 * @param planLandInTime the planLandInTime to set
	 */
	public void setPlanLandInTime(String planLandInTime) {
		this.planLandInTime = planLandInTime;
	}


	/**
	 * @return the endTime
	 */
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
	 * @return the startTime
	 */
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




	
	

}
