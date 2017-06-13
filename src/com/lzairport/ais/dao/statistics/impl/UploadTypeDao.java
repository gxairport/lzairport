package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IUploadTypeDao;
import com.lzairport.ais.models.statistics.UploadType;


/**
 * 
 * FileName      UploadTypeDao.java
 * @Description  统计系统上传数据类型Dao的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年1月12日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class UploadTypeDao extends AodbDaoImpl<Integer, UploadType> implements IUploadTypeDao {


}
