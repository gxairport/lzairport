package com.lzairport.ais.dao.aodb;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.aodb.AreaAttribute;

/**
 * 区域属性实体类的Dao接口
 * @author ZhangYu
 * @version 0.9a 19/08/14
 * @since JDK 1.6
 *
 */

@Local
public interface IAreaAttributeDao extends IDao<Integer, AreaAttribute> {

}
