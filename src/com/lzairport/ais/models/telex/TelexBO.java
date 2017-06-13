package com.lzairport.ais.models.telex;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.lzairport.ais.models.DefaultEntity;

/**
 * 电报的实体类
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK1.6
 *
 */
@Entity
@Table(name="Telex")
public class TelexBO extends DefaultEntity implements Serializable  {

	private static final long serialVersionUID = 1L;

	/**
	 * 各个数据字段名,用来直接调用字段名
	 */
	public static String ID="id";
	
	public static String NUMBER="number";
	
	public static String STATE="state";
	
	public static String CROWN="crown";
	
	public static String TIME="time";
	
	public static String TELEXTYPE="telexType";
	
	public static String PRIORITY="priority";
	
	public static String TIMEGROUP="timeGroup";
	
	public static String ADDRES="address";
	
	public static String ANALYTICYTYPE="analyticyType";
	
	public static String FLIGHTS="flights";
	
	public static String CONTENT="content";
	
	public static String ERRLOG="errlog";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;		

	private int number;
	@Column(length=1)
	private String state;
	private String crown;
	private Date time;
	@ManyToOne
	private TelexType telexType;
	private String priority;
	private String timeGroup;
	private String address;
	@ManyToOne
	private AnalyticyType analyticyType;
	private String flights;
	@Lob
	private String content;

	@Lob
	private String errLog;
	
	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		this.id = (Integer) id;
	}

	/**
	 * @return the errlog
	 */
	public String getErrlog() {
		return errLog;
	}

	/**
	 * @param errlog the errlog to set
	 */
	public void setErrlog(String errlog) {
		this.errLog = errlog;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the crown
	 */
	public String getCrown() {
		return crown;
	}

	/**
	 * @param crown
	 *            the crown to set
	 */
	public void setCrown(String crown) {
		this.crown = crown;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the telexType
	 */
	public TelexType getTelexType() {
		return telexType;
	}

	/**
	 * @param telexType
	 *            the telexType to set
	 */
	public void setTelexType(TelexType telexType) {
		this.telexType = telexType;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the timeGroup
	 */
	public String getTimeGroup() {
		return timeGroup;
	}

	/**
	 * @param timeGroup
	 *            the timeGroup to set
	 */
	public void setTimeGroup(String timeGroup) {
		this.timeGroup = timeGroup;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the flights
	 */
	public String getFlights() {
		return flights;
	}

	/**
	 * @param flights
	 *            the flights to set
	 */
	public void setFlights(String flights) {
		this.flights = flights;
	}


	public AnalyticyType getAnalyticyType() {
		return analyticyType;
	}

	public void setAnalyticyType(AnalyticyType analyticyType) {
		this.analyticyType = analyticyType;
	}



}
