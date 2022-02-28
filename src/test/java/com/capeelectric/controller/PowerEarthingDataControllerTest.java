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

import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.service.impl.PowerEarthingDataServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class PowerEarthingDataControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(PowerEarthingDataControllerTest.class);

	@InjectMocks
	private PowerEarthingDataController powerEarthingDataController;

	@MockBean
	private PowerEarthingDataServiceImpl powerEarthingDataServiceImpl;

	@MockBean
	private PowerEarthingDataException powerEarthingDataException;

	private PowerEarthingData powerEarthingData;

	{
		powerEarthingData = new PowerEarthingData();
		powerEarthingData.setEmcId(1);
		powerEarthingData.setUserName("LVsystem@gmail.com");

	}

	@Test
	public void testSavePowerEarthingData() throws PowerEarthingDataException {
		logger.info("testSavePowerEarthingData Function Started");

		ResponseEntity<String> savePowerEarthingData = powerEarthingDataController
				.savePowerEarthingData(powerEarthingData);
		assertEquals(savePowerEarthingData.getBody(), "PowerEarthingData Successfully Saved");

		logger.info("testSavePowerEarthingData Function Ended");
	}

	@Test
	public void testRetrievePowerEarthingData() throws PowerEarthingDataException {
		List<PowerEarthingData> arrayList = new ArrayList<>();
		arrayList.add(powerEarthingData);

		logger.info("testRetrievePowerEarthingData Function Started");

		when(powerEarthingDataServiceImpl.retrievePowerEarthingData("LVsystem@gmail.com", 1)).thenReturn(arrayList);
		ResponseEntity<List<PowerEarthingData>> retrievePowerEarthingData = powerEarthingDataController
				.retrievePowerEarthingData("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrievePowerEarthingData.getStatusCode());

		logger.info("testRetrievePowerEarthingData Function Ended");

	}

	@Test
	public void testUpdatePowerEarthingData() throws PowerEarthingDataException {

		logger.info("testUpdatePowerEarthingData Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = powerEarthingDataController
				.updatePowerEarthingData(powerEarthingData);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdatePowerEarthingData Function Ended");
	}
}
