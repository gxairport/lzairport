package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.ICarrierDao;
import com.lzairport.ais.models.aodb.Carrier;
import com.lzairport.ais.service.aodb.ICarrierService;
import com.lzairport.ais.service.impl.Service;

/**
 * 承运人实体类的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class CarrierService extends Service<Integer, Carrier> implements
		ICarrierService {

	@EJB
	public void setCarrierDao(ICarrierDao carrierDao){
		setDao(carrierDao);
	}
}
