package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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

import com.capeelectric.exception.FacilityDataException;

import com.capeelectric.model.FacilityData;
import com.capeelectric.service.impl.FacilityDataServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class FacilityDataControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(FacilityDataControllerTest.class);
	@InjectMocks
	private FacilityDataController facilityDataController;

	@MockBean
	private FacilityDataServiceImpl facilityDataServiceImpl;

	@MockBean
	private FacilityDataException facilityDataException;

	private FacilityData facilityData;

	{
		facilityData = new FacilityData();
		facilityData.setEmcId(1);
		facilityData.setUserName("LVsystem@gmail.com");

	}

	@Test
	public void testAddFacilityData() throws FacilityDataException {
		logger.info("testAddFacilityData Function Started");
		ResponseEntity<String> addFacilityData = facilityDataController.addFacilityData(facilityData);
		assertEquals(addFacilityData.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	public void testRetrieveFacilityData() throws FacilityDataException {
		List<FacilityData> arrayList = new ArrayList<>();
		arrayList.add(facilityData);

		logger.info("testRetrieveFacilityData Function Started");

		when(facilityDataServiceImpl.retrieveFacilityData("LVsystem@gmail.com", 1)).thenReturn(arrayList);
		ResponseEntity<List<FacilityData>> retrieveFacilityData = facilityDataController
				.retrieveFacilityData("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveFacilityData.getStatusCode());

		logger.info("testRetrieveFacilityData Function Ended");

	}

	@Test
	public void testUpdateFacilityData() throws FacilityDataException {

		logger.info("testUpdateFacilityData Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = facilityDataController.updateFacilityData(facilityData);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateFacilityData Function Ended");
	}

}
