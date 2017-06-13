package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.RouteHX;

/**
 * 
 * FileName      IRouteDao.java
 * @Description  航线的Dao接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年4月5日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年4月5日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IRouteHXDao extends IDao<Integer, RouteHX> {

}
