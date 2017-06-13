package com.lzairport.ais.service.settlement;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.settlement.ServiceLink;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IServiceLinkService.java
 * @Description  TODO 服务收费的Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年11月7日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年11月7日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IServiceLinkService extends IService<Integer, ServiceLink> {
	
	/**
	 * @Description: TODO创建 一个航班的默认服务性收费
	 * @param hisFlight
	 * @throws Exception 
	 */
	public void createFlightDefaultLinks(HisFlight flight) throws Exception;

}
