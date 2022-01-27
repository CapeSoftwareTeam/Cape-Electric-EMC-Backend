package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.repository.ElectromagneticCompatabilityRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.ElectromagneticCompatabilityService;

@Service
public class ElectromagneticCompatabilityServiceImpl implements ElectromagneticCompatabilityService {

	
	@Autowired
	PowerEarthingDataRepository powerEarthingDataRepository;
	
	@Autowired
	ElectromagneticCompatabilityRepository electromagneticCompatabilityRepository;
	@Autowired
	private FacilityDataRepository facilityDataRepository;

	@Override
	public void saveElectromagneticCompatability(ElectromagneticCompatability electromagneticCompatability)
			throws ElectromagneticCompatabilityException {
		if (electromagneticCompatability != null && electromagneticCompatability.getUserName() != null) {
			Optional<FacilityData> facilityDataRep = facilityDataRepository
					.findByEmcId(electromagneticCompatability.getEmcId());
			Optional<PowerEarthingData> powerEarthingDataRep = powerEarthingDataRepository
					.findByEmcId(electromagneticCompatability.getEmcId());
			Optional<ElectromagneticCompatability> electromagneticDataRep = electromagneticCompatabilityRepository
					.findByEmcId(electromagneticCompatability.getEmcId());
			if (facilityDataRep.isPresent()
					&& facilityDataRep.get().getEmcId().equals(electromagneticCompatability.getEmcId())) {
				if (powerEarthingDataRep.isPresent()
						&& powerEarthingDataRep.get().getEmcId().equals(electromagneticCompatability.getEmcId())) {
					if (!electromagneticDataRep.isPresent()) {
						electromagneticCompatability.setCreatedDate(LocalDateTime.now());
						electromagneticCompatability.setCreatedBy(electromagneticCompatability.getUserName());
						electromagneticCompatabilityRepository.save(electromagneticCompatability);
					} else {
						throw new ElectromagneticCompatabilityException("Given ElectromagneticCompatability Already Exists");
					}
				} else {
					throw new ElectromagneticCompatabilityException("Power and Earthing Data Not Filled");
				}
			} else {
				throw new ElectromagneticCompatabilityException("FacilityData Not Filled");

			}

		} else {
			throw new ElectromagneticCompatabilityException("Invalid Inputs");
		}

	}

	@Override
	public List<ElectromagneticCompatability> retrieveElectromagneticCompatability(String userName, Integer emcId)
			throws ElectromagneticCompatabilityException {
		if (userName != null) {
			List<ElectromagneticCompatability> electromagneticDataRep = electromagneticCompatabilityRepository
					.findByUserNameAndEmcId(userName, emcId);
			if (electromagneticDataRep != null && !electromagneticDataRep.isEmpty()) {
				return electromagneticDataRep;
			} else {
				throw new ElectromagneticCompatabilityException(
						"Given UserName & Id doesn't exist in ElectromagneticCompatability Details");
			}
		} else {
			throw new ElectromagneticCompatabilityException("Invalid Inputs");
		}

	}

	@Override
	public void updateElectromagneticCompatability(ElectromagneticCompatability electromagneticCompatability)
			throws ElectromagneticCompatabilityException {

		if (electromagneticCompatability != null && electromagneticCompatability.getEmcId() != null
				&& electromagneticCompatability.getEmcId() != 0 && electromagneticCompatability.getUserName() != null) {
			Optional<ElectromagneticCompatability> electromagneticDataRep = electromagneticCompatabilityRepository
					.findByEmcId(electromagneticCompatability.getEmcId());

			if (electromagneticDataRep.isPresent()
					&& electromagneticDataRep.get().getEmcId().equals(electromagneticCompatability.getEmcId())) {
				electromagneticCompatability.setUpdatedDate(LocalDateTime.now());
				electromagneticCompatability.setUpdatedBy(electromagneticCompatability.getUserName());
				electromagneticCompatabilityRepository.save(electromagneticCompatability);
			} else {
				throw new ElectromagneticCompatabilityException("Given Emc Id is Invalid");
			}

		} else {
			throw new ElectromagneticCompatabilityException("Invalid inputs");
		}

	}

}
