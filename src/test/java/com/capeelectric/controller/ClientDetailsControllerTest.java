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

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.service.impl.ClientDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class ClientDetailsControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(ClientDetailsControllerTest.class);

	@InjectMocks
	private ClientDetailsController clientDetailsController;

	@MockBean
	private ClientDetailsServiceImpl clientDetailsServiceImpl;

	@MockBean
	private ClientDetailsException clientDetailsException;

	private ClientDetails clientDetails;

	{
		clientDetails = new ClientDetails();
		clientDetails.setEmcId(1);
		clientDetails.setUserName("LVsystem@gmail.com");

	}

	@Test
	public void testSaveClientDetails() throws ClientDetailsException {
		logger.info("testSaveClientDetails Function Started");

		ResponseEntity<ClientDetails> saveClientDetaila = clientDetailsController.saveClientDetails(clientDetails);
		assertEquals(saveClientDetaila.getStatusCode(), HttpStatus.CREATED);

		logger.info("testSaveClientDetails Function Ended");
	}

	@Test
	public void testRetrieveClientDetails() throws ClientDetailsException {
		List<ClientDetails> arrayList = new ArrayList<>();
		arrayList.add(clientDetails);

		logger.info("testRetrieveClientDetails Function Started");

		when(clientDetailsServiceImpl.retrieveClientDetails("LVsystem@gmail.com", 1)).thenReturn(arrayList);
		ResponseEntity<List<ClientDetails>> retrieveFacilityData = clientDetailsController
				.retrieveClientDetails("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveFacilityData.getStatusCode());

		logger.info("testRetrieveClientDetails Function Ended");

	}

	@Test
	public void testUpdateClientDetails() throws ClientDetailsException {

		logger.info("testUpdateClientDetails Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = clientDetailsController.updateClientDetails(clientDetails);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateClientDetails Function Ended");
	}

	@Test
	public void testUpdateClientDetailsStatus() throws ClientDetailsException {

		logger.info("testUpdateClientDetailsStatus Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = clientDetailsController.updateClientDetailsStatus(clientDetails);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateClientDetailsStatus Function Ended");
	}

}
