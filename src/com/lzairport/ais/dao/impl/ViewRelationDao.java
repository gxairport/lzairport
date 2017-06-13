package com.lzairport.ais.dao.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.IViewRelationDao;
import com.lzairport.ais.models.ViewRelation;

/**
 * 显示关联ViewRelation的Dao层接口实现类
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

@Stateless
public class ViewRelationDao extends AodbDaoImpl<Integer, ViewRelation>  implements IViewRelationDao {

}
