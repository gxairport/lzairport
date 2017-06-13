package com.lzairport.ais.jms;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import com.lzairport.ais.utils.EventCodeUtil;
import com.lzairport.ais.utils.JNDIUtil;

/**
 * 
 * @author Yu
 *
 */

public class DynFlightDispatchListeners extends DefaultEntityListeners {

	public DynFlightDispatchListeners() {
		super();
		notice = JNDIUtil.lookupEJB("ejb:/AisEJB//DynFlightDispatchNotice!com.lzairport.ais.jms.INotice");

	}
	
	@PostPersist void postInsert(Object entity){
		notice.changeNotice(EventCodeUtil.EntityChangeEvent, EventCodeUtil.ModelsAdd, entity);
	}
	
	
	@PostRemove void postRemove(Object entity){
		notice.changeNotice(EventCodeUtil.EntityChangeEvent, EventCodeUtil.ModelsRemove, entity);
	}
	
	@PostUpdate void postUpdate(Object entity){
		notice.changeNotice(EventCodeUtil.EntityChangeEvent, EventCodeUtil.ModelsUpdate, entity);
	}
	
	
}
