package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.exception.FacilityDataException;
import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.ElectromagneticCompatabilityRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.ClientDetailsPDFService;
import com.capeelectric.service.ElectromagneticCompatabilityService;
import com.capeelectric.service.ElectromagneticPDFService;
import com.capeelectric.service.FacilityDataPDFService;
import com.capeelectric.service.PowerEarthingDataPDFService;
import com.capeelectric.service.PrintFinalPDFService;

@Service
public class ElectromagneticCompatabilityServiceImpl implements ElectromagneticCompatabilityService {

	private static final Logger logger = LoggerFactory.getLogger(ElectromagneticCompatabilityServiceImpl.class);

	@Autowired
	PowerEarthingDataRepository powerEarthingDataRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	ElectromagneticCompatabilityRepository electromagneticCompatabilityRepository;

	@Autowired
	private FacilityDataRepository facilityDataRepository;

	private ClientDetails clientDetails;

	@Autowired
	private ClientDetailsPDFService clientDetailsPDFService;

	@Autowired
	private FacilityDataPDFService facilityDataPDFService;

	@Autowired
	private PowerEarthingDataPDFService powerEarthingDataPDFService;

	@Autowired
	private ElectromagneticPDFService electromagneticPDFService;

	@Autowired
	private PrintFinalPDFService printFinalPDFService;

	@Override
	public void saveElectromagneticCompatability(ElectromagneticCompatability electromagneticCompatability)
			throws ElectromagneticCompatabilityException, ClientDetailsException, FacilityDataException,
			PowerEarthingDataException, Exception {
		if (electromagneticCompatability != null && electromagneticCompatability.getUserName() != null) {
			Optional<FacilityData> facilityDataRep = facilityDataRepository
					.findByEmcId(electromagneticCompatability.getEmcId());
			Optional<PowerEarthingData> powerEarthingDataRep = powerEarthingDataRepository
					.findByEmcId(electromagneticCompatability.getEmcId());
			Optional<ElectromagneticCompatability> electromagneticDataRep = electromagneticCompatabilityRepository
					.findByEmcId(electromagneticCompatability.getEmcId());
			Optional<ClientDetails> clientDetailsRepo = clientDetailsRepository.findByUserNameAndEmcId(
					electromagneticCompatability.getUserName(), electromagneticCompatability.getEmcId());
			if (clientDetailsRepo.isPresent()
					&& clientDetailsRepo.get().getEmcId().equals(electromagneticCompatability.getEmcId())) {
				if (facilityDataRep.isPresent()
						&& facilityDataRep.get().getEmcId().equals(electromagneticCompatability.getEmcId())) {
					if (powerEarthingDataRep.isPresent()
							&& powerEarthingDataRep.get().getEmcId().equals(electromagneticCompatability.getEmcId())) {
						if (!electromagneticDataRep.isPresent()) {
							electromagneticCompatability.setCreatedDate(LocalDateTime.now());
							electromagneticCompatability.setCreatedBy(electromagneticCompatability.getUserName());
							electromagneticCompatability.setUpdatedBy(electromagneticCompatability.getUserName());
							electromagneticCompatability.setUpdatedDate(LocalDateTime.now());
							electromagneticCompatabilityRepository.save(electromagneticCompatability);
							logger.debug("Electro Magnetic Compatability  Details Successfully Saved in DB");

							clientDetailsRepo = clientDetailsRepository.findByUserNameAndEmcId(
									electromagneticCompatability.getUserName(),
									electromagneticCompatability.getEmcId());
							if (clientDetailsRepo.isPresent() && clientDetailsRepo.get().getEmcId()
									.equals(electromagneticCompatability.getEmcId())) {
								clientDetails = clientDetailsRepo.get();
								clientDetails.setAllStepsCompleted("AllStepCompleted");
								clientDetails.setUpdatedBy(electromagneticCompatability.getUserName());
								clientDetails.setUpdatedDate(LocalDateTime.now());
								clientDetailsRepository.save(clientDetails);
								logger.debug("AllStepCompleted information saved ClientDetails table in DB"
										+ electromagneticCompatability.getUserName());

								clientDetailsPDFService.printClientDetails(electromagneticCompatability.getUserName(),
										electromagneticCompatability.getEmcId());
								logger.debug("PDF printClientDetails() function called successfully");

								facilityDataPDFService.printFacilityDataDetails(
										electromagneticCompatability.getUserName(),
										electromagneticCompatability.getEmcId());
								logger.debug("PDF printFacilityDataDetails() function called successfully");

								powerEarthingDataPDFService.printPowerEarthingData(
										electromagneticCompatability.getUserName(),
										electromagneticCompatability.getEmcId());
								logger.debug("PDF printPowerEarthingData() function called successfully");

								electromagneticPDFService.printElectromagneticData(
										electromagneticCompatability.getUserName(),
										electromagneticCompatability.getEmcId());
								logger.debug("PDF printElectromagneticData() function called successfully");

								printFinalPDFService.printFinalPDF(electromagneticCompatability.getUserName(),
										electromagneticCompatability.getEmcId(),clientDetails.getClientName());
								logger.debug("PDF printFinalPDF() function called successfully");

							} else {
								logger.error("EMC-Id Information not Available in ClientDetails_Table");
								throw new ElectromagneticCompatabilityException(
										"EMC-Id Information not Available in ClientDetails_Table");
							}

						} else {
							logger.error("Given ElectromagneticCompatability Already Exists");
							throw new ElectromagneticCompatabilityException(
									"Given ElectromagneticCompatability Already Exists");
						}
					} else {
						logger.error("Power and Earthing Data Not Filled");
						throw new ElectromagneticCompatabilityException("Power and Earthing Data Not Filled");
					}
				} else {
					logger.error("FacilityData Not Filled");
					throw new ElectromagneticCompatabilityException("FacilityData Not Filled");

				}
			} else {
				logger.error("Client Details Not Filled");
				throw new ElectromagneticCompatabilityException("Client Details Not Filled");

			}

		} else {
			logger.error("Invalid Inputs");
			throw new ElectromagneticCompatabilityException("Invalid Inputs");
		}

	}

