package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IAreaAttributeDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.AreaAttribute;


/**
 * 区域属性实体类的Dao接口的实体类
 * @author ZhangYu
 * @version 0.9a 19/08/14
 * @since JDK 1.6
 *
 */
@Stateless
public class AreaAttributeDao extends AodbDaoImpl<Integer, AreaAttribute>
		implements IAreaAttributeDao {

	
}
