package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.capeelectric.exception.EmcFinalReportException;
import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.EmcFinalReport;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.service.impl.EmcFinalReportServiceImpl;
import com.capeelectric.service.impl.FacilityDataServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EmcFinalReportControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(EmcFinalReportControllerTest.class);

	@InjectMocks
	private EmcFinalReportController emcFinalReportController;

	@MockBean
	private EmcFinalReportServiceImpl emcFinalReportServiceImpl;

	@MockBean
	private EmcFinalReportException emcFinalReportException;

	private EmcFinalReport emcFinalReport;

	{
		emcFinalReport = new EmcFinalReport();
		emcFinalReport.setEmcId(1);
		emcFinalReport.setUserName("LVsystem@gmail.com");
		emcFinalReport.setElectromagneticCompatability(new ElectromagneticCompatability());
		emcFinalReport.setFacilityData(new FacilityData());
		emcFinalReport.setPowerEarthingData(new PowerEarthingData());

	}

	@Test
	public void testRetrieveEmcReports() throws EmcFinalReportException {
		logger.info("testRetrieveEmcReports Function Started");

		when(emcFinalReportServiceImpl.retrieveEmcReports("LVsystem@gmail.com", 1))
				.thenReturn(Optional.of(emcFinalReport));

		ResponseEntity<Optional<EmcFinalReport>> finalReport = emcFinalReportController
				.retrieveEmcReports("LVsystem@gmail.com", 1);
		assertEquals(finalReport.getStatusCode(), HttpStatus.OK);
		logger.info("testRetrieveEmcReports Function Started");

	}

}
