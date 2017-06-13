package com.lzairport.ais.dao.settlement;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.settlement.SettlementCategory;

/**
 * 
 * FileName      ISettlementCategory.java
 * @Description  TODO 结算类别的Dao接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017年1月12日      zhang    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface ISettlementCategoryDao extends IDao<Integer, SettlementCategory> {

}
