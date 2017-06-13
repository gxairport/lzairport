package com.lzairport.ais.dao.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.IModelRelationDao;
import com.lzairport.ais.models.ModelRelation;

/**
 * 实体关联ModelRelation的Dao层接口实现类
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Stateless
public class ModelRelationDao extends AodbDaoImpl<Integer, ModelRelation> implements
		IModelRelationDao {


}
