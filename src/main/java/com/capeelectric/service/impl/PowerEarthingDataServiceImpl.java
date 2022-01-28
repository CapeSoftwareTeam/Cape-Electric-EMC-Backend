package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.PowerEarthingDataService;

@Service
public class PowerEarthingDataServiceImpl implements PowerEarthingDataService {

	private static final Logger logger = LoggerFactory.getLogger(PowerEarthingDataServiceImpl.class);

	@Autowired
	PowerEarthingDataRepository powerEarthingDataRepository;

	@Autowired
	private FacilityDataRepository facilityDataRepository;

	@Override
	public void savePowerEarthingData(PowerEarthingData powerEarthingData) throws PowerEarthingDataException {
		if (powerEarthingData != null && powerEarthingData.getUserName() != null) {
			Optional<FacilityData> facilityDataRep = facilityDataRepository.findByEmcId(powerEarthingData.getEmcId());

			Optional<PowerEarthingData> powerEarthingDataRep = powerEarthingDataRepository
					.findByEmcId(powerEarthingData.getEmcId());
			if (facilityDataRep.isPresent() && facilityDataRep.get().getEmcId().equals(powerEarthingData.getEmcId())) {
				if (!powerEarthingDataRep.isPresent()) {
					powerEarthingData.setCreatedDate(LocalDateTime.now());
					powerEarthingData.setCreatedBy(powerEarthingData.getUserName());
					powerEarthingDataRepository.save(powerEarthingData);
				} else {
					logger.error("Given PowerEarthingData Already Exists");
					throw new PowerEarthingDataException("Given PowerEarthingData Already Exists");
				}
			} else {
				logger.error("Given FacilityData EMC Id is Invalid");
				throw new PowerEarthingDataException("Given FacilityData EMC Id is Invalid");
			}
		} else {
			logger.error("Invalid Inputs");
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
				logger.error("Given UserName & Id doesn't exist in PowerEarthingData Details");
				throw new PowerEarthingDataException("Given UserName & Id doesn't exist in PowerEarthingData Details");
			}
		} else {
			logger.error("Invalid Inputs");
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
				logger.error("Given Emc is Invalid");
				throw new PowerEarthingDataException("Given Emc is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new PowerEarthingDataException("Invalid inputs");
		}

	}

}
