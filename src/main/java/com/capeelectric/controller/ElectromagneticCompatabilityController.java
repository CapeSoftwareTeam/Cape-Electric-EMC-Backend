package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.service.ElectromagneticCompatabilityService;

/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/emc/v1")
public class ElectromagneticCompatabilityController {

	private static final Logger logger = LoggerFactory.getLogger(ElectromagneticCompatabilityController.class);

	@Autowired
	private ElectromagneticCompatabilityService electromagneticCompatabilityService;

	@PostMapping("/saveElectromagneticCompatability")
	public ResponseEntity<String> saveElectromagneticCompatability(@RequestBody ElectromagneticCompatability electromagneticCompatability)
			throws  ElectromagneticCompatabilityException {
		logger.debug("started saveElectromagneticCompatability function userName: {},emcId : {}", electromagneticCompatability.getUserName(),
				electromagneticCompatability.getEmcId());

		electromagneticCompatabilityService.saveElectromagneticCompatability(electromagneticCompatability);
		logger.debug("ended savePowerEarthingData function");

		return new ResponseEntity<String>("Electromagnetic Compatability Successfully Saved", HttpStatus.OK);

	}
	
	@GetMapping("/retrieveElectromagneticCompatability/{userName}/{emcId}")
	public ResponseEntity<List<ElectromagneticCompatability>> retrieveElectromagneticCompatability(@PathVariable String userName,
			@PathVariable Integer emcId) throws  ElectromagneticCompatabilityException {
		logger.info("called retrieveFacilityData function UserName: {}, emcId : {}", userName, emcId);
		return new ResponseEntity<List<ElectromagneticCompatability>>(electromagneticCompatabilityService.retrieveElectromagneticCompatability(userName, emcId),
				HttpStatus.OK);
	}
	@PutMapping("/updateElectromagneticCompatability")
	public ResponseEntity<String> updateElectromagneticCompatability(@RequestBody ElectromagneticCompatability electromagneticCompatability)
			throws  ElectromagneticCompatabilityException {
		logger.info("called updatePowerEarthingData function UserName : {},emcId : {}", electromagneticCompatability.getUserName(),
				electromagneticCompatability.getEmcId());
		electromagneticCompatabilityService.updateElectromagneticCompatability(electromagneticCompatability);
		return new ResponseEntity<String>("ElectromagneticCompatability Updated Successfully", HttpStatus.OK);
	}
}
