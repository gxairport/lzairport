package com.lzairport.ais.webservice.aodb;

import javax.ejb.Remote;

/**
 * 
 * FileName      IFlightWebService.java
 * @Description  TODO 航班动态的WebService接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年10月24日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年10月24日      Administrator    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Remote
public interface IFlightWebService {
	
	public String findDynByCondition(String condistion) throws Exception;

}
