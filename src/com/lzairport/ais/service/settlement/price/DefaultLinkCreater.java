package com.lzairport.ais.service.settlement.price;

import java.util.List;

import javax.ejb.EJB;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.ServiceLink;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.settlement.IServiceLinkService;

/**
 * 
 * FileName      DefaultMultipleCreater.java
 * @Description  TODO 收费项目生成者多个的基类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年1月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017年1月7日      zhang    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


public abstract class DefaultLinkCreater extends DefaultCreater {
	
	@EJB
	protected IServiceLinkService serviceLinkService;
	
	protected SettlementItem item;


	
	/**
	 * 
	 * @Description: TODO 获取收费项目的数量
	 * @param flight 需要收费的航班
	 * @param type   收费类型
	 * @return 数量
	 */
	protected abstract Double getNumber(SettlementType type,ServiceLink link);

	@Override
	public void create(HisFlight flight, SettlementType type) throws Exception {
		/*
		 * 查询服务环节
		 */
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[]{ServiceLink.FLIGHTNO,"=",flight.getFlightNO(),
				"AND",ServiceLink.ISOUTIN,"=",flight.getIsOutIn(),"AND",ServiceLink.EXECDATE,"=",flight.getExecDate(),
				"AND",ServiceLink.SETTLEMENTTYPE,"=",type});
		conditions.setFetchOneToMany("ALL");
		List<ServiceLink> links = serviceLinkService.findByConditionAll(conditions);
		for(ServiceLink link:links){
			item = link.getSettlementItem();
			Double number = getNumber(type,link);
			Double price = getPrice(flight);
			createSettlement(flight, type, item, number, price);
		}
		
	}
	
	

}
