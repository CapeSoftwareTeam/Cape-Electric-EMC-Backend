package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ELECTRONICSYSTEM_TABLE")
public class ElectronicSystem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ELECTRONICSYSTEM_ID ")
	private Integer electronicSystemId;

	@Column(name = "PANEL_ID")
	private Integer panelId;

	@Column(name = "NAMEPLATE_DATA")
	private Integer namePlateData;

	@Column(name = "MAIN_CIRCUTE_BRAKER")
	private Integer mainCircuteBraker;

	@Column(name = "MAINCIRCUTE_BRAKER_RATING")
	private Integer mainCircuteBrakerRating;

	@Column(name = "EMERGENCY_TRIP_REMOTE")
	private Integer emergencyTripRemote;

	@Column(name = "EMERGENCY_TRIP_LOCAL")
	private Integer emergencyTripLocal;

	@Column(name = "OHTHER_TRIP")
	private Integer otherTrip;

	@Column(name = "DIFFERENTAL_PROTECTION")
	private Integer differentalProtection;

	@Column(name = "BUODING_STELL")
	private Integer bouodingStell;

	@Column(name = "PANEL_FEED")
	private Integer panelFeed;

	@Column(name = "PHASE_WIRES")
	private Integer phaseWires;

	@Column(name = "NEUTRAL_WIRE")
	private Integer neutralWire;

	@Column(name = "PE_WIRE_SIZE")
	private Integer peWireSize;

	@Column(name = "PANNEL_CONNECTORS")
	private Integer pannelConnectors;

	@Column(name = "NEUTRAL_BUS")
	private Integer neutralBus;

	@Column(name = "EARTH_BUS")
	private Integer earthBus;

	@Column(name = "LIST_OF_NONELECTRONICLOAD")
	private Integer listOfNonElectronicLoad;

	@Column(name = "DEDICATED_ELECTRONIC_SYSTEM")
	private Integer dedicatedElectronicSystem;

	@Column(name = "NON_COMPUTER_LOADS")
	private Integer nonComputerLoads;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "POWEREARTHINGDATA_ID")
	private PowerEarthingData powerEarthingData;

	public Integer getElectronicSystemId() {
		return electronicSystemId;
	}

	public void setElectronicSystemId(Integer electronicSystemId) {
		this.electronicSystemId = electronicSystemId;
	}

	public Integer getPanelId() {
		return panelId;
	}

	public void setPanelId(Integer panelId) {
		this.panelId = panelId;
	}

	public Integer getNamePlateData() {
		return namePlateData;
	}

	public void setNamePlateData(Integer namePlateData) {
		this.namePlateData = namePlateData;
	}

	public Integer getMainCircuteBraker() {
		return mainCircuteBraker;
	}

	public void setMainCircuteBraker(Integer mainCircuteBraker) {
		this.mainCircuteBraker = mainCircuteBraker;
	}

	public Integer getMainCircuteBrakerRating() {
		return mainCircuteBrakerRating;
	}

	public void setMainCircuteBrakerRating(Integer mainCircuteBrakerRating) {
		this.mainCircuteBrakerRating = mainCircuteBrakerRating;
	}

	public Integer getEmergencyTripRemote() {
		return emergencyTripRemote;
	}

	public void setEmergencyTripRemote(Integer emergencyTripRemote) {
		this.emergencyTripRemote = emergencyTripRemote;
	}

	public Integer getEmergencyTripLocal() {
		return emergencyTripLocal;
	}

	public void setEmergencyTripLocal(Integer emergencyTripLocal) {
		this.emergencyTripLocal = emergencyTripLocal;
	}

	public Integer getOtherTrip() {
		return otherTrip;
	}

	public void setOtherTrip(Integer otherTrip) {
		this.otherTrip = otherTrip;
	}

	public Integer getDifferentalProtection() {
		return differentalProtection;
	}

	public void setDifferentalProtection(Integer differentalProtection) {
		this.differentalProtection = differentalProtection;
	}

	public Integer getBouodingStell() {
		return bouodingStell;
	}

	public void setBouodingStell(Integer bouodingStell) {
		this.bouodingStell = bouodingStell;
	}

	public Integer getPanelFeed() {
		return panelFeed;
	}

	public void setPanelFeed(Integer panelFeed) {
		this.panelFeed = panelFeed;
	}

	public Integer getPhaseWires() {
		return phaseWires;
	}

	public void setPhaseWires(Integer phaseWires) {
		this.phaseWires = phaseWires;
	}

	public Integer getNeutralWire() {
		return neutralWire;
	}

	public void setNeutralWire(Integer neutralWire) {
		this.neutralWire = neutralWire;
	}

	public Integer getPeWireSize() {
		return peWireSize;
	}

	public void setPeWireSize(Integer peWireSize) {
		this.peWireSize = peWireSize;
	}

	public Integer getPannelConnectors() {
		return pannelConnectors;
	}

	public void setPannelConnectors(Integer pannelConnectors) {
		this.pannelConnectors = pannelConnectors;
	}

	public Integer getNeutralBus() {
		return neutralBus;
	}

	public void setNeutralBus(Integer neutralBus) {
		this.neutralBus = neutralBus;
	}

	public Integer getEarthBus() {
		return earthBus;
	}

	public void setEarthBus(Integer earthBus) {
		this.earthBus = earthBus;
	}

	public Integer getListOfNonElectronicLoad() {
		return listOfNonElectronicLoad;
	}

	public void setListOfNonElectronicLoad(Integer listOfNonElectronicLoad) {
		this.listOfNonElectronicLoad = listOfNonElectronicLoad;
	}

	public Integer getDedicatedElectronicSystem() {
		return dedicatedElectronicSystem;
	}

	public void setDedicatedElectronicSystem(Integer dedicatedElectronicSystem) {
		this.dedicatedElectronicSystem = dedicatedElectronicSystem;
	}

	public Integer getNonComputerLoads() {
		return nonComputerLoads;
	}

	public void setNonComputerLoads(Integer nonComputerLoads) {
		this.nonComputerLoads = nonComputerLoads;
	}

	public PowerEarthingData getPowerEarthingData() {
		return powerEarthingData;
	}

	public void setPowerEarthingData(PowerEarthingData powerEarthingData) {
		this.powerEarthingData = powerEarthingData;
	}

}
