package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IPayServiceDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.PayPrice;


/**
 * 付费服务类型的Dao接口的实现类
 * @author ZhangYu
 * @version 0.9a 28/09/14
 * @since JDK 1.6
 *
 */

@Stateless
public class PayServiceDao extends AodbDaoImpl<Integer, PayPrice> implements
		IPayServiceDao {

}
