package com.lzairport.ais.vo.converter;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.models.aodb.Airlines;
import com.lzairport.ais.models.aodb.Carrier;
import com.lzairport.ais.models.aodb.HisPayingPassenger;
import com.lzairport.ais.service.aodb.IAirlinesService;
import com.lzairport.ais.service.aodb.ICarrierService;
import com.lzairport.ais.service.aodb.IHisPayingPassengerService;
import com.lzairport.ais.vo.PayingPassengerVO;


/**
 * 
 * FileName      PayingPassengerConverter.java
 * @Description  TODO历史付费旅客值对象和航班实体转换类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年2月19日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2017年2月19日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */
@Stateless
public class PayingPassengerConverter extends BaseConverter<HisPayingPassenger, PayingPassengerVO>
		implements IConverter<HisPayingPassenger, PayingPassengerVO> {

	@EJB
	private IHisPayingPassengerService payingPassengerService;
	
	@EJB
	private IAirlinesService airlinesService;
	
	@EJB
	private ICarrierService carrierService;

	@Override
	public void copyDiffPropertiesFromVO2PO(HisPayingPassenger target, PayingPassengerVO source) throws Exception {
		// TODO Auto-generated method stub
		target.setCarrier(carrierService.findByFieldSingle(Carrier.CNSHORTNAME, source.getCarrierName()));
		target.setAirlines(airlinesService.findByFieldSingle(Airlines.CNSHORTNAME, source.getAirlinesName()));
	}

	@Override
	public void copyDiffPropertiesFromPO2VO(PayingPassengerVO target, HisPayingPassenger source) throws Exception {
		// TODO Auto-generated method stub
		if (source.getAirlines() != null){
			target.setAirlinesName(source.getAirlines().getCnShortName());
		}
		
		if (source.getCarrier() != null){
			target.setCarrierName(source.getCarrier().getCnShortName());
		}
	}

	@Override
	protected HisPayingPassenger findEntity(PayingPassengerVO vo) {
		return payingPassengerService.findByID(vo.getId());
	}

}
