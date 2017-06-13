package com.lzairport.ais.models.statistics;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 
 * FileName      ForecastRoute.java
 * @Description  TODO 预测航线的实体
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月1日 
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2016年7月1日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Entity
public class ForecastRoute extends DefaultEntity implements Serializable {
	
	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 1L;
	
	

	
	/**
	 * 各个数据字段名,用来直接点用字段名
	 */
	public static String ID = "id";
	public static String ROUTEHX = "routeHX";
	public static String IPAX = "iPax";
	public static String OPAX = "oPax";
	public static String PAX = "pax";
	public static String IATE = "iRate";
	public static String ORATE = "oRate";
	public static String RATE =  "rate";
	public static String FLTCOUNT = "countFLT";
	public static String FORECASTMONTHS = "forecastMonths";
	public static String COMPAREPAX = "comparePax";
	public static String COMPAREFLTCOUNT = "compareCountFLT";
	public static String ACTUALPAX = "actualPax";
	public static String ACTUALRATE ="actualRate";
	public static String ACTUALFLTCOUNT ="actualCountFLT";
	public static String ACTUALDIFFRATE ="actualDiffRate ";
	public static String ACTUALDIFFCOUNTFLT ="actualDiffCountFLT";
	public static String ACTUALDIFFPAX = "actualDiffPax"; 
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String routeHX;
	private int iPax;
	private int oPax;
	private int pax;
	private double iRate;
	private double oRate;
	private double rate;
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
	
	private double actualDiffRate;
	private int actualDiffCountFLT;
	private int actualDiffPax; 
	
	

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="forecastRoute_id") 
	private Set<ForecastRouteMonth> fmRoutes;
	

	
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
	 * @return the fmRoutes
	 */
	public Set<ForecastRouteMonth> getFmRoutes() {
		return fmRoutes;
	}

	/**
	 * @param fmRoutes the fmRoutes to set
	 */
	public void setFmRoutes(Set<ForecastRouteMonth> fmRoutes) {
		this.fmRoutes = fmRoutes;
	}

	
	
	
	/**
	 * @return the actualDiffRate
	 */
	public double getActualDiffRate() {
		return actualDiffRate;
	}

	/**
	 * @param actualDiffRate the actualDiffRate to set
	 */
	public void setActualDiffRate(double actualDiffRate) {
		this.actualDiffRate = actualDiffRate;
	}

	/**
	 * @return the actualDiffCountFLT
	 */
	public int getActualDiffCountFLT() {
		return actualDiffCountFLT;
	}

	/**
	 * @param actualDiffCountFLT the actualDiffCountFLT to set
	 */
	public void setActualDiffCountFLT(int actualDiffCountFLT) {
		this.actualDiffCountFLT = actualDiffCountFLT;
	}

	/**
	 * @return the actualDiffPax
	 */
	public int getActualDiffPax() {
		return actualDiffPax;
	}

	/**
	 * @param actualDiffPax the actualDiffPax to set
	 */
	public void setActualDiffPax(int actualDiffPax) {
		this.actualDiffPax = actualDiffPax;
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

}
