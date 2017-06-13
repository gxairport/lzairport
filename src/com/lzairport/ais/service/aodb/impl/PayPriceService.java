package com.lzairport.ais.service.aodb.impl;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IPayServiceDao;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.PayPrice;
import com.lzairport.ais.service.aodb.IAirlinesService;
import com.lzairport.ais.service.aodb.IPayPriceService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.utils.SYS_VARS;


/**
 * 付费服务类型的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 04/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class PayPriceService extends Service<Integer, PayPrice> implements
		IPayPriceService {
	
	private static QueryConditions conditions = new QueryConditions();
	
	/**
	 * 默认付费航空公司两字代码
	 */
	private static String PAYDEFAULTCODE = "99";
	
	private Airlines defaultAirlines;
	@EJB
	private IAirlinesService airlinesService;
	
	
	@EJB
	public void setPayServiceDao(IPayServiceDao payServiceDao){
		setDao(payServiceDao);
	}
	
	@PostConstruct
	public void init(){
		
		defaultAirlines = airlinesService.findByFieldSingle(Airlines.CORPTWOCHARCODE, PAYDEFAULTCODE);
	}

	@Override
	public PayPrice getPayPrice(String PayCode, Airlines airlines) {
		
		conditions.setExpresstion(new Object[]{	PayPrice.CODE,"=",PayCode,SYS_VARS.LinkSqlAnd,
				PayPrice.AIRLINES,"=",airlines});
		PayPrice payPrice = findByConditionSingle(conditions);
		
		if (payPrice == null){
			conditions.setExpresstion(new Object[]{	PayPrice.CODE,"=",PayCode,
					SYS_VARS.LinkSqlAnd,PayPrice.AIRLINES,"=",defaultAirlines});
			payPrice = findByConditionSingle(conditions);
		}
		return payPrice;
	}
}
