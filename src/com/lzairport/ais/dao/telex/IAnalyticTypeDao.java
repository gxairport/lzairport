package com.lzairport.ais.dao.telex;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.telex.AnalyticyType;


/**
 * 电报解析类型AnalyticType的Dao层接口
 * @author ZhangYu
 * @version 0.9a 24/06/24
 * @since JDK 1.6
 *
 */

@Local
public interface IAnalyticTypeDao  extends IDao<Integer,AnalyticyType>{

}