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

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.service.impl.ClientDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class ClientDetailsServiceTest {
	@InjectMocks
	private ClientDetailsServiceImpl clientDetailsServiceImpl;

	@MockBean
	private ClientDetailsRepository clientDetailsRepository;

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
		List<ClientDetails> arrayList = new ArrayList<ClientDetails>();
		arrayList.add(clientDetails);
		clientDetailsServiceImpl.saveClientDetails(clientDetails);

//		when(clientDetailsRepository.findByUserName("LVsystem@gmail.com")).thenReturn(arrayList);
//
//		clientDetails.setEmcId(1);
//		clientDetails.setUserName("LVsystem@gmail.com");
//
//		ClientDetailsException assertThrows_1 = Assertions.assertThrows(ClientDetailsException.class,
//				() -> clientDetailsServiceImpl.saveClientDetails(clientDetails));
//		assertEquals(assertThrows_1.getMessage(), "Given UserName Already Exists");

		clientDetails.setEmcId(13);
		clientDetails.setUserName(null);
		ClientDetailsException assertThrows_2 = Assertions.assertThrows(ClientDetailsException.class,
				() -> clientDetailsServiceImpl.saveClientDetails(clientDetails));
		assertEquals(assertThrows_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveClientDetails() throws ClientDetailsException {
		List<ClientDetails> arrayList = new ArrayList<ClientDetails>();
		arrayList.add(clientDetails);

		when(clientDetailsRepository.findByEmcId(1)).thenReturn(arrayList);
		clientDetailsServiceImpl.retrieveClientDetails("LVsystem@gmail.com", 1);

		ClientDetailsException assertThrows_1 = Assertions.assertThrows(ClientDetailsException.class,
				() -> clientDetailsServiceImpl.retrieveClientDetails("LVsystem@gmail.com", 12));
		assertEquals(assertThrows_1.getMessage(), "Given EmcId doesn't exist in ClientDetails");

		ClientDetailsException assertThrows_2 = Assertions.assertThrows(ClientDetailsException.class,
				() -> clientDetailsServiceImpl.retrieveClientDetails(null, 1));
		assertEquals(assertThrows_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateClientDetails() throws ClientDetailsException {

		when(clientDetailsRepository.findByUserNameAndEmcId("LVsystem@gmail.com", 1))
				.thenReturn(Optional.of(clientDetails));
		clientDetailsServiceImpl.updateClientDetails(clientDetails);

		ClientDetailsException assertThrows_1 = Assertions.assertThrows(ClientDetailsException.class,
				() -> clientDetailsServiceImpl.updateClientDetails(null));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");

		clientDetails.setEmcId(8);
		ClientDetailsException assertThrows_2 = Assertions.assertThrows(ClientDetailsException.class,
				() -> clientDetailsServiceImpl.updateClientDetails(clientDetails));
		assertEquals(assertThrows_2.getMessage(), "Given Emc Id is Invalid");

	}

}
