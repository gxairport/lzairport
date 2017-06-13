package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;
import com.lzairport.ais.models.statistics.BaseRouteMonth;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IBaseRouteMonthService.java
 * @Description  TODO 预测航线每个月的基准数据的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年7月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年7月7日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Remote
public interface IBaseRouteMonthService extends IService<Integer, BaseRouteMonth> {

}
