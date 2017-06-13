package com.lzairport.ais.service.telex.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.telex.ITelexTypeDao;
import com.lzairport.ais.models.telex.TelexType;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.telex.ITelexTypeService;

/**
 * 电报类型TelexType的Service层接口的实现类
 * @author ZhangYu
 * @version 0.9a 29/04/15
 * @since JDK 1.6
 *
 */

@Stateless
public class TelexTypeService extends Service<Integer, TelexType> implements
		ITelexTypeService {

	@EJB
	public void setTelexTypeDao(ITelexTypeDao telexTypeDao){
		setDao(telexTypeDao);
	}

	@Override
	public TelexType getTelexDefaultType() {

		return findByFieldSingle(TelexType.NAME, "不处理报文");
	}
}
