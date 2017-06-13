package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;
import com.lzairport.ais.models.settlement.SettlementCategory;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      ISettlementCategoryService.java
 * @Description  TODO 结算类别的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017年1月12日      zhang    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Remote
public interface ISettlementCategoryService extends IService<Integer, SettlementCategory> {

}
