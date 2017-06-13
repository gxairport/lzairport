package com.lzairport.ais.dao.statistics.impl;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.lzairport.ais.dao.statistics.IUploadRecordDao;
import com.lzairport.ais.models.statistics.UploadRecord;


/**
 * 
 * FileName      UploadRecordDao.java
 * @Description  统计上传记录的Dao的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年1月12日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class UploadRecordDao extends AodbDaoImpl<Integer, UploadRecord> implements IUploadRecordDao {

	
}
