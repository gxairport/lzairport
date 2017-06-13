package com.lzairport.ais.models.settlement.forecast;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.lzairport.ais.models.IntIdEntity;
import com.lzairport.ais.models.settlement.SettlementItem;


/**
 * FileName      ForecastLink.java
 * @Description  TODO  各预测服务环节 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月24日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月24日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class ForecastLink extends IntIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static String ITEM       = "item";
	public static String PROPORTION = "proportion";
	
	/**
	 * 项目名称
	 */
	@ManyToOne
	private SettlementItem item;
	/**
	 * 比例
	 */
	private int proportion; 
	

	public SettlementItem getItem() {
		return item;
	}

	public void setItem(SettlementItem item) {
		this.item = item;
	}

	public int getProportion() {
		return proportion;
	}

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}
	
	

}
