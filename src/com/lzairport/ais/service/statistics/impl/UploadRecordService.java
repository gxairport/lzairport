package com.lzairport.ais.service.statistics.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.statistics.IUploadRecordDao;
import com.lzairport.ais.models.statistics.UploadRecord;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IUploadRecordService;

/**
 * 
 * FileName      UploadRecordService.java
 * @Description  统计系统上传记录的Service的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年1月12日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class UploadRecordService extends Service<Integer, UploadRecord> implements IUploadRecordService {

	@EJB
	public void setUploadRecordDao(IUploadRecordDao dao){
		setDao(dao);
	}
	

}
