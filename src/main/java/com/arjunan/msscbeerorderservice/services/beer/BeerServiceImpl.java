package com.arjunan.msscbeerorderservice.services.beer;

import com.arjunan.msscbeerorderservice.web.model.BeerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;


@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields  = true)
@Service
public class BeerServiceImpl implements  BeerService {

    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private final String BEER_UPC_PATH_V1 = "/api/v1/beer/beerUpc/";
    private final RestTemplate restTemplate;

    private String beerServiceHost;

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    public BeerServiceImpl(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }


    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.ofNullable(restTemplate.getForObject(beerServiceHost + BEER_PATH_V1 + id , BeerDTO.class));
    }

    @Override
    public Optional<BeerDTO> getBeerByUpc(String upc) {
        return Optional.ofNullable(restTemplate.getForObject(beerServiceHost + BEER_UPC_PATH_V1 + upc , BeerDTO.class));
    }


}
