package com.capeelectric.service;

import com.capeelectric.exception.FacilityDataException;

public interface FacilityDataPDFService {

	public void printFacilityDataDetails(String userName, Integer emcId) throws FacilityDataException;

}
