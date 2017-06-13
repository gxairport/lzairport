package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.Aircraft;

/**
 * 飞机的Dao接口
 * @author ZhangYu
 * @version 0.9a 22/08/24
 * @since JDK 1.6
 *
 */

@Local
public interface IAircraftDao extends IDao<Integer, Aircraft> {

}
