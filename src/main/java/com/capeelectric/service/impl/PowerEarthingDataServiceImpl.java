package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.PowerEarthingDataService;

@Service
public class PowerEarthingDataServiceImpl implements PowerEarthingDataService {

	@Autowired
	PowerEarthingDataRepository powerEarthingDataRepository;

	@Override
	public void savePowerEarthingData(PowerEarthingData powerEarthingData) throws PowerEarthingDataException {
		if (powerEarthingData != null && powerEarthingData.getUserName() != null) {
			Optional<PowerEarthingData> powerEarthingDataRep = powerEarthingDataRepository
					.findByEmcId(powerEarthingData.getEmcId());
			if (!powerEarthingDataRep.isPresent()) {
				powerEarthingData.setCreatedDate(LocalDateTime.now());
				powerEarthingData.setCreatedBy(powerEarthingData.getUserName());
				powerEarthingDataRepository.save(powerEarthingData);
			} else {
				throw new PowerEarthingDataException("User  name  already exists");
			}

		} else {
			throw new PowerEarthingDataException("Invalid Inputs");
		}

	}

	@Override
	public List<PowerEarthingData> retrievePowerEarthingData(String userName, Integer emcId)
			throws PowerEarthingDataException {
		if (userName != null) {
			List<PowerEarthingData> powerEarthingDataRep = powerEarthingDataRepository.findByUserNameAndEmcId(userName,
					emcId);
			if (powerEarthingDataRep != null && !powerEarthingDataRep.isEmpty()) {
				return powerEarthingDataRep;
			} else {
				throw new PowerEarthingDataException("Given UserName & Id doesn't exist in Basic Lps Details");
			}
		} else {
			throw new PowerEarthingDataException("Invalid Inputs");
		}
	}

	@Override
	public void updatePowerEarthingData(PowerEarthingData powerEarthingData) throws PowerEarthingDataException {
		if (powerEarthingData != null && powerEarthingData.getEmcId() != null && powerEarthingData.getEmcId() != 0
				&& powerEarthingData.getUserName() != null) {
			Optional<PowerEarthingData> powerEarthingDataRep = powerEarthingDataRepository
					.findByEmcId(powerEarthingData.getEmcId());

			if (powerEarthingDataRep.isPresent()
					&& powerEarthingDataRep.get().getEmcId().equals(powerEarthingData.getEmcId())) {
				powerEarthingData.setUpdatedDate(LocalDateTime.now());
				powerEarthingData.setUpdatedBy(powerEarthingData.getUserName());
				powerEarthingDataRepository.save(powerEarthingData);
			} else {
				throw new PowerEarthingDataException("Given Basic LPS Id is Invalid");
			}

		} else {
			throw new PowerEarthingDataException("Invalid inputs");
		}

	}

}
