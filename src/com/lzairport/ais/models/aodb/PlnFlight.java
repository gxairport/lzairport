package com.lzairport.ais.models.aodb;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * 航班计划的实体类
 * @author ZhangYu
 * @version 0.9a 16/08/14
 * @since JDK 1.6
 *
 */

@Entity
public class PlnFlight extends Flight {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="plnFlight_id")
	private Set<PlnShareFlight> shareFlights ;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="plnFlight_id")
	private Set<PlnStopFlight> stopFlights; 
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="plnFlight_id")
	private Set<PlnFlightLoad> loads ;	

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="plnFlight_id")
	private Set<PlnPayingPassenger> payingPassengers;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},orphanRemoval=true)
	@JoinColumn(name="plnFlight_id")
	private Set<PlnFlightDisPatch> flightDisPatchs; 
	
	@Override
	public Set<PlnFlightLoad> getLoads() {
		return loads;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setLoads(Set loads) {
		this.loads = loads;
	}


	@Override
	public Set<PlnStopFlight> getStopFlights() {
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
	public Set<PlnShareFlight> getShareFlights() {
		// TODO Auto-generated method stub
		return this.shareFlights;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setShareFlights(Set shareFlights) {
		// TODO Auto-generated method stub
		this.shareFlights = shareFlights;
	}

	@Override
	public Set<PlnPayingPassenger> getPayingPassengers() {
		return this.payingPassengers;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setPayingPassengers(Set payingPassengers) {
		// TODO Auto-generated method stub
		this.payingPassengers = payingPassengers;
	}

	@Override
	public Set<PlnFlightDisPatch> getFlightDisPatchs() {
		return this.flightDisPatchs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setFlightDisPatchs(Set flightDisPatchs) {
		// TODO Auto-generated method stub
		this.flightDisPatchs = flightDisPatchs;
	}
	
	
	

}
