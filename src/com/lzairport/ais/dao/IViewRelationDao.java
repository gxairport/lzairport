package com.lzairport.ais.dao;

import javax.ejb.Local;
import com.lzairport.ais.models.ViewRelation;

/**
 * 显示关联ViewRelation的Dao层接口
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Local
public interface IViewRelationDao extends IDao<Integer, ViewRelation> {

}
