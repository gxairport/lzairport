package com.lzairport.ais.models.statistics.forecast;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.aodb.AreaAttribute;

/**
 * FileName      BenchmarkFlight.java
 * @Description  TODO 预测航班的基准数据实体类 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月15日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月15日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class BenchmarkRouteHX extends IntIdEntity {
	
	
	private static final long serialVersionUID = 1L;
	
	public static String ROUTEHX   = "routeHX";
	public static String BMONTHS  = "bMonths";
	public static String TRANSFER  = "transfer";
	public static String ATTRIBUTE = "attribute";
	
	
	/**
	 * 航线
	 */
	@Column(length=20)
	private String routeHX;
	
	/**
	 *  是否是中转航班
	 */
	private boolean transfer;

	@ManyToOne
	private AreaAttribute attribute; 
	
	
	
	/**
	 * 基准航线月数据
	 */
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="benchmarkRouteHX_id")
	private Set<BenchmarkMonth> bMonths;
	



	public String getRouteHX() {
		return routeHX;
	}

	public void setRouteHX(String routeHX) {
		this.routeHX = routeHX;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	public AreaAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(AreaAttribute attribute) {
		this.attribute = attribute;
	}

	public Set<BenchmarkMonth> getbMonths() {
		return bMonths;
	}

	public void setbMonths(Set<BenchmarkMonth> bMonths) {
		this.bMonths = bMonths;
	}



}
