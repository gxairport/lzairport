package com.lzairport.ais.models.aodb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 运输航班服务的抽象类，Dyn,His,Pln的航班运输服务都集成该表
 * @author ZhangYu
 * @version 0.9a 08/09/14
 * @since JDK 1.6
 *
 */

@MappedSuperclass
public abstract class FlightDisPatch extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public  final static String ID = "id";
	public  final static String PREWARNINGTIME = "preWarningTime";
	public  final static String ENDPLNTIME = "endPlnTime";
	public  final static String STARTPLNTIME = "startPlnTime";
	public  final static String ABNORMALMAKETIME = "abNormalMakeTime";
	public  final static String ABNORMALCLEARTIME = "abNormalClearTime";
	public  final static String PLNCLEARTIME = "plnClearTime";
	public  final static String ENDREALTIME = "endRealTime";
	public  final static String STARTREALTIME = "startRealTime";
	public  final static String DISPATCHITEM ="disPatchItem"; 
	public  final static String FLIGHT = "flight";
	
	
	//列表中文字段名
	 public final static List<String> FieldCnames  = Arrays.asList(
			"(开始)",
			"(完成)"
			);

	//中文字段名所对应的属性
	 public final static List<String> FieldEnames  = Arrays.asList(
			FlightDisPatch.STARTREALTIME,
			FlightDisPatch.ENDREALTIME			
			);
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;			
	
	@ManyToOne 
	private FlightDisPatchItem disPatchItem; 
	


	private Date startPlnTime;
	
	private Date endPlnTime;
	
	private Date startRealTime;
	
	private Date endRealTime;
	
	private Date preWarningTime;
	
	private Date abNormalMakeTime;
	
	private Date abNormalClearTime;
	
	private Date plnClearTime;
	
	private int times;
	
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		if (id != null){
			this.id = (Integer) id;
		}
	}

	/**
	 * @return the disPatchItem
	 */
	public FlightDisPatchItem getDisPatchItem() {
		return disPatchItem;
	}

	/**
	 * @param disPatchItem the disPatchItem to set
	 */
	public void setDisPatchItem(FlightDisPatchItem disPatchItem) {
		this.disPatchItem = disPatchItem;
	}

	/**
	 * @return the startPlnTime
	 */
	public Date getStartPlnTime() {
		return startPlnTime;
	}

	/**
	 * @param startPlnTime the startPlnTime to set
	 */
	public void setStartPlnTime(Date startPlnTime) {
		this.startPlnTime = startPlnTime;
	}

	/**
	 * @return the endPlnTime
	 */
	public Date getEndPlnTime() {
		return endPlnTime;
	}

	/**
	 * @param endPlnTime the endPlnTime to set
	 */
	public void setEndPlnTime(Date endPlnTime) {
		this.endPlnTime = endPlnTime;
	}

	/**
	 * @return the startRealTime
	 */
	public Date getStartRealTime() {
		return startRealTime;
	}

	/**
	 * @param startRealTime the startRealTime to set
	 */
	public void setStartRealTime(Date startRealTime) {
		this.startRealTime = startRealTime;
	}

	/**
	 * @return the endRealTime
	 */
	public Date getEndRealTime() {
		return endRealTime;
	}

	/**
	 * @param endRealTime the endRealTime to set
	 */
	public void setEndRealTime(Date endRealTime) {
		this.endRealTime = endRealTime;
	}

	/**
	 * @return the preWarningTime
	 */
	public Date getPreWarningTime() {
		return preWarningTime;
	}

	/**
	 * @param preWarningTime the preWarningTime to set
	 */
	public void setPreWarningTime(Date preWarningTime) {
		this.preWarningTime = preWarningTime;
	}

	/**
	 * @return the abNormalMakeTime
	 */
	public Date getAbNormalMakeTime() {
		return abNormalMakeTime;
	}

	/**
	 * @param abNormalMakeTime the abNormalMakeTime to set
	 */
	public void setAbNormalMakeTime(Date abNormalMakeTime) {
		this.abNormalMakeTime = abNormalMakeTime;
	}

	/**
	 * @return the abNormalClearTime
	 */
	public Date getAbNormalClearTime() {
		return abNormalClearTime;
	}

	/**
	 * @param abNormalClearTime the abNormalClearTime to set
	 */
	public void setAbNormalClearTime(Date abNormalClearTime) {
		this.abNormalClearTime = abNormalClearTime;
	}

	/**
	 * @return the plnClearTime
	 */
	public Date getPlnClearTime() {
		return plnClearTime;
	}

	/**
	 * @param plnClearTime the plnClearTime to set
	 */
	public void setPlnClearTime(Date plnClearTime) {
		this.plnClearTime = plnClearTime;
	}

	/**
	 * @return the times
	 */
	public int getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(int times) {
		this.times = times;
	}	
	

	/**
	 * 设置关联航班
	 * @return 关联航班实体
	 */
	public abstract Flight getFlight();

	/**
	 * 设置关联航班
	 * @param flight 关联航班实体
	 */
	public  abstract void  setFlight(Flight flight); 
	


}
