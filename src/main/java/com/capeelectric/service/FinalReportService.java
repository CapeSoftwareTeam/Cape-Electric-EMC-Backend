package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.EmcFinalReportException;
import com.capeelectric.model.EmcFinalReport;
import com.capeelectric.model.FacilityData;

import java.util.List;

public interface FinalReportService {
	List<FacilityData> retrieveListOfFacilityData(String userName)throws EmcFinalReportException;
	Optional<EmcFinalReport> retrieveEmcReports(String userName, Integer emcId) throws EmcFinalReportException;
}
