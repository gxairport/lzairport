package com.lzairport.ais.service.aodb;

import javax.ejb.Remote;

import com.lzairport.ais.models.aodb.HisShareFlight;
import com.lzairport.ais.service.IService;


@Remote
public interface IHisShareFlightService extends
		IService<Integer, HisShareFlight> {

}
