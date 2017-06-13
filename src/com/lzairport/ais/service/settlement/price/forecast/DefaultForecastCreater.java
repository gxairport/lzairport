package com.lzairport.ais.service.settlement.price.forecast;

import java.text.DecimalFormat;
import java.util.List;
import javax.ejb.EJB;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.settlement.Discount;
import com.lzairport.ais.models.settlement.SettlementItem;
import com.lzairport.ais.models.settlement.SettlementType;
import com.lzairport.ais.models.settlement.forecast.ForecastBase;
import com.lzairport.ais.models.settlement.forecast.ForecastSettlement;
import com.lzairport.ais.service.settlement.IDiscountService;
import com.lzairport.ais.service.settlement.forecast.IForecastSettlementService;
import com.lzairport.ais.utils.AisBeanUtils;
import com.lzairport.ais.utils.SYS_VARS.DiscountStyle;

public abstract class DefaultForecastCreater {
	
	@EJB
	private IForecastSettlementService forecastSettlementService;

	@EJB
	private IDiscountService discountService;
	
	private DecimalFormat df2 = new DecimalFormat("#.00");
	
	private DecimalFormat df3 = new DecimalFormat("#.000");

	/**
	 * 
	 * @Description: TODO 获取收费项目的折扣
	 * @param flight
	 *            需要收费的航班
	 * @param type
	 *            收费类型
	 * @return 折扣
	 */
	protected Discount getDiscount(ForecastBase base, SettlementItem item) {
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[] { Discount.SETTLEMENTITEM, "=", item });
		conditions.setFetchManyToOne("ALL");
		List<Discount> discounts = discountService.findByConditionAll(conditions);
		Discount result = null;
		if (discounts != null) {
			for (Discount discount : discounts) {
				Boolean found = true;

				if (discount.getAirlines() != null && !discount.getAirlines().equals(base.getAirlines())) {
					found = false;
				}


				if (discount.getRouteHX() != null && discount.getRouteHX().length() > 0
						&& !discount.getRouteHX().equals(base.getRouteHX())) {
					found = false;
				}

				if (discount.getFlightNO() != null && base.getBigFlightNO() != null
						&& base.getBigFlightNO().indexOf(discount.getFlightNO()) > -1) {
					found = false;
				}

				if (discount.getTask() != null && !discount.getTask().equals(base.getTask())) {
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
	 * @param base         预测航线基础数据
	 * @param type         收费项目类型
	 * @param item         收费项目细项
	 * @param price        收费单价
	 * @throws Exception
	 */
	protected void createForecastSettlement(ForecastBase base,SettlementType type, SettlementItem item,Double number, 
			Double price) throws Exception {
		/*
		 * 初始化各项属性
		 */
		ForecastSettlement forecastSettlement = null;
		Double auomnt = 0.0;
		number = Double.parseDouble(df3.format(number));
		price = Double.parseDouble(df2.format(price));
		
		/* 
		 *     根据折扣计算单价   
		 */
		Discount discount = getDiscount(base,item);
		if (discount != null) {
			if (discount.getStyle().equals(DiscountStyle.Range)) {
				price = price * discount.getPercentage() / 100;
			} else {
				price = discount.getPrice().doubleValue();
			}
		}
		/*
		 *  计算收入金额
		 */
		auomnt = Double.parseDouble(df2.format(price * number));
		
		if (auomnt > 0 && base != null) {
			forecastSettlement = new ForecastSettlement();
			/*
			 *  复制相同的属性
			 */
			AisBeanUtils.copyProperties(forecastSettlement, base);
			/*
			 * 设置价格属性
			 */
			forecastSettlement.setPrice(price);
			forecastSettlement.setAuomnt(auomnt);
			forecastSettlement.setNumber(number);
			forecastSettlement.setSettlementItem(item);
			forecastSettlement.setSettlementCategory(type.getCategory());
		}
		
		/*
		 *  添加一条航线的一个收入预测
		 */
		if (forecastSettlement != null){
			forecastSettlementService.add(forecastSettlement);
		}
	}

}
