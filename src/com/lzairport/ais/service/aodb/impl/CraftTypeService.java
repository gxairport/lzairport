package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.ICraftTypeDao;
import com.lzairport.ais.models.aodb.CraftType;
import com.lzairport.ais.service.aodb.ICraftTypeService;
import com.lzairport.ais.service.impl.Service;


/**
 * 机型的实体类的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class CraftTypeService extends Service<Integer, CraftType> implements
		ICraftTypeService {

	@EJB
	public void setCraftTypeDao(ICraftTypeDao craftTypeDao){
		setDao(craftTypeDao);
	}

}
