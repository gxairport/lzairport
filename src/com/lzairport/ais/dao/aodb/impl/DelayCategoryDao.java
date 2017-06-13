package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDelayCategoryDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.DelayCategory;

/**
 * 延误类别的实体类的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 19/08/14
 * @since JDK 1.6
 *
 */


@Stateless
public class DelayCategoryDao extends AodbDaoImpl<Integer, DelayCategory>
		implements IDelayCategoryDao {

	
}
