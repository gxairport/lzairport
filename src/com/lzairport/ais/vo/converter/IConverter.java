package com.lzairport.ais.vo.converter;

import javax.ejb.Local;

/**
 * 
 * FileName      IHisFlightConverter.java
 * @Description  历史航班信息值对象和航班实体转换类的接口
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2015年10月21日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2015年10月21日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */


@Local
public interface IConverter<E,V>  {
	
	/**
	 * 
	 * @Description: TODO从HisFlight对象转换为FlightVO对象
	 * @param flight 需要转换的HisFlight对象
	 * @return FlightVO对象
	 * @throws Exception
	 */
	public V getVOject(E entity) throws Exception;
	
	/**
	 * 
	 * @Description: TODO 从VO对象转换为HisFlight对象
	 * @param flightVO 需要转换的FlightVO对象 
	 * @return HisFlight对象
	 * @throws Exception
	 */
	public E getEntity(V vObject) throws Exception;
	
	

}