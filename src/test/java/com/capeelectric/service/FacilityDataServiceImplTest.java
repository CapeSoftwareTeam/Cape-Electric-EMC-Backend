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

import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.FacilityData;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.service.impl.FacilityDataServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class FacilityDataServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(FacilityDataServiceImplTest.class);
	@InjectMocks
	private FacilityDataServiceImpl facilityDataServiceImpl;

	@MockBean
	private FacilityDataRepository facilityDataRepository;

	@MockBean
	private ClientDetailsRepository clientDetailsRepository;

	@MockBean
	private FacilityDataException facilityDataException;

	private FacilityData facilityData;

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
	public void testAddFacilityData() throws FacilityDataException {
		when(facilityDataRepository.findByEmcId(1)).thenReturn(Optional.of(facilityData));
		when(clientDetailsRepository.findByUserNameAndEmcId("LVsystem@gmail.com", 1))
				.thenReturn(Optional.of(clientDetails));

		FacilityDataException assertThrows_1 = Assertions.assertThrows(FacilityDataException.class,
				() -> facilityDataServiceImpl.addFacilityData(facilityData));
		assertEquals(assertThrows_1.getMessage(), "Given FacilityData Already Exists");

		facilityData.setEmcId(1);
		facilityData.setUserName(null);
		FacilityDataException assertThrows_2 = Assertions.assertThrows(FacilityDataException.class,
				() -> facilityDataServiceImpl.addFacilityData(facilityData));
		assertEquals(assertThrows_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveFacilityData() throws FacilityDataException {
		List<FacilityData> arrayList = new ArrayList<FacilityData>();
		arrayList.add(facilityData);

		when(facilityDataRepository.findByUserNameAndEmcId("LVsystem@gmail.com", 1)).thenReturn(arrayList);
		facilityDataServiceImpl.retrieveFacilityData("LVsystem@gmail.com", 1);

		FacilityDataException assertThrows_1 = Assertions.assertThrows(FacilityDataException.class,
				() -> facilityDataServiceImpl.retrieveFacilityData("cape", 1));
		assertEquals(assertThrows_1.getMessage(), "Given UserName & Id doesn't exist in FacilityData Details");

		FacilityDataException assertThrows_2 = Assertions.assertThrows(FacilityDataException.class,
				() -> facilityDataServiceImpl.retrieveFacilityData(null, 1));
		assertEquals(assertThrows_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateFacilityData() throws FacilityDataException {

		when(facilityDataRepository.findByEmcId(1)).thenReturn(Optional.of(facilityData));
		facilityDataServiceImpl.updateFacilityData(facilityData);

		FacilityDataException assertThrows_1 = Assertions.assertThrows(FacilityDataException.class,
				() -> facilityDataServiceImpl.updateFacilityData(null));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");

		facilityData.setEmcId(8);

		FacilityDataException assertThrows_2 = Assertions.assertThrows(FacilityDataException.class,
				() -> facilityDataServiceImpl.updateFacilityData(facilityData));
		assertEquals(assertThrows_2.getMessage(), "Given Emc Id is Invalid");

	}

}
