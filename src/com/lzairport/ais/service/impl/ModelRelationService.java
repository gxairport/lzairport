package com.lzairport.ais.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.IModelRelationDao;
import com.lzairport.ais.models.ModelRelation;
import com.lzairport.ais.service.IModelRelationService;

/**
 * 实体关联ModelRelation的Service层接口实现类
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Stateless
public class ModelRelationService extends Service<Integer, ModelRelation> implements
		IModelRelationService {
	
	@EJB
	public void setModelRelationDao(IModelRelationDao modelRelationDao ){
		setDao(modelRelationDao);
	}

}
