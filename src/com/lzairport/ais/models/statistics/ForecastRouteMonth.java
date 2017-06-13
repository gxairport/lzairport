package com.lzairport.ais.models.statistics;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.lzairport.ais.models.DefaultEntity;

/**
 * 
 * FileName      ForecastRouteMonth.java
 * @Description  TODO 预测航线月的实体类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月1日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月1日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class ForecastRouteMonth extends DefaultEntity implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 各个数据字段名,用来直接点用字段名
	 */
	public static String ID = "id";
	public static String MONTH = "month";
	public static String IPAX = "iPax";
	public static String OPAX = "oPax";
	public static String PAX = "pax";
	public static String IRATE = "iRate";
	public static String ORATE = "oRate";
	public static String RATE = "rate";
	public static String FLTCOUNT = "countFLT";
	public static String ADDFLTCOUNT = "addCountFLT";
	public static String ADDPAX = "addPax";
	public static String SHARE = "share";
	public static String SEAT = "seat";
	public static String COMPAREPAX = "comparePax";
	public static String COMPAREFLTCOUNT = "compareCountFLT";
	public static String ACTUALIPAX = "actualiPax";
	public static String ACTUALOPAX = "actualoPax";
	public static String ACTUALPAX = "actualPax";
	public static String ACTUALIRATE = "actualiRate";
	public static String ACTUALORATE = "actualoRate";
	public static String ACTUALRATE ="actualRate";
	public static String ACTUALFLTCOUNT ="actualCountFLT";
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int month;
	private int iPax;
	private int oPax;
	private int pax;
	private double iRate;
	private double oRate;
	private double rate;
	private int seat;
	private double share;
	private int countFLT;
	private int comparePax;
	private int compareCountFLT;
	private int actualiPax;
	private int actualoPax;
	private int actualPax;
	private double actualiRate;
	private double actualoRate;
	private double actualRate;
	private int actualCountFLT;
	
	

	@Override
	public Object getId() {
		return this.id;
	}

	@Override
	public void setId(Object id) {
		this.id = (Integer) id;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the iPax
	 */
	public int getiPax() {
		return iPax;
	}

	/**
	 * @param iPax the iPax to set
	 */
	public void setiPax(int iPax) {
		this.iPax = iPax;
	}

	/**
	 * @return the oPax
	 */
	public int getoPax() {
		return oPax;
	}

	/**
	 * @param oPax the oPax to set
	 */
	public void setoPax(int oPax) {
		this.oPax = oPax;
	}

	/**
	 * @return the pax
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * @param pax the pax to set
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * @return the iRate
	 */
	public double getiRate() {
		return iRate;
	}

	/**
	 * @param iRate the iRate to set
	 */
	public void setiRate(double iRate) {
		this.iRate = iRate;
	}

	/**
	 * @return the oRate
	 */
	public double getoRate() {
		return oRate;
	}

	/**
	 * @param oRate the oRate to set
	 */
	public void setoRate(double oRate) {
		this.oRate = oRate;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the seat
	 */
	public int getSeat() {
		return seat;
	}

	/**
	 * @param seat the seat to set
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}

	/**
	 * @return the share
	 */
	public double getShare() {
		return share;
	}

	/**
	 * @param share the share to set
	 */
	public void setShare(double share) {
		this.share = share;
	}

	/**
	 * @return the countFLT
	 */
	public int getCountFLT() {
		return countFLT;
	}

	/**
	 * @param countFLT the countFLT to set
	 */
	public void setCountFLT(int countFLT) {
		this.countFLT = countFLT;
	}

	/**
	 * @return the comparePax
	 */
	public int getComparePax() {
		return comparePax;
	}

	/**
	 * @param comparePax the comparePax to set
	 */
	public void setComparePax(int comparePax) {
		this.comparePax = comparePax;
	}

	/**
	 * @return the compareCountFLT
	 */
	public int getCompareCountFLT() {
		return compareCountFLT;
	}

	/**
	 * @param compareCountFLT the compareCountFLT to set
	 */
	public void setCompareCountFLT(int compareCountFLT) {
		this.compareCountFLT = compareCountFLT;
	}

	/**
	 * @return the actualiPax
	 */
	public int getActualiPax() {
		return actualiPax;
	}

	/**
	 * @param actualiPax the actualiPax to set
	 */
	public void setActualiPax(int actualiPax) {
		this.actualiPax = actualiPax;
	}

	/**
	 * @return the actualoPax
	 */
	public int getActualoPax() {
		return actualoPax;
	}

	/**
	 * @param actualoPax the actualoPax to set
	 */
	public void setActualoPax(int actualoPax) {
		this.actualoPax = actualoPax;
	}

	/**
	 * @return the actualPax
	 */
	public int getActualPax() {
		return actualPax;
	}

	/**
	 * @param actualPax the actualPax to set
	 */
	public void setActualPax(int actualPax) {
		this.actualPax = actualPax;
	}

	/**
	 * @return the actualiRate
	 */
	public double getActualiRate() {
		return actualiRate;
	}

	/**
	 * @param actualiRate the actualiRate to set
	 */
	public void setActualiRate(double actualiRate) {
		this.actualiRate = actualiRate;
	}

	/**
	 * @return the actualoRate
	 */
	public double getActualoRate() {
		return actualoRate;
	}

	/**
	 * @param actualoRate the actualoRate to set
	 */
	public void setActualoRate(double actualoRate) {
		this.actualoRate = actualoRate;
	}

	/**
	 * @return the actualRate
	 */
	public double getActualRate() {
		return actualRate;
	}

	/**
	 * @param actualRate the actualRate to set
	 */
	public void setActualRate(double actualRate) {
		this.actualRate = actualRate;
	}

	/**
	 * @return the actualCountFLT
	 */
	public int getActualCountFLT() {
		return actualCountFLT;
	}

	/**
	 * @param actualCountFLT the actualCountFLT to set
	 */
	public void setActualCountFLT(int actualCountFLT) {
		this.actualCountFLT = actualCountFLT;
	}
	
	/**
	 * 
	 * @Description: TODO 自动计算计算的字段，如平均客座率，增加的人数，架次
	 * @param month
	 */
	public void autoCalc() {
		DecimalFormat df = new DecimalFormat("#.0");
		setPax(getiPax()+getoPax());
		double rate = Double.parseDouble(df.format((getiRate()+getoRate())/2));
		setRate(rate);
	}


	
}
