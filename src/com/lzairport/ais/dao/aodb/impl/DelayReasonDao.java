package com.lzairport.ais.dao.aodb.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDelayReasonDao;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.models.aodb.DelayReason;

/**
 * 延误原因的实体类的Dao接口的实体类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */
@Stateless
public class DelayReasonDao extends AodbDaoImpl<Integer, DelayReason> implements
		IDelayReasonDao {

	
}
