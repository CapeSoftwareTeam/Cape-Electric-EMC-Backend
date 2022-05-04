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

import com.capeelectric.service.AWSEmailService;

@RestController
@RequestMapping("api/emc/v1")

public class MailPDFController {

	private static final Logger logger = LoggerFactory.getLogger(MailPDFController.class);

	@Autowired
	private AWSEmailService awsEmailService;

	@GetMapping("/sendPDFinMail/{userName}/{emcId}/{clientName}")
	public ResponseEntity<byte[]> sendFinalPDF(@PathVariable String userName, @PathVariable Integer emcId,
			@PathVariable String clientName) throws Exception {
		logger.info("called sendFinalPDF function userName: {},emcId : {}, clientName : {}", userName, emcId,
				clientName);

		awsEmailService.sendEmailPDF(userName, emcId, emcId, clientName);

		return new ResponseEntity<byte[]>(HttpStatus.OK);
	}
}
