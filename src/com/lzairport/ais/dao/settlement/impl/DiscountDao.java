package com.lzairport.ais.dao.settlement.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.settlement.IDiscountDao;
import com.lzairport.ais.models.settlement.Discount;



/**
 * 
 * FileName      DiscountDao.java
 * @Description  TODO 结算折扣Dao实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月26日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月26日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class DiscountDao extends AodbDaoImpl<Integer, Discount> implements IDiscountDao {

	
}
