package com.lzairport.ais.service.telex.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.telex.IAllowTypeDao;
import com.lzairport.ais.models.telex.AllowType;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.telex.IAllowTypeService;

/**
 * 报文所属类型AllowType的Service层接口的实现类
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 29/04/15
 */

@Stateless
public class AllowTypeService extends Service<Integer, AllowType> implements IAllowTypeService{

	@EJB
	public void setAllowTypeDao(IAllowTypeDao allowTypeDao){
		setDao(allowTypeDao);
	}
}
