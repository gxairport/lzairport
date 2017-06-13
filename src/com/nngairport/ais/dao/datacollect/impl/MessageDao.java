package com.nngairport.ais.dao.datacollect.impl;

import javax.ejb.Stateless;
import com.lzairport.ais.dao.impl.AodbDaoImpl;
import com.nngairport.ais.dao.datacollect.IMessageDao;
import com.nngairport.ais.models.datacollect.Message;


/**
 * FileName      MessageDao.java
 * @Description  TODO IB消息Dao接口实现类 
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年5月25日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年5月25日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class MessageDao extends AodbDaoImpl<Integer, Message> implements IMessageDao{

}
