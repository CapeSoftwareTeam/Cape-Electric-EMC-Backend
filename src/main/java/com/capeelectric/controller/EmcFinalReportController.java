package com.capeelectric.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.EmcFinalReportException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.EmcFinalReport;
import com.capeelectric.service.impl.EmcFinalReportServiceImpl;

/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/emc/v1")
public class EmcFinalReportController {

	private static final Logger logger = LoggerFactory.getLogger(EmcFinalReportController.class);

	@Autowired
	EmcFinalReportServiceImpl emcFinalReportServiceImpl;

	@GetMapping("/retrieveEmcReport/{userName}/{emcId}")
	public ResponseEntity<Optional<EmcFinalReport>> retrieveEmcReports(@PathVariable String userName,
			@PathVariable Integer emcId) throws EmcFinalReportException {
		logger.info("FinalReportAPI_started retrieveFinalEmcReport function userName: {},emcId : {}", userName, emcId);

		return new ResponseEntity<Optional<EmcFinalReport>>(
				emcFinalReportServiceImpl.retrieveEmcReports(userName, emcId), HttpStatus.OK);

	}

	@GetMapping("/retrieveListOfClientDetails/{userName}")
	public ResponseEntity<List<ClientDetails>> retrieveListOfClientDetails(@PathVariable String userName)
			throws EmcFinalReportException {
		logger.info("FinalReportAPI_started retrieveListOfClientDetails function userName: {},emcId : {}", userName);

		return new ResponseEntity<List<ClientDetails>>(emcFinalReportServiceImpl.retrieveListOfClientDetails(userName),
				HttpStatus.OK);

	}

}
