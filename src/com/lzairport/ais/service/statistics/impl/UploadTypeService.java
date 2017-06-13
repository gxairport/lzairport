package com.lzairport.ais.service.statistics.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.dao.statistics.IUploadTypeDao;
import com.lzairport.ais.models.statistics.UploadType;
import com.lzairport.ais.service.impl.Service;
import com.lzairport.ais.service.statistics.IUploadTypeService;


/**
 * 
 * FileName      UploadTypeService.java
 * @Description  统计系统上传记录类型Service的实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年1月12日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class UploadTypeService extends Service<Integer, UploadType> implements IUploadTypeService {
	
	/**
	 * 自定义常量
	 */
	private static int AspLoadTypeCode = 1;
	
	private static int AspPlaneType = 2;
	
	private static int PisDynType = 3;
	

	@EJB
	public void setUploadTypeDao(IUploadTypeDao dao){
		setDao(dao);
	}

	@Override
	public UploadType getAspLoadType() {
		return findByFieldSingle(UploadType.ID, AspLoadTypeCode);
	}

	@Override
	public UploadType getAspPlaneType() {
		return findByFieldSingle(UploadType.ID, AspPlaneType);
	}

	@Override
	public UploadType getPisDynType() {
		return findByFieldSingle(UploadType.ID, PisDynType);
	}

	
	
}
