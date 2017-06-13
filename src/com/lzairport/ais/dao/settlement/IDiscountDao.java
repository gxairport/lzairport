package com.lzairport.ais.dao.settlement;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.settlement.Discount;

/**
 * 
 * FileName      IDiscount.java
 * @Description  TODO 结算折扣的Dao接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月26日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月26日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IDiscountDao extends IDao<Integer, Discount> {

}
