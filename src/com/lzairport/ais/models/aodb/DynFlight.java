package com.lzairport.ais.models.aodb;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 * 航班动态实体类
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class DynFlight extends Flight   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static  String DELAYTIMES ="delayTimes";
	
	public static  String FPLTIMES="FPLTimes";
	
	public static  String CLEARANCETIMES ="clearanceTimes";
	
	
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="dynFlight_id")
	private Set<DynShareFlight> shareFlights ;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="dynFlight_id")
	private Set<DynStopFlight> stopFlights; 
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="dynFlight_id")
	private Set<DynFlightLoad> loads ;
	

	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="dynFlight_id")
	private Set<DynPayingPassenger> payingPassengers;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="dynFlight_id")
	private Set<DynFlightDisPatch> flightDisPatchs;
	
	
	private int delayTimes;
	
	private int FPLTimes;
	
	private int clearanceTimes;
	

	@Override
	public Set<DynFlightLoad> getLoads() {
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
	public Set<DynStopFlight> getStopFlights() {
		// TODO Auto-generated method stub
		return stopFlights;
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setStopFlights(Set stopFlights) {
		// TODO Auto-generated method stub
		this.stopFlights = stopFlights;
	}

	@Override
	public Set<DynShareFlight> getShareFlights() {
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
	public Set<DynPayingPassenger> getPayingPassengers() {
		return payingPassengers;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setPayingPassengers(Set payingPassengers) {
		this.payingPassengers =payingPassengers;
	}


	@Override
	public Set<DynFlightDisPatch> getFlightDisPatchs() {
		return this.flightDisPatchs;
	}

	@SuppressWarnings({  "rawtypes", "unchecked" })
	@Override
	public void setFlightDisPatchs(Set flightDisPatchs) {
		// TODO Auto-generated method stub
		this.flightDisPatchs = flightDisPatchs;
	}

	/**
	 * @return the delayTimes
	 */
	public int getDelayTimes() {
		return delayTimes;
	}

	/**
	 * @param delayTimes the delayTimes to set
	 */
	public void setDelayTimes(int delayTimes) {
		this.delayTimes = delayTimes;
	}

	/**
	 * @return the fPLTimes
	 */
	public int getFPLTimes() {
		return FPLTimes;
	}

	/**
	 * @param fPLTimes the fPLTimes to set
	 */
	public void setFPLTimes(int fPLTimes) {
		FPLTimes = fPLTimes;
	}

	/**
	 * @return the clearanceTimes
	 */
	public int getClearanceTimes() {
		return clearanceTimes;
	}

	/**
	 * @param clearanceTimes the clearanceTimes to set
	 */
	public void setClearanceTimes(int clearanceTimes) {
		this.clearanceTimes = clearanceTimes;
	}
	

}
