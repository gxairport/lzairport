package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.Airport;


/**
 * 机场Dao接口
 * @author ZhangYu
 * @version 0.9a 22/08/24
 * @since JDK 1.6
 *
 */

@Local
public interface IAirportDao extends IDao<Integer, Airport> {

}
