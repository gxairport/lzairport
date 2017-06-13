package com.lzairport.ais.service.aodb;

import java.util.Date;
import javax.ejb.Remote;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.aodb.HisFlight;
import com.lzairport.ais.models.statistics.CumulativeColumnField;
import com.lzairport.ais.service.DataFetchResponseInfo;
import com.lzairport.ais.vo.FlightVO;

/**
 * 航班历史的Service接口
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */

@Remote
public interface IHisFlightService extends IFlightService<Integer, HisFlight> {

	/**
	 * 
	 * @Description: 返回EXTJS页面所需要的Response对象
	 * @param conditions 条件
	 * @return DataFetchResponseInfo对象 
	 * @throws Exception
	 */
	public DataFetchResponseInfo fetchByCondition(QueryConditions conditions) throws Exception;
	
	/**
	 * 
	 * @Description: 根据flightVO对象保存HisFlight对象
	 * @param flightVO
	 * @throws Exception 
	 */
	public void update(FlightVO flightVO) throws Exception;


	/**
	 * @Description: 将指定日期之后的关联航班载量数据写入航班实体中的属性中
	 * @param startDate
	 */
	public void convertLoad(Date startDate);

	
	/**
	 * @Description: 将指定航班载量数据写入航班实体中的属性中,并计算客座率
	 * 以提高系统效率
	 * @param flight
	 */
	public void updateExtraParams(HisFlight flight);
	
	/**
	 * 
	 * @Description: 累计到指定日期的月旅客吞吐量
	 * @param date 指定日期
	 * @return 月旅客吞吐量
	 * @throws Exception
	 */
	public Integer cumulativeMonthField(Date date,CumulativeColumnField field) throws Exception;
	
	/**
	 * 
	 * @Description: 累计到指定日期的年旅客吞吐量
	 * @param date 指定日期
	 * @return 月旅客吞吐量
	 * @throws Exception
	 */
	public Integer cumulativeYearField(Date date,CumulativeColumnField field) throws Exception ;

	
	/**
	 * 
	 * @Description: 返回航班的航线，规则如下：
	 * 1、MU5203 SHA-LZH 航线为 SHA-LZH-SHA
	 * 2、MU5204 LZH-SHA 航线为 SHA-LZH-SHA
	 * 3、Mu5379 TAO-CGO-LZH 航线为 LZH-CGO-TAO
	 * 4、MU5380 LZH-CGO-TAO 航线为 LZH-CGO-TAO
	 * 5、EU2201 CTU-LZH-SZX 航线为 CTU-LZH-SZX
	 * 6、EU2202 SZX-LZH-CTU 航线为 SZX-LZH-CTU
	 * @return
	 */
	public void setRouteCn(HisFlight flight);
	
	/**
	 * 
	 * @Description: TODO 查找航班的上一段联接航班
	 * @param flight
	 */
	public void linkFlight(HisFlight flight);
	


}