	@Override
	public List<ElectromagneticCompatability> retrieveElectromagneticCompatability(String userName, Integer emcId)
			throws ElectromagneticCompatabilityException {
		if (userName != null && !userName.isEmpty() && emcId != null) {
			List<ElectromagneticCompatability> electromagneticDataRep = electromagneticCompatabilityRepository
					.findByUserNameAndEmcId(userName, emcId);
			if (electromagneticDataRep != null && !electromagneticDataRep.isEmpty()) {
				return electromagneticDataRep;
			} else {
				logger.error("Given UserName & Id doesn't exist in ElectromagneticCompatability Details");
				throw new ElectromagneticCompatabilityException(
						"Given UserName & Id doesn't exist in ElectromagneticCompatability Details");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new ElectromagneticCompatabilityException("Invalid Inputs");
		}

	}

	@Override
	public void updateElectromagneticCompatability(ElectromagneticCompatability electromagneticCompatability)
			throws ElectromagneticCompatabilityException {

		if (electromagneticCompatability != null && electromagneticCompatability.getEmcId() != null
				&& electromagneticCompatability.getUserName() != null) {
			Optional<ElectromagneticCompatability> electromagneticDataRep = electromagneticCompatabilityRepository
					.findByEmcId(electromagneticCompatability.getEmcId());

			if (electromagneticDataRep.isPresent()
					&& electromagneticDataRep.get().getEmcId().equals(electromagneticCompatability.getEmcId())) {
				electromagneticCompatability.setUpdatedDate(LocalDateTime.now());
				electromagneticCompatability.setUpdatedBy(electromagneticCompatability.getUserName());
				electromagneticCompatabilityRepository.save(electromagneticCompatability);
			} else {
				logger.error("Given Emc Id is Invalid");
				throw new ElectromagneticCompatabilityException("Given Emc Id is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new ElectromagneticCompatabilityException("Invalid inputs");
		}

	}

}
