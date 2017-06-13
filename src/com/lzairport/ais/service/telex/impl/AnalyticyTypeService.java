package com.lzairport.ais.service.telex.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.telex.IAnalyticTypeDao;
import com.lzairport.ais.models.telex.AnalyticyType;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.telex.IAnalyticyTypeService;


/**
 * 电报解析状态Service实现类，用以返回定义好的电报解析状态
 * @author ZhangYu
 * @version 0.9a 13/12/14
 * @since JDK 1.6
 *
 */


@Stateless
public class AnalyticyTypeService   extends Service<Integer,AnalyticyType> implements IAnalyticyTypeService {
	
	
	@EJB
	public void setAnalyticyTypeDao(IAnalyticTypeDao analyticyTypeDao){
		setDao(analyticyTypeDao);
	}

	@Override
	public AnalyticyType getUnAnalyticedTelexType() {
		// TODO Auto-generated method stub
		return  dao.findByFieldSingle(AnalyticyType.NAME, "未解析");
	}

	@Override
	public AnalyticyType getAnalyticedTelexType() {
		// TODO Auto-generated method stub
		return  dao.findByFieldSingle(AnalyticyType.NAME, "解析完成");
	}

	@Override
	public AnalyticyType getErrAnalyticyType() {
		// TODO Auto-generated method stub
		return dao.findByFieldSingle(AnalyticyType.NAME, "解析出错");
	}

	

}
