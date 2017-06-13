package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IAreaAttributeDao;
import com.lzairport.ais.models.aodb.AreaAttribute;
import com.lzairport.ais.service.aodb.IAreaAttributeService;
import com.lzairport.ais.service.impl.Service;

/**
 * 区域属性实体类的Service接口的实体类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class AreaAttributeService extends Service<Integer, AreaAttribute>
		implements IAreaAttributeService {

	@EJB
	public void setAreaAttributeDao(IAreaAttributeDao areaAttributeDao){
		setDao(areaAttributeDao);
	}

	@Override
	public AreaAttribute getDomAttribute() {
		// TODO Auto-generated method stub
		return dao.findByFieldSingle(AreaAttribute.CODE, "DOM");
	}

	@Override
	public AreaAttribute getIntAttribute() {
		// TODO Auto-generated method stub
		return dao.findByFieldSingle(AreaAttribute.CODE, "INT");
	}

	@Override
	public AreaAttribute getRegAttribute() {
		// TODO Auto-generated method stub
		return dao.findByFieldSingle(AreaAttribute.CODE, "REG");
	}
}
