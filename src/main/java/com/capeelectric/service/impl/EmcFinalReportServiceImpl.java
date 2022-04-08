package com.capeelectric.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EmcFinalReportException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.EmcFinalReport;
import com.capeelectric.model.FacilityData;
import com.capeelectric.model.PowerEarthingData;
import com.capeelectric.model.State;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.repository.ElectromagneticCompatabilityRepository;
import com.capeelectric.repository.FacilityDataRepository;
import com.capeelectric.repository.PowerEarthingDataRepository;
import com.capeelectric.service.FinalReportService;

@Service
public class EmcFinalReportServiceImpl implements FinalReportService {

	private static final Logger logger = LoggerFactory.getLogger(EmcFinalReportServiceImpl.class);

	@Autowired
	private ElectromagneticCompatabilityRepository electromagneticCompatabilityRepository;

	@Autowired
	private FacilityDataRepository facilityDataRepository;

	@Autowired
	private PowerEarthingDataRepository powerEarthingDataRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Override
	public Optional<EmcFinalReport> retrieveEmcReports(String userName, Integer emcId) throws EmcFinalReportException {

		if (userName != null && !userName.isEmpty() && emcId != null) {
			EmcFinalReport emcFinalReport = new EmcFinalReport();
			emcFinalReport.setUserName(userName);
			emcFinalReport.setEmcId(emcId);

			// ClientDetails Fetch
			logger.debug("fetching process started for ClientDetails");
			Optional<ClientDetails> clientDetailsRepo = clientDetailsRepository.findByUserNameAndEmcId(userName, emcId);
			logger.debug("ClientDetails fetching ended");
			if (clientDetailsRepo.isPresent() && clientDetailsRepo != null) {
				emcFinalReport.setClientDetails(clientDetailsRepo.get());

				// FacilityData Fetch
				logger.debug("fetching process started for FacilityData");
				Optional<FacilityData> facilityDatails = facilityDataRepository.findByEmcId(emcId);
				logger.debug("FacilityData fetching ended");
				if (facilityDatails.isPresent() && facilityDatails != null) {
					emcFinalReport.setFacilityData(facilityDatails.get());

					// PowerEarthingData Fetch
					logger.debug("fetching process started for PowerEarthingData");
					Optional<PowerEarthingData> powerEarthingDatails = powerEarthingDataRepository.findByEmcId(emcId);
					logger.debug("PowerEarthingData fetching ended");
					if (powerEarthingDatails.isPresent() && powerEarthingDatails != null) {
						emcFinalReport.setPowerEarthingData(powerEarthingDatails.get());

						// ElectromagneticCompatability Fetch
						logger.debug("fetching process started for ElectromagneticCompatability");
						Optional<ElectromagneticCompatability> electromagneticDatails = electromagneticCompatabilityRepository
								.findByEmcId(emcId);
						logger.debug("ElectromagneticCompatability fetching ended");
						if (electromagneticDatails.isPresent() && electromagneticDatails != null) {
							emcFinalReport.setElectromagneticCompatability(electromagneticDatails.get());
						}
					}
				}
			}
			return Optional.of(emcFinalReport);
		} else {
			logger.debug("Invalid Input");
			throw new EmcFinalReportException("Invalid Input");

		}
	}

	@Override
	public List<ClientDetails> retrieveListOfClientDetails(String userName) throws EmcFinalReportException {
		if (userName != null) {
			try {
				logger.info("ClientDetails fetching process started");
				List<ClientDetails> clientDetails =  clientDetailsRepository.findByUserName(userName);
				clientDetails.sort((o1, o2) -> o1.getUpdatedDate().compareTo(o2.getUpdatedDate()));
				return clientDetails;
			} catch (Exception e) {
				logger.info("ClientDetails fetching process faild");
				throw new EmcFinalReportException("ClientDetails Not Available");
			}
		} else {
			throw new EmcFinalReportException("Invaild Input");
		}
	}
	
	@Override
	public List<ClientDetails> retrieveAllCLientDetails() throws EmcFinalReportException {
		return (List<ClientDetails>) clientDetailsRepository.findAll();
	}

}
