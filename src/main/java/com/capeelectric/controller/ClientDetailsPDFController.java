package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.service.ClientDetailsPDFService;

@RestController
@RequestMapping("/api/emc/v1")
public class ClientDetailsPDFController {
	private static final Logger logger = LoggerFactory.getLogger(ClientDetailsPDFController.class);

	@Autowired
	private ClientDetailsPDFService clientDetailsPDFService;

	@GetMapping("/printClientDetails/{userName}/{emcId}")
	public ResponseEntity<String> printClientDetails(@PathVariable String userName, @PathVariable Integer emcId)
			throws ClientDetailsException {
		logger.info("called printClientDetails function userName: {},emcId : {}", userName, emcId);
		clientDetailsPDFService.printClientDetails(userName, emcId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
