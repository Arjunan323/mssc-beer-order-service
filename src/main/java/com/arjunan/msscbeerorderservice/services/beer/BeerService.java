package com.arjunan.msscbeerorderservice.services.beer;

import com.arjunan.msscbeerorderservice.web.model.BeerDTO;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDTO> getBeerById(UUID id);

    Optional<BeerDTO> getBeerByUpc(String upc);
}
