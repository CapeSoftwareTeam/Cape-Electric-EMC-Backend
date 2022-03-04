package com.capeelectric.service;

import com.capeelectric.exception.ClientDetailsException;


public interface ClientDetailsPDFService {

	public void printClientDetails(String userName, Integer emcId) throws ClientDetailsException;

}
