package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.model.ElectromagneticCompatability;

public interface ElectromagneticPDFService {

	public void printElectromagneticData(String userName, Integer emcId, Optional<ElectromagneticCompatability> electromagneticDataRep) throws ElectromagneticCompatabilityException;

}
