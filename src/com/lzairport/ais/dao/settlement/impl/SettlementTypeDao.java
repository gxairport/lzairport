package com.lzairport.ais.dao.settlement.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.settlement.ISettlementTypeDao;
import com.lzairport.ais.models.settlement.SettlementType;


/**
 * 
 * FileName      SettlementTypeDao.java
 * @Description  TODO 结算类型的Dao实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月7日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class SettlementTypeDao extends AodbDaoImpl<Integer, SettlementType> implements ISettlementTypeDao {


}
