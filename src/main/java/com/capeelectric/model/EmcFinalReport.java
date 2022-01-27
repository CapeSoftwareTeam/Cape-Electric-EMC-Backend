package com.capeelectric.model;

public class EmcFinalReport {
	private String userName;

	private Integer emcId;

	private PowerEarthingData powerEarthingData;

	private FacilityData facilityData;

	private ElectromagneticCompatability electromagneticCompatability;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getEmcId() {
		return emcId;
	}

	public void setEmcId(Integer emcId) {
		this.emcId = emcId;
	}

	public PowerEarthingData getPowerEarthingData() {
		return powerEarthingData;
	}

	public void setPowerEarthingData(PowerEarthingData powerEarthingData) {
		this.powerEarthingData = powerEarthingData;
	}

	public FacilityData getFacilityData() {
		return facilityData;
	}

	public void setFacilityData(FacilityData facilityData) {
		this.facilityData = facilityData;
	}

	public ElectromagneticCompatability getElectromagneticCompatability() {
		return electromagneticCompatability;
	}

	public void setElectromagneticCompatability(ElectromagneticCompatability electromagneticCompatability) {
		this.electromagneticCompatability = electromagneticCompatability;
	}
	
	
	


}
