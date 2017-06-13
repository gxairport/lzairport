package com.lzairport.ais.models.settlement.forecast;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.FlightTask;

/**
 * FileName      ForecastBase.java
 * @Description  TODO 预测数据基础数据
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月23日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月23日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class ForecastBase extends IntIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String MONTH        = "month";
	public static String ROUTEHX      = "routeHX";
	public static String BIGFLIGHTNO  = "bigFlightNO";
	public static String AIRLINES     = "airlines"; 
	public static String COUNTFLT     = "countFlt";
	public static String PAX          = "pax";
	public static String CARGOMAIL    = "cargoMail";
	public static String CRAFTCODE    = "craftCode";
	public static String PAYLOAD      = "payLoad";
	public static String WEIGHT       = "weight";
	public static String SEAT         = "seat";
	public static String TASK         = "task";
	public static String ATTRIBUTE    = "attribute";
	public static String AFTERRUN     = "afterRun";
	public static String BEFORERUN    = "beforeRun";
	public static String LINKS        = "links";
	
	
	/**
	 * 月份
	 */
	private int month;
	
	/**
	 * 航线
	 */
	private String routeHX;
	
	/**
	 * 大航班号
	 */
	private String bigFlightNO;
	
	
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
	 *  架次 
	 */
	private int countFlt;
	
	/**
	 *  出港旅客数量
	 */
	private int pax;
	
	/**
	 * 货邮数量
	 */
	private int cargoMail;
	
	/**
	 * 停场比例
	 */
	private int parkProportion;
	
	/**
	 * 机型
	 */
	@Column(length=15)
	private String craftCode;

	/**
	 *  业载
	 */
    private int payLoad;
	
    /**
     * 全重
     */
    private int weight;
    
    /**
     * 座位
     */
    private int seat;
	
	/**
	 *   航后比例
	 */
	private boolean afterRun;
	
	/**
	 *   航前比例
	 */
	private boolean beforeRun;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="forecastBase_id")
	private Set<ForecastLink> links;


	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getRouteHX() {
		return routeHX;
	}

	public void setRouteHX(String routeHX) {
		this.routeHX = routeHX;
	}

	public Integer getCountFlt() {
		return countFlt;
	}

	public void setCountFlt(int countFlt) {
		this.countFlt = countFlt;
	}

	public Integer getPax() {
		return pax;
	}

	public void setPax(int pax) {
		this.pax = pax;
	}

	public Integer getCargoMail() {
		return cargoMail;
	}

	public void setCargoMail(int cargoMail) {
		this.cargoMail = cargoMail;
	}

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}

	public int getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(int payLoad) {
		this.payLoad = payLoad;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	public Integer getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}



	public int getParkProportion() {
		return parkProportion;
	}

	public void setParkProportion(int parkProportion) {
		this.parkProportion = parkProportion;
	}

	public boolean isAfterRun() {
		return afterRun;
	}

	public void setAfterRun(boolean afterRun) {
		this.afterRun = afterRun;
	}

	public boolean isBeforeRun() {
		return beforeRun;
	}

	public void setBeforeRun(boolean beforeRun) {
		this.beforeRun = beforeRun;
	}

	public Set<ForecastLink> getLinks() {
		return links;
	}

	public void setLinks(Set<ForecastLink> links) {
		this.links = links;
	}

	public String getBigFlightNO() {
		return bigFlightNO;
	}

	public void setBigFlightNO(String bigFlightNO) {
		this.bigFlightNO = bigFlightNO;
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

	public Airlines getAirlines() {
		return airlines;
	}

	public void setAirlines(Airlines airlines) {
		this.airlines = airlines;
	}

}
