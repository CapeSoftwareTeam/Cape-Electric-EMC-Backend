package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;


public interface ClientDetailsPDFService {

	public void printClientDetails(String userName, Integer emcId, Optional<ClientDetails> clientDetailsRepo) throws ClientDetailsException;

}
