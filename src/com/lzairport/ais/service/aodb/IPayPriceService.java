package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.PayPrice;
import com.lzairport.ais.service.IService;


/**
 * 付费服务类型的Service接口
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IPayPriceService extends IService<Integer, PayPrice> {
	
	
	/**
	 * 根据Pay代码和所属航空公司查找价格对象，如果没有找到用默认价格
	 * @param PayCode 付费代码 有FF1,FF2,FC
	 * @param airlines 
	 * @return 价格对象
	 */
	public PayPrice getPayPrice(String PayCode,Airlines airlines);

}
