package com.arjunan.msscbeerorderservice.web.mappers;

import com.arjunan.msscbeerorderservice.domain.BeerOrderLine;
import com.arjunan.msscbeerorderservice.services.beer.BeerService;
import com.arjunan.msscbeerorderservice.web.model.BeerDTO;
import com.arjunan.msscbeerorderservice.web.model.BeerOrderLineDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Override
    public BeerOrderLineDTO beerOrderLineToBeerOrderLineDTO(BeerOrderLine beerOrderLine) {
        BeerOrderLineDTO beerOrderLineDTO = beerOrderLineMapper.beerOrderLineToBeerOrderLineDTO(beerOrderLine);
        Optional<BeerDTO> beerByUpc = beerService.getBeerByUpc(beerOrderLine.getUpc());

        beerByUpc.ifPresent((beerDTO -> {
            beerOrderLineDTO.setBeerName(beerDTO.getBeerName());
            beerOrderLineDTO.setBeerStyle(beerDTO.getBeerStyle());
            beerOrderLineDTO.setUpc(String.valueOf(beerDTO.getUpc()));
            beerOrderLineDTO.setPrice(beerDTO.getPrice());
            beerOrderLineDTO.setBeerId(beerDTO.getId());
        }));

        return beerOrderLineDTO;
    }
}
