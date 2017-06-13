package com.lzairport.ais.models.aodb;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



/**
 * 航班历史的实体类
 * @author ZhangYu
 * @version 0.9a 24/08/14
 * @since JDK 1.6
 *
 */
@Entity
public class HisFlight extends Flight {
	
	
	public static String APPROVE       = "approve";
	public static String CARRIER       = "carrier";
	public static String CRAFTCODE     = "craftCode";
	public static String LOC_ADULT     = "loc_Adult";
	public static String LOC_CHD       = "loc_Chd";
	public static String LOC_INF       = "loc_Inf";
	public static String LOC_GOODS     = "loc_Goods";
	public static String LOC_MAIL      = "loc_Mail";
	public static String LOC_LUGGAGE   = "loc_Luggage";
	public static String FLIGHT        = "flight";
	public static String TRA_ADULT     = "tra_Adult";
	public static String TRA_CHD       = "tra_Chd";
	public static String TRA_INF       = "tra_Inf";
	public static String TRA_GOODS     = "tra_Goods";
	public static String TRA_MAIL      = "tra_Mail";
	public static String TRA_LUGGAGE   = "tra_Luggage";
	public static String AVAILABLELOAD = "availableLoad";
	public static String AVAILABLESEAT = "availableSeat";
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="carrier")
	private Carrier carrier;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="hisFlight_id")
	private Set<HisShareFlight> shareFlights ;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="hisFlight_id")
	private Set<HisStopFlight> stopFlights; 
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="hisFlight_id")
	private Set<HisFlightLoad> loads ;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="hisFlight_id")
	private Set<HisPayingPassenger> payingPassengers;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="hisFlight_id")
	private Set<HisFlightDisPatch> flightDisPatchs;
	
	
	/**
	 *   机型代码只记录前四位
	 */
	@Column(length=4)
	private String craftCode;
	
	private Boolean approve;
	
	@Column(name="adult")
	private int loc_Adult;
	
	@Column(name="chd")
	private int loc_Chd;
	
	@Column(name="inf")
	private int loc_Inf;
	
	@Column(name="goods")
	private int loc_Goods;
	
	@Column(name="mail")
	private int loc_Mail;
	
	@Column(name="luggage")
	private int loc_Luggage;	
	
	private int tra_Adult;
	
	private int tra_Chd;
	
	private int tra_Inf;
	
	private int tra_Goods;
	
	private int tra_Mail;
	
	private int tra_Luggage;
	
	private int availableLoad;
	
	private int availableSeat;
	

	/**
	 * @return the carrier
	 */
	public Carrier getCarrier() {
		return carrier;
	}

	/**
	 * @param carrier the carrier to set
	 */
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	@Override
	public Set<HisFlightLoad> getLoads() {
		// TODO Auto-generated method stub
		return loads;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setLoads(Set loads) {
		// TODO Auto-generated method stub
		this.loads = loads;
	}


	@Override
	public Set<HisStopFlight> getStopFlights() {
		// TODO Auto-generated method stub
		return stopFlights;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setStopFlights(Set stopFlights) {
		// TODO Auto-generated method stub
		this.stopFlights = stopFlights;
	}

	@Override
	public Set<HisShareFlight> getShareFlights() {
		// TODO Auto-generated method stub
		return shareFlights;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setShareFlights(Set shareFlights) {
		// TODO Auto-generated method stub
		this.shareFlights = shareFlights;
	}




	@Override
	public Set<HisPayingPassenger> getPayingPassengers() {
		return this.payingPassengers;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setPayingPassengers(Set payingPassengers) {
		// TODO Auto-generated method stub
		this.payingPassengers = payingPassengers;
	}

	@Override
	public Set<HisFlightDisPatch> getFlightDisPatchs() {
		return flightDisPatchs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setFlightDisPatchs(Set flightDisPatchs) {
		// TODO Auto-generated method stub
		this.flightDisPatchs = flightDisPatchs;
	}

	/**
	 * @return the approve
	 */
	public Boolean getApprove() {
		return approve;
	}

	/**
	 * @param approve the approve to set
	 */
	public void setApprove(Boolean approve) {
		this.approve = approve;
	}


	/**
	 * @return the loc_Adult
	 */
	public int getLoc_Adult() {
		return loc_Adult;
	}

	/**
	 * @param loc_Adult the loc_Adult to set
	 */
	public void setLoc_Adult(int loc_Adult) {
		this.loc_Adult = loc_Adult;
	}

	/**
	 * @return the loc_Chd
	 */
	public int getLoc_Chd() {
		return loc_Chd;
	}

	/**
	 * @param loc_Chd the loc_Chd to set
	 */
	public void setLoc_Chd(int loc_Chd) {
		this.loc_Chd = loc_Chd;
	}

	/**
	 * @return the loc_Inf
	 */
	public int getLoc_Inf() {
		return loc_Inf;
	}

	/**
	 * @param loc_Inf the loc_Inf to set
	 */
	public void setLoc_Inf(int loc_Inf) {
		this.loc_Inf = loc_Inf;
	}

	/**
	 * @return the loc_Goods
	 */
	public int getLoc_Goods() {
		return loc_Goods;
	}

	/**
	 * @param loc_Goods the loc_Goods to set
	 */
	public void setLoc_Goods(int loc_Goods) {
		this.loc_Goods = loc_Goods;
	}

	/**
	 * @return the loc_Mail
	 */
	public int getLoc_Mail() {
		return loc_Mail;
	}

	/**
	 * @param loc_Mail the loc_Mail to set
	 */
	public void setLoc_Mail(int loc_Mail) {
		this.loc_Mail = loc_Mail;
	}

	/**
	 * @return the loc_Luggage
	 */
	public int getLoc_Luggage() {
		return loc_Luggage;
	}

	/**
	 * @param loc_Luggage the loc_Luggage to set
	 */
	public void setLoc_Luggage(int loc_Luggage) {
		this.loc_Luggage = loc_Luggage;
	}

	/**
	 * @return the tra_Adult
	 */
	public int getTra_Adult() {
		return tra_Adult;
	}

	/**
	 * @param tra_Adult the tra_Adult to set
	 */
	public void setTra_Adult(int tra_Adult) {
		this.tra_Adult = tra_Adult;
	}

	/**
	 * @return the tra_Chd
	 */
	public int getTra_Chd() {
		return tra_Chd;
	}

	/**
	 * @param tra_Chd the tra_Chd to set
	 */
	public void setTra_Chd(int tra_Chd) {
		this.tra_Chd = tra_Chd;
	}

	/**
	 * @return the tra_Inf
	 */
	public int getTra_Inf() {
		return tra_Inf;
	}

	/**
	 * @param tra_Inf the tra_Inf to set
	 */
	public void setTra_Inf(int tra_Inf) {
		this.tra_Inf = tra_Inf;
	}

	/**
	 * @return the tra_Goods
	 */
	public int getTra_Goods() {
		return tra_Goods;
	}

	/**
	 * @param tra_Goods the tra_Goods to set
	 */
	public void setTra_Goods(int tra_Goods) {
		this.tra_Goods = tra_Goods;
	}

	/**
	 * @return the tra_Mail
	 */
	public int getTra_Mail() {
		return tra_Mail;
	}

	/**
	 * @param tra_Mail the tra_Mail to set
	 */
	public void setTra_Mail(int tra_Mail) {
		this.tra_Mail = tra_Mail;
	}

	/**
	 * @return the tra_Luggage
	 */
	public int getTra_Luggage() {
		return tra_Luggage;
	}

	/**
	 * @param tra_Luggage the tra_Luggage to set
	 */
	public void setTra_Luggage(int tra_Luggage) {
		this.tra_Luggage = tra_Luggage;
	}

	/**
	 * @return the availableLoad
	 */
	public int getAvailableLoad() {
		return availableLoad;
	}

	/**
	 * @param availableLoad the availableLoad to set
	 */
	public void setAvailableLoad(int availableLoad) {
		this.availableLoad = availableLoad;
	}

	/**
	 * @return the availableSeat
	 */
	public int getAvailableSeat() {
		return availableSeat;
	}

	/**
	 * @param availableSeat the availableSeat to set
	 */
	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}

}
