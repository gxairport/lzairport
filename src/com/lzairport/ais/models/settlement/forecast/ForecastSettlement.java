package com.lzairport.ais.models.settlement.forecast;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.models.aodb.FlightTask;
import com.lzairport.ais.models.settlement.SettlementCategory;
import com.lzairport.ais.models.settlement.SettlementItem;

/**
 * FileName      ForecastSettlement.java
 * @Description  TODO 预测收入结算
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月24日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月24日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class ForecastSettlement extends IntIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String MONTH              = "month";
	public static String ROUTEHX            = "routeHX";
	public static String BIGFLIGHTNO        = "bigFlightNO";
	public static String AIRLINES           = "airlines";
	public static String ATTRIBUTE          = "attribute";
	public static String TASK               = "task";
	public static String DISCOUNT           = "discount";
	public static String NUMBER             = "number";
	public static String PRICE              = "price";
	public static String AUOMNT             = "auomnt";
	public static String SETTLEMENTITEM     = "settlementItem";
	public static String SETTLEMENTCATEGORY = "settlementCategory";
	
	
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
	 *  折扣
	 */
	private int discount;
	
	/**
	 *  数量
	 */
	private Double number;
	
	/**
	 *  单价
	 */
	private Double price;
	
	/**
	 *  金额
	 */
	private Double auomnt;
	
	/**
	 * 结算细项
	 */
	@ManyToOne
	private SettlementItem settlementItem;
	
	/**
	 * 结算总类
	 */
	@ManyToOne
	private SettlementCategory settlementCategory;


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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAuomnt() {
		return auomnt;
	}

	public void setAuomnt(Double auomnt) {
		this.auomnt = auomnt;
	}

	public SettlementItem getSettlementItem() {
		return settlementItem;
	}

	public void setSettlementItem(SettlementItem settlementItem) {
		this.settlementItem = settlementItem;
	}

	public SettlementCategory getSettlementCategory() {
		return settlementCategory;
	}

	public void setSettlementCategory(SettlementCategory settlementCategory) {
		this.settlementCategory = settlementCategory;
	}

	public String getBigFlightNO() {
		return bigFlightNO;
	}

	public void setBigFlightNO(String bigFlightNO) {
		this.bigFlightNO = bigFlightNO;
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

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}
	
	

}
