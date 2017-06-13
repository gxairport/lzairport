package com.lzairport.ais.service.settlement.price;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;

/**
 * 
 * FileName      DefaultSingleCreater.java
 * @Description  TODO 收费项目生成者单个的基类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年1月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017年1月7日      zhang    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


public abstract class DefaultSingleCreater extends DefaultCreater {


	
	/**
	 * @Description: TODO 获取收费项目的明细项
	 * @param flight 需要收费的航班
	 * @return 收费项目的明细项
	 */
	protected abstract SettlementItem getSetItem(HisFlight flight);
	
	/**
	 * @Description: TODO 获取收费项目的数量
	 * @param flight 需要收费的航班
	 * @param type   收费类型
	 * @return 数量
	 */
	protected abstract Double getNumber(HisFlight flight,SettlementType type);
	
	@Override
	public void create(HisFlight flight, SettlementType type) throws Exception {
		/*
		 * 获取收入的各项计算数据
		 */
		SettlementItem item = getSetItem(flight);
		Double number =getNumber(flight, type);
		Double price = getPrice(flight);
		createSettlement(flight, type, item, number, price);
	}




}
