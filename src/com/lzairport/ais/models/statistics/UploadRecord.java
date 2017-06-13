package com.lzairport.ais.models.statistics;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzairport.ais.models.DefaultEntity;

/**
 * 
 * FileName      UploadRecord.java
 * @Description  用来记录统计系统上传数据的情况
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年1月12日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年1月12日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class UploadRecord extends DefaultEntity implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 各个数据字段名,用来直接点用字段名
	 */
	public static String ID="id";
	
	public static String UPLOADTYPE ="uploadType";
	
	public static String UPLOADDATE = "uploadDate";
	
	public static String TOTALRECORDS = "totalRecords";
	
	public static String UPDATERECORDS = "updateRecords";
	
	public static String NEWRECORDS = "newRecords";
	
	public static String FAILRECORDS = "failRecords";
	
	public static String DESCRIPTION = "description"; 
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	@ManyToOne
	private UploadType uploadType;
	
	private Date uploadDate;
	
	private int totalRecords;
	
	private int updateRecords;
	
	private int newRecords;
	
	private int failRecords;
	
	@Lob
	private String description;
	
	
	

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
	 * @return the uploadType
	 */
	public UploadType getUploadType() {
		return uploadType;
	}

	/**
	 * @param uploadType the uploadType to set
	 */
	public void setUploadType(UploadType uploadType) {
		this.uploadType = uploadType;
	}

	/**
	 * @return the uploadDate
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  	
	public Date getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate the uploadDate to set
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the updateRecords
	 */
	public int getUpdateRecords() {
		return updateRecords;
	}

	/**
	 * @param updateRecords the updateRecords to set
	 */
	public void setUpdateRecords(int updateRecords) {
		this.updateRecords = updateRecords;
	}

	/**
	 * @return the newRecords
	 */
	public int getNewRecords() {
		return newRecords;
	}

	/**
	 * @param newRecords the newRecords to set
	 */
	public void setNewRecords(int newRecords) {
		this.newRecords = newRecords;
	}

	/**
	 * @return the failRecords
	 */
	public int getFailRecords() {
		return failRecords;
	}

	/**
	 * @param failRecords the failRecords to set
	 */
	public void setFailRecords(int failRecords) {
		this.failRecords = failRecords;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	

}
