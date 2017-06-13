package com.lzairport.ais.service.settlement.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.settlement.IDiscountDao;
import com.lzairport.ais.models.settlement.Discount;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.settlement.IDiscountService;

/**
 * 
 * FileName      DiscountService.java
 * @Description  TODO 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月26日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月26日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Stateless
public class DiscountService extends Service<Integer, Discount> implements IDiscountService {
	
	@EJB
	public void setDiscountDao(IDiscountDao dao){
		setDao(dao);
	}
}
