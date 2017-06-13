package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IAirlinesDao;
import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.service.aodb.IAirlinesService;
import com.lzairport.ais.service.impl.Service;


/**
 * 航空公司Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 22/08/24
 * @since JDK 1.6
 *
 */

@Stateless
public class AirlinesService extends Service<Integer, Airlines> implements
		IAirlinesService {

	@EJB
	public void setAirlinesDao(IAirlinesDao airlinesDao){
		setDao(airlinesDao);
	}
}
