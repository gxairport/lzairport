package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisPayingPassenger;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.vo.PayingPassengerVO;


/**
 * 航班历史付费旅客的Service接口
 * @author ZhangYu
 * @version 0.9a 01/05/15
 * @since JDK 1.6
 *
 */

@Remote
public interface IHisPayingPassengerService extends
		IService<Integer, HisPayingPassenger> {
	/**
	 * 
	 * @Description: 返回EXTJS页面所需要的Response对象
	 * @param conditions 条件
	 * @return DataFetchResponseInfo对象 
	 * @throws Exception
	 */
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception;
	
	public HisPayingPassenger update(PayingPassengerVO vo) throws Exception;
}
