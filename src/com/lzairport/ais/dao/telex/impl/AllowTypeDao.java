package com.lzairport.ais.dao.telex.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.telex.IAllowTypeDao;
import com.lzairport.ais.models.telex.AllowType;

/**
 * 报文所属类型AllowType的Dao层接口的实现类
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 24/06/14
 */

@Stateless
public class AllowTypeDao extends AodbDaoImpl<Integer, AllowType> implements IAllowTypeDao {


}
