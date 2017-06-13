package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.ICraftTypeDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.CraftType;

/**
 * 机型的实体类的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Stateless
public class CraftTypeDao extends AodbDaoImpl<Integer, CraftType> implements
		ICraftTypeDao {


}
