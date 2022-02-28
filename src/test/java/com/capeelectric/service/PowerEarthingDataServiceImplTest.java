package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.impl.PowerEarthingDataServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class PowerEarthingDataServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(PowerEarthingDataServiceImplTest.class);

	@InjectMocks
	private PowerEarthingDataServiceImpl powerEarthingDataServiceImpl;

	@MockBean
	private PowerEarthingDataRepository powerEarthingDataRepository;

	@MockBean
	private PowerEarthingDataException powerEarthingDataException;

	@MockBean
	private FacilityDataRepository facilityDataRepository;

	@MockBean
	private ClientDetailsRepository clientDetailsRepository;

	private PowerEarthingData powerEarthingData;

	private FacilityData facilityData;

	{
		powerEarthingData = new PowerEarthingData();
		powerEarthingData.setEmcId(1);
		powerEarthingData.setUserName("LVsystem@gmail.com");

	}

	{
		facilityData = new FacilityData();
		facilityData.setEmcId(1);
		facilityData.setUserName("LVsystem@gmail.com");

	}
	private ClientDetails clientDetails;

	{
		clientDetails = new ClientDetails();
		clientDetails.setEmcId(1);
		clientDetails.setUserName("LVsystem@gmail.com");

	}

	@Test
	public void testSavePowerEarthingData() throws PowerEarthingDataException {
		when(powerEarthingDataRepository.findByEmcId(1)).thenReturn(Optional.of(powerEarthingData));
		when(facilityDataRepository.findByEmcId(1)).thenReturn(Optional.of(facilityData));
		when(clientDetailsRepository.findByUserNameAndEmcId("LVsystem@gmail.com", 1))
				.thenReturn(Optional.of(clientDetails));

		PowerEarthingDataException assertThrows_1 = Assertions.assertThrows(PowerEarthingDataException.class,
				() -> powerEarthingDataServiceImpl.savePowerEarthingData(powerEarthingData));
		assertEquals(assertThrows_1.getMessage(), "Given PowerEarthingData Already Exists");

		powerEarthingData.setUserName(null);

		PowerEarthingDataException assertThrows_2 = Assertions.assertThrows(PowerEarthingDataException.class,
				() -> powerEarthingDataServiceImpl.savePowerEarthingData(powerEarthingData));
		assertEquals(assertThrows_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrievePowerEarthingData() throws PowerEarthingDataException {
		List<PowerEarthingData> arrayList = new ArrayList<PowerEarthingData>();
		arrayList.add(powerEarthingData);

		when(powerEarthingDataRepository.findByUserNameAndEmcId("LVsystem@gmail.com", 1)).thenReturn(arrayList);

		PowerEarthingDataException assertThrows_1 = Assertions.assertThrows(PowerEarthingDataException.class,
				() -> powerEarthingDataServiceImpl.retrievePowerEarthingData("cape", 1));
		assertEquals(assertThrows_1.getMessage(), "Given UserName & Id doesn't exist in PowerEarthingData Details");

		PowerEarthingDataException assertThrows_2 = Assertions.assertThrows(PowerEarthingDataException.class,
				() -> powerEarthingDataServiceImpl.retrievePowerEarthingData(null, null));
		assertEquals(assertThrows_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdatePowerEarthingData() throws PowerEarthingDataException {

		when(powerEarthingDataRepository.findByEmcId(1)).thenReturn(Optional.of(powerEarthingData));
		powerEarthingDataServiceImpl.updatePowerEarthingData(powerEarthingData);

		powerEarthingData.setUserName(null);
		PowerEarthingDataException assertThrows_1 = Assertions.assertThrows(PowerEarthingDataException.class,
				() -> powerEarthingDataServiceImpl.updatePowerEarthingData(powerEarthingData));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");

		powerEarthingData.setEmcId(12);
		powerEarthingData.setUserName("cape");
		PowerEarthingDataException assertThrows_2 = Assertions.assertThrows(PowerEarthingDataException.class,
				() -> powerEarthingDataServiceImpl.updatePowerEarthingData(powerEarthingData));
		assertEquals(assertThrows_2.getMessage(), "Given Emc is Invalid");

	}
}
