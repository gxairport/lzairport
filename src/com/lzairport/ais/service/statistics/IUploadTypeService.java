package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.UploadType;
import com.lzairport.ais.service.IService;

/**
 * 
 * FileName      IUploadTypeService.java
 * @Description  统计系统上传记录类型Service接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年1月12日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IUploadTypeService extends IService<Integer, UploadType> {
	
	/**
	 * 
	 * @Description: 返回民航统计系统载量类型
	 * @return
	 */
	public UploadType getAspLoadType();
	
	/**
	 * 
	 * @Description: 返回民航统计系统飞机类型
	 * @return
	 */
	public UploadType getAspPlaneType();
	
	/**
	 * 
	 * @Description: 返回柳州机场PIS系统类型
	 * @return
	 */
	public UploadType getPisDynType();

}
