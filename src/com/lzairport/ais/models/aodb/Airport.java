package com.lzairport.ais.models.aodb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.lzairport.ais.models.DefaultEntity;


/**
 * 机场的实体类
 * @author ZhangYu
 * @version 0.9a 17/08/14
 * @since JDK 1.6
 *
 */
@Entity
public class Airport extends DefaultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 各个数据字段名,用来调用点用字段名
	 */	
	public static String SHORTNAME ="shortName";
	public static String MAXCRAFTTYPE = "maxCraftType";
	public static String AREAATTRIBUTE = "areaAttribute";
	public static String THREECHARCODE = "threeCharCode";
	public static String HASOPEN = "hasOpen";
	public static String ID = "id";
	public static String CNNAME = "cnName";
	public static String DESCRIPTION = "description";
	public static String DEPTPHONE = "deptPhone";
	public static String FOURCHARCODE = "fourCharCode";
	public static String ENFULLNAME = "enName";
	public static String CNDESCRIPTION = "cnDescription";
	public static String HOMEPAGE = "homePage";	
	public static String INTERVALDELAYTIME = "intervalDelayTime";
	public static String REVADDDEP = "revAddDEP";
	public static String REVADDARR = "revAddARR";
	public static String REVADDPLN = "revAddPLN";
	public static String REVADDEST = "revAddEST";
	public static String OUTROUTE = "outRoute";
	public static String INROUTE = "InRoute";
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	@Id	
	@Column(length = 3)
	private String threeCharCode;
	
	@Column(length = 4)
	private String fourCharCode;
	
	@Column(length=8)
	private String shortName;
	
	private String description;
	
	@Column(length=64)
	private String enName;
	
	@Column(length=32)
	private String cnName;
	
	
	
	private String deptPhone;
	
	private String homePage;
	
	@ManyToOne
	@JoinColumn(name="attribute")
	private AreaAttribute areaAttribute;

	@ManyToOne
	@JoinColumn(name="maxCraftType")
	private CraftType maxCraftType;
	
	private boolean hasOpen;
	
	private int intervalDelayTime;
	
	/**
	 * 起飞报收电地址 FPL相同
	 */
	private String revAddDEP;
	
	/**
	 * 落地报收电地址 
	 */
	private String revAddARR;
	
	/**
	 *  计划报收电地址
	 */
	private String revAddPLN;
	
	/**
	 * 飞越报的收电地址
	 */
	private String revAddEST;
	
	/**
	 * 出港航路
	 */
	private String outRoute;
	
	/**
	 * 进港航路
	 */
	private String InRoute;
	
	
	
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
	 * @return the threeCharCode
	 */
	public String getThreeCharCode() {
		return threeCharCode;
	}

	/**
	 * @param threeCharCode the threeCharCode to set
	 */
	public void setThreeCharCode(String threeCharCode) {
		this.threeCharCode = threeCharCode;
	}

	/**
	 * @return the fourCharCode
	 */
	public String getFourCharCode() {
		return fourCharCode;
	}

	/**
	 * @param fourCharCode the fourCharCode to set
	 */
	public void setFourCharCode(String fourCharCode) {
		this.fourCharCode = fourCharCode;
	}

	/**
	 * @return the deptPhone
	 */
	public String getDeptPhone() {
		return deptPhone;
	}

	/**
	 * @param deptPhone the deptPhone to set
	 */
	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	/**
	 * @return the homePage
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * @param homePage the homePage to set
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	/**
	 * @return the attribute
	 */
	public AreaAttribute getAreaAttribute() {
		return areaAttribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAreaAttribute(AreaAttribute areaAttribute) {
		this.areaAttribute = areaAttribute;
	}

	/**
	 * @return the maxCraftType
	 */
	public CraftType getMaxCraftType() {
		return maxCraftType;
	}

	/**
	 * @param maxCraftType the maxCraftType to set
	 */
	public void setMaxCraftType(CraftType maxCraftType) {
		this.maxCraftType = maxCraftType;
	}

	/**
	 * @return the hasOpen
	 */
	public boolean getHasOpen() {
		return hasOpen;
	}

	/**
	 * @param hasOpen the hasOpen to set
	 */
	public void setHasOpen(boolean hasOpen) {
		this.hasOpen = hasOpen;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	/**
	 * @return the enName
	 */
	public String getEnName() {
		return enName;
	}

	/**
	 * @param enName the enName to set
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}

	/**
	 * @return the cnName
	 */
	public String getCnName() {
		return cnName;
	}

	/**
	 * @param cnName the cnName to set
	 */
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	/**
	 * @return the intervalDelayTime
	 */
	public int getIntervalDelayTime() {
		return intervalDelayTime;
	}

	/**
	 * @param intervalDelayTime the intervalDelayTime to set
	 */
	public void setIntervalDelayTime(int intervalDelayTime) {
		this.intervalDelayTime = intervalDelayTime;
	}


	/**
	 * @return the revAddDEP
	 */
	public String getRevAddDEP() {
		return revAddDEP;
	}

	/**
	 * @param revAddDEP the revAddDEP to set
	 */
	public void setRevAddDEP(String revAddDEP) {
		this.revAddDEP = revAddDEP;
	}

	/**
	 * @return the revAddARR
	 */
	public String getRevAddARR() {
		return revAddARR;
	}

	/**
	 * @param revAddARR the revAddARR to set
	 */
	public void setRevAddARR(String revAddARR) {
		this.revAddARR = revAddARR;
	}

	/**
	 * @return the revAddPLN
	 */
	public String getRevAddPLN() {
		return revAddPLN;
	}

	/**
	 * @param revAddPLN the revAddPLN to set
	 */
	public void setRevAddPLN(String revAddPLN) {
		this.revAddPLN = revAddPLN;
	}

	/**
	 * @return the revAddEST
	 */
	public String getRevAddEST() {
		return revAddEST;
	}

	/**
	 * @param revAddEST the revAddEST to set
	 */
	public void setRevAddEST(String revAddEST) {
		this.revAddEST = revAddEST;
	}

	/**
	 * @return the outRoute
	 */
	public String getOutRoute() {
		return outRoute;
	}

	/**
	 * @param outRoute the outRoute to set
	 */
	public void setOutRoute(String outRoute) {
		this.outRoute = outRoute;
	}

	/**
	 * @return the inRoute
	 */
	public String getInRoute() {
		return InRoute;
	}

	/**
	 * @param inRoute the inRoute to set
	 */
	public void setInRoute(String inRoute) {
		InRoute = inRoute;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.cnName;
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@Override
	public Object getIdValue() {
		return this.getThreeCharCode();
	}




	

}
