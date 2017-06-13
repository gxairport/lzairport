package com.lzairport.ais.service.aodb.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.aodb.IHisPayingPassengerDao;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisPayingPassenger;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.service.aodb.IHisPayingPassengerService;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.vo.PayingPassengerVO;
import com.lzairport.ais.vo.converter.IConverter;



/**
 * 航班历史付费旅客实体类的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */


@Stateless
public class HisPayingPassengerService extends
		Service<Integer, HisPayingPassenger> implements
		IHisPayingPassengerService {
	
	@EJB(beanName="PayingPassengerConverter")
	private IConverter<HisPayingPassenger, PayingPassengerVO> converter;

	@EJB
	public void setHisPayingPassengerDao(IHisPayingPassengerDao hisPayingPassengerDao){
		setDao(hisPayingPassengerDao);
	}

	@Override
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception {
		
		List<HisPayingPassenger> passengers = findByConditionAll(conditions);
		List<PayingPassengerVO>  vos = new ArrayList<PayingPassengerVO>();
		for (HisPayingPassenger flight:passengers){	
			vos.add(converter.getVOject(flight));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("records", vos);
		result.put("totalCount", findCountByCondition(conditions));
		DataFetchResponseInfo responseInfo = new DataFetchResponseInfo();
		responseInfo.setTotalRows(findCountByCondition(conditions));
		responseInfo.setMatchingObjects(vos);
		return responseInfo;
	}

	@Override
	public HisPayingPassenger update(PayingPassengerVO vo) throws Exception {
		// TODO Auto-generated method stub
		HisPayingPassenger payingPassenger = converter.getEntity(vo);
		return update(payingPassenger);
	}
	
}
