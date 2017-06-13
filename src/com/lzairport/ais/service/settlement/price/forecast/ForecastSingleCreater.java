package com.lzairport.ais.service.settlement.price.forecast;

import javax.ejb.EJB;

import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.settlement.ISettlementItemService;


/**
 * FileName      SingleForecastCreater.java
 * @Description  TODO 预测收费项目生成者单个的基类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年4月4日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年4月4日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

public abstract class ForecastSingleCreater extends DefaultForecastCreater implements IForecastSettlementCreater {
	
	@EJB
	protected ISettlementItemService itemService;

	@EJB
	protected IAreaAttributeService areaService;
	
	/**
	 * 
	 * @Description: TODO 获取预测收费项目的单价
	 * @param Base  需要收费的航班
	 * @return 收费项目的单价
	 */
	protected abstract Double getPrice(ForecastBase base);
	
	/**
	 * @Description: TODO 获取收费项目的明细项
	 * @param flight 需要收费的航班
	 * @return 收费项目的明细项
	 */
	protected abstract SettlementItem getSetItem(ForecastBase base);
	
	/**
	 * @Description: TODO 获取收费项目的数量
	 * @param flight 需要收费的航班
	 * @param type   收费类型
	 * @return 数量
	 */
	protected abstract Double getNumber(ForecastBase base);

	@Override
	public void create(ForecastBase base, SettlementType type) throws Exception {
		
		SettlementItem item = getSetItem(base);
		Double number =getNumber(base);
		Double price = getPrice(base);
		createForecastSettlement(base, type, item, number, price);
	}


}
