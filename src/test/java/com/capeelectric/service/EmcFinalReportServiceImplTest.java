package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.exception.EmcFinalReportException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.EmcFinalReport;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.ElectromagneticCompatabilityRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.impl.EmcFinalReportServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EmcFinalReportServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(EmcFinalReportServiceImplTest.class);

	@InjectMocks
	private EmcFinalReportServiceImpl emcFinalReportServiceImpl;

	@MockBean
	private FacilityDataRepository facilityDataRepository;

	@MockBean
	private PowerEarthingDataRepository powerEarthingDataRepository;

	@MockBean
	private ElectromagneticCompatabilityRepository electromagneticCompatabilityRepository;

	@MockBean
	private EmcFinalReportException emcFinalReportException;

	@MockBean
	private ClientDetailsRepository clientDetailsRepository;

	private EmcFinalReport emcFinalReport;

	private FacilityData facilityData;

	{
		emcFinalReport = new EmcFinalReport();
		emcFinalReport.setEmcId(1);
		emcFinalReport.setUserName("LVsystem@gmail.com");
		emcFinalReport.setElectromagneticCompatability(retriveElectromagneticCompatability());
		emcFinalReport.setFacilityData(retriveFacilityData());
		emcFinalReport.setPowerEarthingData(retrivePowerEarthingData());

	}

	{

		facilityData = new FacilityData();
		facilityData.setUserName("LVsystem@gmail.com");
	}

	@Test
	public void testRetrieveEmcReports() throws EmcFinalReportException {

		logger.info("testRetrieveEmcReports method started");

		when(facilityDataRepository.findByEmcId(1)).thenReturn(Optional.of(retriveFacilityData()));
		when(powerEarthingDataRepository.findByEmcId(1)).thenReturn(Optional.of(retrivePowerEarthingData()));
		when(electromagneticCompatabilityRepository.findByEmcId(1))
				.thenReturn(Optional.of(retriveElectromagneticCompatability()));
		when(clientDetailsRepository.findByUserNameAndEmcId("LVsystem@gmail.com", 1))
				.thenReturn(Optional.of(retriveClientDetails()));

		Optional<EmcFinalReport> retrieveFinalReport = emcFinalReportServiceImpl
				.retrieveEmcReports("LVsystem@gmail.com", 1);
		assertNotNull(retrieveFinalReport);

		EmcFinalReportException finalReportException = Assertions.assertThrows(EmcFinalReportException.class,
				() -> emcFinalReportServiceImpl.retrieveEmcReports(null, 1));
		assertEquals(finalReportException.getMessage(), "Invalid Input");
		logger.info("testRetrieveEmcReports method ended");

	}

	@Test
	public void testRetrieveListOfFacilityData() throws EmcFinalReportException {
		logger.info("testRetrieveListOfFacilityData Function Started");
		List<FacilityData> arrayList = new ArrayList<FacilityData>();
		arrayList.add(facilityData);

		when(facilityDataRepository.findByUserName("LVsystem@gmail.com")).thenReturn(arrayList);

		List<FacilityData> arrayList1 = emcFinalReportServiceImpl.retrieveListOfFacilityData("LVsystem@gmail.com");
		assertTrue(arrayList1.contains(facilityData));

		EmcFinalReportException finalReportException = Assertions.assertThrows(EmcFinalReportException.class,
				() -> emcFinalReportServiceImpl.retrieveListOfFacilityData(null));
		assertEquals(finalReportException.getMessage(), "Invaild Input");
		logger.info("testRetrieveListOfFacilityData Function ended");

	}

	private ElectromagneticCompatability retriveElectromagneticCompatability() {
		ElectromagneticCompatability electromagneticCompatability = new ElectromagneticCompatability();
		electromagneticCompatability.setUserName("LVsystem@gmail.com");
		electromagneticCompatability.setEmcId(1);
		return electromagneticCompatability;
	}

	private PowerEarthingData retrivePowerEarthingData() {
		PowerEarthingData powerEarthingData = new PowerEarthingData();
		powerEarthingData.setUserName("LVsystem@gmail.com");
		powerEarthingData.setEmcId(1);
		return powerEarthingData;
	}

	private FacilityData retriveFacilityData() {
		FacilityData facilityData = new FacilityData();
		facilityData.setUserName("LVsystem@gmail.com");
		facilityData.setEmcId(1);
		return facilityData;
	}

	private ClientDetails retriveClientDetails() {
		ClientDetails clientDetails = new ClientDetails();
		clientDetails.setUserName("LVsystem@gmail.com");
		clientDetails.setEmcId(1);

		return clientDetails;
	}

}
