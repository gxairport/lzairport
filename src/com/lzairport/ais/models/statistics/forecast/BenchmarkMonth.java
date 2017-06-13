package com.lzairport.ais.models.statistics.forecast;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.aodb.Airlines;

/**
 * 
 * 
 * FileName      BenchmarkMonth.java
 * @Description  TODO 基准数据月的实体类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月15日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月15日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class BenchmarkMonth extends IntIdEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static String YEAR              = "year";
	public static String MONTH             = "month";
	public static String AIRLINES          = "airlines"; 
	public static String CRAFTCODE         = "craftCode"; 
	public static String COUNTFLT          = "countFlt"; 
	public static String AVGSEAT           = "avgSeat"; 
	public static String INRATE            = "inRate";
	public static String OUTRATE           = "outRate";
	public static String LOCALRATE         = "localRate";
	public static String AVGINCARGO        = "avgInCargo";
	public static String AVGOUTCARGO       = "avgOutCargo";
	
	/**
	 *  各数据字段
	 */
	
	
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
	 * 航空公司
	 */
	@ManyToOne
	private Airlines airlines;
	
	/**
	 * 预测机型
	 */
	@Column(length=4)
	private String craftCode;
	
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
	 *   平均进港货物
	 */
	private int avgInCargo;
	
	/**
	 * 平均出港货物
	 */
	private int avgOutCargo;

	
	/**
	 *  用于前端展示的字段
	 */
	@Transient
	private String airlinesName;

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

	public Airlines getAirlines() {
		return airlines;
	}

	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

	

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
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

	public int getAvgInCargo() {
		return avgInCargo;
	}

	public void setAvgInCargo(int avgInCargo) {
		this.avgInCargo = avgInCargo;
	}

	public int getAvgOutCargo() {
		return avgOutCargo;
	}

	public void setAvgOutCargo(int avgOutCargo) {
		this.avgOutCargo = avgOutCargo;
	}

	public String getAirlinesName() {
		if (this.getAirlines() != null){
			return this.getAirlines().getCnShortName();
		}else{
			return null;
		}
	}

	
	
	
}
