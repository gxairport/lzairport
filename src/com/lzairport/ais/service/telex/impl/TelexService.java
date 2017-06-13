package com.lzairport.ais.service.telex.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.dao.telex.ITelexDao;
import com.lzairport.ais.models.telex.TelexBO;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.telex.ITelexService;
import com.lzairport.ais.utils.DateTimeUtil;


/**
 * 电报TelexBo的Service层接口的实现类
 * @author ZhangYu
 * @version 0.9a 49/04/15
 * @since JDK 1.6
 *
 */

@Stateless
public class TelexService extends Service<Integer, TelexBO> implements ITelexService {
	
	/**
	 * 过期日期7天
	 */
	private static int EXPIREDAY = -7;

	@EJB
	public void setTelexDao(ITelexDao telexDao){
		setDao(telexDao);
	}

	@Override
	public void clearExpireData() {
		// TODO Auto-generated method stub
		QueryConditions conditions = new QueryConditions();
		conditions.setExpresstion(new Object[] {TelexBO.TIME,"<=",
				DateTimeUtil.addToDay(new Date(), EXPIREDAY)});		
		List<TelexBO> telexs = findByConditionAll(conditions);
		for(TelexBO telex:telexs){
			remove(telex);
		}
		
	}
}
