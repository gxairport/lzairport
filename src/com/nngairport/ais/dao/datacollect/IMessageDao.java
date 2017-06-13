package com.nngairport.ais.dao.datacollect;

import javax.ejb.Local;

import com.lzairport.ais.dao.IDao;
import com.nngairport.ais.models.datacollect.Message;


/**
 * 
 * 
 * FileName      IMessageDao.java
 * @Description  TODO  IB消息Dao接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年5月25日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年5月25日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Local
public interface IMessageDao extends IDao<Integer, Message> {

}
