package com.lzairport.ais.service.statistics;

import javax.ejb.Remote;

import com.lzairport.ais.models.statistics.UploadRecord;
import com.lzairport.ais.service.IService;


/**
 * 
 * FileName      IUploadRecordService.java
 * @Description  统计系统上传记录的Service的接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年1月12日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IUploadRecordService extends IService<Integer, UploadRecord> {

}
