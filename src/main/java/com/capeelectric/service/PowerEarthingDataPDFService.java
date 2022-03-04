package com.capeelectric.service;

import com.capeelectric.exception.PowerEarthingDataException;

public interface PowerEarthingDataPDFService {

	public void printPowerEarthingData(String userName, Integer emcId) throws PowerEarthingDataException;

}
