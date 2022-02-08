package com.capeelectric.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CountryDetailsException;
import com.capeelectric.model.Country;
import com.capeelectric.model.State;
import com.capeelectric.repository.CountryRepository;
import com.capeelectric.repository.StateRepository;
import com.capeelectric.service.CountryDetailsService;

@Service
public class CountryDetailServiceImpl implements CountryDetailsService {
	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	public List<State> fetchStatesByCountryCode(String code) throws CountryDetailsException {

		List<State> state = stateRepository.fetchStatesByCountryCode(code);
		state.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		return state;
	}

	public List<Country> fetchCountries() {

		List<Country> country = (List<Country>) countryRepository.findAll();
		country.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		return country;
	}

	
}