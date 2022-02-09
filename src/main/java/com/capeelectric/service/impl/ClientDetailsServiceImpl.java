package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.FacilityData;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.service.ClientDetailsService;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(ClientDetailsServiceImpl.class);

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Override
	public ClientDetails saveClientDetails(ClientDetails clientDetails) throws ClientDetailsException {
		if (clientDetails != null && clientDetails.getUserName() != null) {
			Optional<ClientDetails> clientDetailsRepo = clientDetailsRepository
					.findByUserName(clientDetails.getUserName());
			if (!clientDetailsRepo.isPresent()) {
				clientDetails.setCreatedDate(LocalDateTime.now());
				clientDetails.setCreatedBy(clientDetails.getUserName());
				return clientDetailsRepository.save(clientDetails);
			} else {
				logger.error("Given UserName and Emc Id Already Exists");
				throw new ClientDetailsException("Given UserName and Emc Id Already Exists");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new ClientDetailsException("Invalid Inputs");
		}

	}

	@Override
	public List<ClientDetails> retrieveClientDetails(String userName, Integer emcId) throws ClientDetailsException {
		if (userName != null && !userName.isEmpty() && emcId != null) {
			List<ClientDetails> clientDetailsRepo = clientDetailsRepository.findByEmcId(emcId);
			if (clientDetailsRepo != null && !clientDetailsRepo.isEmpty()) {
				return clientDetailsRepo;
			} else {
				logger.error("Given EmcId doesn't exist in ClientDetails");
				throw new ClientDetailsException("Given EmcId doesn't exist in ClientDetails");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new ClientDetailsException("Invalid Inputs");
		}
	}

	@Override
	public void updateClientDetails(ClientDetails clientDetails) throws ClientDetailsException {
		if (clientDetails != null && clientDetails.getUserName() != null && clientDetails.getEmcId() != null) {
			Optional<ClientDetails> clientDetailsRepo = clientDetailsRepository
					.findByUserNameAndEmcId(clientDetails.getUserName(), clientDetails.getEmcId());
			if (clientDetailsRepo.isPresent() && clientDetailsRepo.get().getEmcId().equals(clientDetails.getEmcId())) {
				clientDetails.setUpdatedDate(LocalDateTime.now());
				clientDetails.setUpdatedBy(clientDetails.getUserName());
				clientDetailsRepository.save(clientDetails);
			} else {
				logger.error("Given Emc Id is Invalid");
				throw new ClientDetailsException("Given Emc Id is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new ClientDetailsException("Invalid inputs");
		}

	}

}
