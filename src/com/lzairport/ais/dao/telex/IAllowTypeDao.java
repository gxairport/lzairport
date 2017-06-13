package com.lzairport.ais.dao.telex;

import javax.ejb.Local;
import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.models.telex.AllowType;

/**
 * 报文所属类型AllowType的Dao层接口
 * @author ZhangYu
 * @since JDK 1.6
 * @version 0.9a 24/06/14
 */

@Local
public interface IAllowTypeDao extends IDao<Integer, AllowType> {

}
