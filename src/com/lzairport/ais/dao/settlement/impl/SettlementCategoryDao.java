package com.lzairport.ais.dao.settlement.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.settlement.ISettlementCategoryDao;
import com.lzairport.ais.models.settlement.SettlementCategory;



/**
 * 
 * FileName      SettlementCategoryDao.java
 * @Description  TODO 结算类别的Dao接口实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017年1月12日      zhang    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class SettlementCategoryDao extends AodbDaoImpl<Integer, SettlementCategory> implements ISettlementCategoryDao {
	

}
