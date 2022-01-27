package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.EmcFinalReportException;
import com.capeelectric.model.EmcFinalReport;

public interface FinalReportService {

	Optional<EmcFinalReport> retrieveEmcReports(String userName, Integer emcId) throws EmcFinalReportException;
}
