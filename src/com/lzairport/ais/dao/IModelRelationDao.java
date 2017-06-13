package com.lzairport.ais.dao;

import javax.ejb.Local;

import com.lzairport.ais.models.ModelRelation;

/**
 * 关联实体ModelRelation的Dao层接口
 * @author ZhangYu
 * @version 0.9a 27/06/24
 * @since JDK 1.6
 *
 */

@Local
public interface IModelRelationDao extends IDao<Integer, ModelRelation> {

}
