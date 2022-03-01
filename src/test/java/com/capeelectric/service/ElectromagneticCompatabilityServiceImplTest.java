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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.ElectromagneticCompatabilityRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.impl.ElectromagneticCompatabilityServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class ElectromagneticCompatabilityServiceImplTest {

	@InjectMocks
	private ElectromagneticCompatabilityServiceImpl electromagneticCompatabilityServiceImpl;

	@MockBean
	private ElectromagneticCompatabilityRepository electromagneticCompatabilityRepository;

	@MockBean
	private ElectromagneticCompatabilityException electromagneticCompatabilityException;

	@MockBean
	private PowerEarthingDataException powerEarthingDataException;

	@MockBean
	private PowerEarthingDataRepository powerEarthingDataRepository;

	@MockBean
	private ClientDetailsRepository clientDetailsRepository;

	@MockBean
	private FacilityDataRepository facilityDataRepository;

	private ElectromagneticCompatability electromagneticCompatability;

	private PowerEarthingData powerEarthingData;

	private FacilityData facilityData;

	{
		electromagneticCompatability = new ElectromagneticCompatability();
		electromagneticCompatability.setEmcId(1);
		electromagneticCompatability.setUserName("LVsystem@gmail.com");

	}

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
	public void testSaveElectromagneticCompatability() throws ElectromagneticCompatabilityException {
		when(powerEarthingDataRepository.findByEmcId(1)).thenReturn(Optional.of(powerEarthingData));
		when(facilityDataRepository.findByEmcId(1)).thenReturn(Optional.of(facilityData));
		when(electromagneticCompatabilityRepository.findByEmcId(1))
				.thenReturn(Optional.of(electromagneticCompatability));
		when(clientDetailsRepository.findByUserNameAndEmcId("LVsystem@gmail.com", 1))
				.thenReturn(Optional.of(clientDetails));

		ElectromagneticCompatabilityException assertThrows_1 = Assertions
				.assertThrows(ElectromagneticCompatabilityException.class, () -> electromagneticCompatabilityServiceImpl
						.saveElectromagneticCompatability(electromagneticCompatability));
		assertEquals(assertThrows_1.getMessage(), "Given ElectromagneticCompatability Already Exists");

		electromagneticCompatability.setUserName(null);
		ElectromagneticCompatabilityException assertThrows_2 = Assertions
				.assertThrows(ElectromagneticCompatabilityException.class, () -> electromagneticCompatabilityServiceImpl
						.saveElectromagneticCompatability(electromagneticCompatability));
		assertEquals(assertThrows_2.getMessage(), "Invalid Inputs");

		electromagneticCompatability.setEmcId(4);
		electromagneticCompatability.setUserName("ggggg");
		ElectromagneticCompatabilityException assertThrows_3 = Assertions
				.assertThrows(ElectromagneticCompatabilityException.class, () -> electromagneticCompatabilityServiceImpl
						.saveElectromagneticCompatability(electromagneticCompatability));
		assertEquals(assertThrows_3.getMessage(), "Client Details Not Filled");

	}

	@Test
	public void testRetrieveElectromagneticCompatability() throws ElectromagneticCompatabilityException {
		List<ElectromagneticCompatability> arrayList = new ArrayList<ElectromagneticCompatability>();
		arrayList.add(electromagneticCompatability);

		when(electromagneticCompatabilityRepository.findByUserNameAndEmcId("LVsystem@gmail.com", 1))
				.thenReturn(arrayList);

		ElectromagneticCompatabilityException assertThrows_1 = Assertions.assertThrows(
				ElectromagneticCompatabilityException.class,
				() -> electromagneticCompatabilityServiceImpl.retrieveElectromagneticCompatability("cape", 1));
		assertEquals(assertThrows_1.getMessage(),
				"Given UserName & Id doesn't exist in ElectromagneticCompatability Details");

		ElectromagneticCompatabilityException assertThrows_2 = Assertions.assertThrows(
				ElectromagneticCompatabilityException.class,
				() -> electromagneticCompatabilityServiceImpl.retrieveElectromagneticCompatability(null, null));
		assertEquals(assertThrows_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateElectromagneticCompatability() throws ElectromagneticCompatabilityException {

		when(electromagneticCompatabilityRepository.findByEmcId(1))
				.thenReturn(Optional.of(electromagneticCompatability));
		electromagneticCompatabilityServiceImpl.updateElectromagneticCompatability(electromagneticCompatability);

		electromagneticCompatability.setEmcId(null);
		electromagneticCompatability.setUserName(null);

		ElectromagneticCompatabilityException assertThrows_1 = Assertions
				.assertThrows(ElectromagneticCompatabilityException.class, () -> electromagneticCompatabilityServiceImpl
						.updateElectromagneticCompatability(electromagneticCompatability));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");

		electromagneticCompatability.setEmcId(22);
		electromagneticCompatability.setUserName("cape");

		ElectromagneticCompatabilityException assertThrows_2 = Assertions
				.assertThrows(ElectromagneticCompatabilityException.class, () -> electromagneticCompatabilityServiceImpl
						.updateElectromagneticCompatability(electromagneticCompatability));
		assertEquals(assertThrows_2.getMessage(), "Given Emc Id is Invalid");

	}

}
