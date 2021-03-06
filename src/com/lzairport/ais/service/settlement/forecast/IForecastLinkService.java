/**
 * 
 */
package com.lzairport.ais.service.settlement.forecast;

import javax.ejb.Remote;

import com.lzairport.ais.models.settlement.forecast.ForecastLink;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IForecastLink.java
 * @Description  TODO 各预测服务环节的Service接口 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年3月24日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年3月24日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IForecastLinkService extends IService<Integer, ForecastLink> {

}
