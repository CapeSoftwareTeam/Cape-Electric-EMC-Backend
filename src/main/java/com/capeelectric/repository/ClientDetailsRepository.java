package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.ClientDetails;
import com.capeelectric.model.ElectromagneticCompatability;
import com.capeelectric.model.FacilityData;

@Repository
public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Integer> {

	Optional<ClientDetails> findByUserName(String userName);

	List<ClientDetails> findByEmcId(Integer emcId);

	Optional<ClientDetails> findByUserNameAndEmcId(String userName, Integer emcId);

}
