package com.lzairport.ais.service.settlement.price;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.Discount;
import com.lzairport.ais.models.settlement.Settlement;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.aodb.IHisFlightService;
import com.lzairport.ais.service.settlement.IDiscountService;
import com.lzairport.ais.service.settlement.ISettlementItemService;
import com.lzairport.ais.service.settlement.ISettlementService;
import com.lzairport.ais.utils.SYS_VARS.DiscountStyle;
import com.lzairport.ais.utils.SYS_VARS.OutIn;

/**
 * 
 * FileName DefaultCreater.java
 * 
 * @Description TODO 收费项目生成者的抽象类
 * @author ZhangYu Company: LZAirport
 * @version V0.9a CreateDate: 2016年11月11日
 * @ModificationHistory Date Author Version Discription
 *<p>
 *---------------------------------------------
 *<p>
 *2016年11月11日 Administrator 1.0 1.0
 *<p>
 *Why & What is modified: <修改原因描述>
 */

public abstract class DefaultCreater implements ISettlementCreater {

	@EJB
	private ISettlementService settlementService;

	@EJB
	protected ISettlementItemService itemService;

	@EJB
	protected IAreaAttributeService areaService;

	@EJB
	protected IHisFlightService flightService;

	@EJB
	private IDiscountService discountService;
	
	private DecimalFormat df2 = new DecimalFormat("#.00");
	
	private DecimalFormat df3 = new DecimalFormat("#.000");

	protected Date startTime;

	protected Date endTime;

	
	
	

	/**
	 * 
	 * @Description: TODO 获取收费项目的单价
	 * @param flight
	 *            需要收费的航班
	 * @return 收费项目的单价
	 */
	protected abstract Double getPrice(HisFlight flight);

	/**
	 * 
	 * @Description: TODO 获取收费项目的折扣
	 * @param flight
	 *            需要收费的航班
	 * @param type
	 *            收费类型
	 * @return 折扣
	 */
	protected Discount getDiscount(HisFlight flight, SettlementItem item) {
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[] { Discount.SETTLEMENTITEM, "=", item });
		conditions.setFetchManyToOne("ALL");
		List<Discount> discounts = discountService.findByConditionAll(conditions);
		Discount result = null;
		if (discounts != null) {
			for (Discount discount : discounts) {
				Boolean found = true;

				if (discount.getAirlines() != null && !discount.getAirlines().equals(flight.getAirlines())) {
					found = false;
				}

				if (discount.getCarrier() != null && !discount.getCarrier().equals(flight.getAircraft().getCarrier())) {
					found = false;
				}

				if (discount.getRouteHX() != null && discount.getRouteHX().length() > 0
						&& !discount.getRouteHX().equals(flight.getRouteHX())) {
					found = false;
				}

				if (discount.getFlightNO() != null && discount.getFlightNO().length() > 0
						&& !discount.getFlightNO().equals(flight.getFlightNO())) {
					found = false;
				}

				if (discount.getTask() != null && !discount.getTask().equals(flight.getTask())) {
					found = false;
				}

				if (found) {
					result = discount;
					break;
				}
			}

		}
		return result;
	}

	/**
	 * 
	 * @Description: TODO 创建一个新的收入结算实体
	 * @param flight
	 * @param item
	 * @param number
	 * @param price
	 * @return
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	protected void createSettlement(HisFlight flight, SettlementType type, SettlementItem item, Double number,
			Double price) throws Exception {

		
		Settlement settlement = null;
		startTime = null;
		endTime   = null;
		Double auomnt = 0.0;
		Discount discount = getDiscount(flight, item);

		if (discount != null) {
			if (discount.getStyle().equals(DiscountStyle.Range)) {
				price = price * discount.getPercentage() / 100;
			} else {
				price = discount.getPrice().doubleValue();
			}
		}

		number = Double.parseDouble(df3.format(number));
		price = Double.parseDouble(df2.format(price));
		/*
		 *  +0.0001是为了四舍五入的时候确保323.575这种确保进位
		 */
		auomnt = Double.parseDouble(df2.format(price * number+0.0001));

		/*
		 *   将金额小数点第二位不为2，4，6，8，0，且计量单位为0.5的 进港航班+0.01
		 *   效果如下：例如 进港通信配载费253.95 计量单位为0.5 进港为266.48 出港为266.47
		 */
		String priceStr = price.toString();
		int pos = priceStr.indexOf(".");
		if (pos != -1 && priceStr.substring(pos + 1).length() == 2 && type.getUnit() == 0.5 && price * 100 % 2 != 0) {
			if (OutIn.Dep.equals(flight.getIsOutIn())){
				auomnt = Double.parseDouble(df2.format(auomnt-0.01));
			}
		}

		if (auomnt > 0 && flight != null) {
			settlement = new Settlement();
			/*
			 * 写入航班属性
			 */
			settlement.setFlightField(flight);
			settlement.setEstimate(!flight.getApprove());
			settlement.setStartTime(startTime);
			settlement.setEndTime(endTime);
			settlement.setCreateTime(new Date());
			
			/*
			 * 写入价格属性
			 */
			if (discount != null && discount.getStyle().equals(DiscountStyle.Range)) {
				settlement.setDiscount(discount.getPercentage());
			} else {
				settlement.setDiscount(100);
			}
			settlement.setPrice(price);
			settlement.setAuomnt(auomnt);
			settlement.setNumber(number);
			settlement.setSettlementItem(item);
			settlement.setSettlementCategory(type.getCategory());
		}

		if (settlement != null) {
			settlementService.add(settlement);
		}

	}

}
