package com.lzairport.ais.models.statistics.forecast;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.FlightTask;

/**
 * FileName      ForecastMonthFlight.java
 * @Description  TODO 预测航班每月的实体类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月15日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月15日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class ForecastMonthFlight extends IntIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String YEAR          = "year";
	public static String MONTH         = "month";
	public static String BIGFLIGHTNO   = "bigFlightNO";
	public static String CRAFTCODE     = "craftCode";
	public static String AIRLINES      = "airlines";
	public static String TASK          = "task";
	public static String ATTRIBUTE     = "attribute";
	public static String COUNTFLT      = "countFlt";
	public static String AVGSEAT       = "avgSeat";
	public static String INRATE        = "inRate";
	public static String OUTRATE       = "outRate";
	public static String LOCALRATE     = "localRate";
	public static String INCARGO       = "inCargo";
	public static String OUTCARGO      = "outCargo";
	public static String INPAX         = "inPax";
	public static String OUTPAX        = "outPax";
	public static String LOCALPAX      = "localPax";
	
	
	/**
	 * 预测年份
	 */
	@Column(length=4)
	private String year;
	
	/**
	 *  月份
	 */
	private int month;
	
	/**
	 * 大航班号
	 */
	private String bigFlightNO;

	/**
	 * 机型
	 */
	@Column(length=15)
	private String craftCode;
	
	/**
	 * 航空公司
	 */
	@ManyToOne
	private Airlines airlines;
	
	/**
	 * 任务类型
	 */
	@ManyToOne
	private FlightTask task;
	
	/**
	 *  区域
	 */
	@ManyToOne
	private AreaAttribute attribute;
	
	/**
	 * 架次
	 */
	private int countFlt;
	
	/**
	 *   座位数
	 */
	private int avgSeat;
	
	/**
	 * 进港客座率
	 */
	private double inRate;
	
	/**
	 *  出港客座率
	 */
	private double outRate;
	
	/**
	 * 本地出港旅客座率
	 */
	private double localRate;
	
	/**
	 * 进港货物
	 */
	private int inCargo;
	
	/**
	 * 出港货物
	 */
	private int outCargo;
	
	/**
	 * 进港人数
	 */
	private int inPax;
	
	/**
	 * 出港人数
	 */
	private int outPax;

	/**
	 * 本地出港人数
	 */
	private int localPax;
	
	


	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getBigFlightNO() {
		return bigFlightNO;
	}

	public void setBigFlightNO(String bigFlightNO) {
		this.bigFlightNO = bigFlightNO;
	}

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}

	public Airlines getAirlines() {
		return airlines;
	}

	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

	public FlightTask getTask() {
		return task;
	}

	public void setTask(FlightTask task) {
		this.task = task;
	}

	public AreaAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(AreaAttribute attribute) {
		this.attribute = attribute;
	}

	public int getCountFlt() {
		return countFlt;
	}

	public void setCountFlt(int countFlt) {
		this.countFlt = countFlt;
	}

	public int getAvgSeat() {
		return avgSeat;
	}

	public void setAvgSeat(int avgSeat) {
		this.avgSeat = avgSeat;
	}

	public double getInRate() {
		return inRate;
	}

	public void setInRate(double inRate) {
		this.inRate = inRate;
	}

	public double getOutRate() {
		return outRate;
	}

	public void setOutRate(double outRate) {
		this.outRate = outRate;
	}

	public double getLocalRate() {
		return localRate;
	}

	public void setLocalRate(double localRate) {
		this.localRate = localRate;
	}

	public int getInCargo() {
		return inCargo;
	}

	public void setInCargo(int inCargo) {
		this.inCargo = inCargo;
	}

	public int getOutCargo() {
		return outCargo;
	}

	public void setOutCargo(int outCargo) {
		this.outCargo = outCargo;
	}

	public int getInPax() {
		return inPax;
	}

	public void setInPax(int inPax) {
		this.inPax = inPax;
	}

	public int getOutPax() {
		return outPax;
	}

	public void setOutPax(int outPax) {
		this.outPax = outPax;
	}

	public int getLocalPax() {
		return localPax;
	}

	public void setLocalPax(int localPax) {
		this.localPax = localPax;
	}
	
	

}
