package com.lzairport.ais.models.statistics;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.CraftType;
import com.lzairport.ais.utils.SYS_VARS.Week;

/**
 * 
 * FileName      BaseSchedule.java
 * @Description  TODO 基准的季度航线实体类，用于生成预测数据
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年9月10日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年9月10日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Entity
public class BaseSchedule extends DefaultEntity implements Serializable{
	
	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	
	public static String STARTTIME = "startTime";
	public static String ENDTIME = "endTime"; 
	public static String CRAFTTYPE = "craftType";
	public static String EXECWEEK = "execWeek";
	public static String ROUTEHX ="routeHX";
	public static String ATTRIBUTE ="attribute";
	public static String BIGFLIGHTNO ="bigFlightNO";
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(length=20)
	private String routeHX;
	
	private Date startTime;
	
	private Date endTime;
	
	
	@ManyToOne
	@JoinColumn(name="attribute")
	private AreaAttribute attribute; 
	
	@ManyToOne
	@JoinColumn(name="airlines") 
	private Airlines airlines;
	
	@Column(length=16)
	private String bigFlightNO;
	
	@ManyToOne
	@JoinColumn(name="craftType")
	private CraftType craftType;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(length=12)
	private Set<Week> execWeek;
	
	@Transient
	public String airlinesCN;
	
	@Transient
	public String attributeCN;
	
	@Transient
	public String execFrequency;
	
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
		if (id != null){
			this.id = (Integer) id;
		}
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
	 * @return the startTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd ")  
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
	@DateTimeFormat(pattern="yyyy-MM-dd ")  
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
	 * @return the bigFlightNO
	 */
	public String getBigFlightNO() {
		return bigFlightNO;
	}

	/**
	 * @param bigFlightNO the bigFlightNO to set
	 */
	public void setBigFlightNO(String bigFlightNO) {
		this.bigFlightNO = bigFlightNO;
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
	 * @return 返回 航空公司中文名
	 */
	public String getAirlinesCN() {
		return airlines.getCnShortName();
	}

	/**
	 * @return 返回区域中文名
	 */
	public String getAttributeCN() {
		return attribute.getCnShortName();
	}

	/**
	 * @return 返回执行频率
	 */
	public String getExecFrequency() {
		
		String frequency = "";
		
		Week[]  weeks = Week.values();
		
		for(Week week:weeks){
			if (this.execWeek.contains(week)){
				frequency += String.valueOf(week.ordinal()+1);
			}else{
				frequency +=".";
			}
		}
		return frequency;
	}

	/**
	 * @return the craftTypeCode
	 */
	public String getCraftTypeCode() {
		return this.craftType.getCode();
	}

	/**
	 * @param airlinesCN the airlinesCN to set
	 */
	public void setAirlinesCN(String airlinesCN) {
		this.airlinesCN = airlinesCN;
	}

	/**
	 * @param attributeCN the attributeCN to set
	 */
	public void setAttributeCN(String attributeCN) {
		this.attributeCN = attributeCN;
	}

	/**
	 * @param execFrequency the execFrequency to set
	 */
	public void setExecFrequency(String execFrequency) {
		this.execFrequency = execFrequency;
	}

	/**
	 * @param craftTypeCode the craftTypeCode to set
	 */
	public void setCraftTypeCode(String craftTypeCode) {
		this.craftTypeCode = craftTypeCode;
	}
	
	
	

}
