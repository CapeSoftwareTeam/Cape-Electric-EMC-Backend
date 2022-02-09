package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.service.impl.ElectromagneticCompatabilityServiceImpl;
import com.capeelectric.service.impl.PowerEarthingDataServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class ElectromagneticCompatabilityControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(ElectromagneticCompatabilityControllerTest.class);

	@InjectMocks
	private ElectromagneticCompatabilityController electromagneticCompatabilityController;

	@MockBean
	private ElectromagneticCompatabilityServiceImpl electromagneticCompatabilityServiceImpl;

	@MockBean
	private ElectromagneticCompatabilityException electromagneticCompatabilityException;

	private ElectromagneticCompatability electromagneticCompatability;

	{
		electromagneticCompatability = new ElectromagneticCompatability();
		electromagneticCompatability.setEmcId(1);
		electromagneticCompatability.setUserName("LVsystem@gmail.com");

	}

	@Test
	public void testSaveElectromagneticCompatability() throws ElectromagneticCompatabilityException {
		logger.info("testSaveElectromagneticCompatability Function Started");

		ResponseEntity<String> saveElectromagneticCompatability = electromagneticCompatabilityController
				.saveElectromagneticCompatability(electromagneticCompatability);
		assertEquals(saveElectromagneticCompatability.getBody(), "Electromagnetic Compatability Successfully Saved");

		logger.info("testSaveElectromagneticCompatability Function Ended");
	}

	@Test
	public void testRetrieveElectromagneticCompatability() throws ElectromagneticCompatabilityException {
		List<ElectromagneticCompatability> arrayList = new ArrayList<>();
		arrayList.add(electromagneticCompatability);

		logger.info("testRetrieveElectromagneticCompatability Function Started");

		when(electromagneticCompatabilityServiceImpl.retrieveElectromagneticCompatability("LVsystem@gmail.com", 1))
				.thenReturn(arrayList);
		ResponseEntity<List<ElectromagneticCompatability>> retrieveFacilityData = electromagneticCompatabilityController
				.retrieveElectromagneticCompatability("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveFacilityData.getStatusCode());

		logger.info("testRetrieveElectromagneticCompatability Function Ended");

	}

	@Test
	public void testUpdateElectromagneticCompatability() throws ElectromagneticCompatabilityException {

		logger.info("testUpdateElectromagneticCompatability Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = electromagneticCompatabilityController
				.updateElectromagneticCompatability(electromagneticCompatability);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateElectromagneticCompatability Function Ended");
	}
}
