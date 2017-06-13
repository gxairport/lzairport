package com.lzairport.ais.dao.telex.impl;



import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.telex.IAnalyticTypeDao;
import com.lzairport.ais.models.telex.AnalyticyType;

/**
 * 电报解析类型AnalyticType的Dao层接口的实现类
 * @author ZhangYu
 * @version 0.9a 24/06/24
 * @since JDK 1.6
 *
 */

@Stateless
public class AnalyticyTypeDao  extends AodbDaoImpl<Integer, AnalyticyType>implements IAnalyticTypeDao {
	

}
