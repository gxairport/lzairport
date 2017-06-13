package com.nngairport.ais.service.datacollect;

import javax.ejb.Remote;

import com.lzairport.ais.service.IService;
import com.nngairport.ais.models.datacollect.Message;

@Remote
public interface IMessageService extends IService<Integer, Message> {

}
