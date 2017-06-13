package com.nngairport.ais.models.datacollect;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.lzairport.ais.models.IntIdEntity;

/**
 * FileName      Message.java
 * @Description  TODO   IB消息实体类，用于保存IB发来的报文
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2017年5月25日
 * @ModificationHistory
 * Date         Author     Version   Description
 * <p>---------------------------------------------
 * <p>2017年5月25日      ZhangYu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@Entity
public class Message extends IntIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 发送序列号
	 */
	private int sequenceNumber;
	
	/**
	 * 消息类型
	 */
	@Column(length=4)
	private String messageType;

	/**
	 *  发送系统
	 */
	@Column(length=20)
	private String sourceSystemID;

	/**
	 * 目标系统
	 */
	@Column(length=20)
	private String destinationSystemID;
	
	
	/**
	 *  消息原系统
	 */
	@Column(length=20)
	private String messageOriginatorID;
	
	/**
	 * 发送时间
	 */
	private Date sentDateTime;
	
	
	/**
	 *  原系统时间
	 */
	private Date OriginatorDateTime;
	
	@Lob
	private String content;

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getSourceSystemID() {
		return sourceSystemID;
	}

	public void setSourceSystemID(String sourceSystemID) {
		this.sourceSystemID = sourceSystemID;
	}

	public String getDestinationSystemID() {
		return destinationSystemID;
	}

	public void setDestinationSystemID(String destinationSystemID) {
		this.destinationSystemID = destinationSystemID;
	}

	public String getMessageOriginatorID() {
		return messageOriginatorID;
	}

	public void setMessageOriginatorID(String messageOriginatorID) {
		this.messageOriginatorID = messageOriginatorID;
	}

	public Date getSentDateTime() {
		return sentDateTime;
	}

	public void setSentDateTime(Date sentDateTime) {
		this.sentDateTime = sentDateTime;
	}

	public Date getOriginatorDateTime() {
		return OriginatorDateTime;
	}

	public void setOriginatorDateTime(Date originatorDateTime) {
		OriginatorDateTime = originatorDateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	

}
