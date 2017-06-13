package com.nngairport.ais.service.datacollect.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.lzairport.ais.service.impl.Service;
import com.nngairport.ais.dao.datacollect.IMessageDao;
import com.nngairport.ais.models.datacollect.Message;
import com.nngairport.ais.service.datacollect.IMessageService;


/**
 * FileName      MessageService.java
 * @Description  TODO  IB消息Service接口实现类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年5月25日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年5月25日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Stateless
public class MessageService extends Service<Integer, Message> implements IMessageService {

	@EJB
	public void setMessageDao(IMessageDao dao){
		setDao(dao);
	}
}
