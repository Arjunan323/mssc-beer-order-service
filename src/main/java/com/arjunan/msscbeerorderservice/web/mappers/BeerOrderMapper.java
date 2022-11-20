package com.arjunan.msscbeerorderservice.web.mappers;

import com.arjunan.msscbeerorderservice.domain.BeerOrder;
import com.arjunan.msscbeerorderservice.web.model.BeerOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    @Mapping(target = "customerId", ignore = true)
    BeerOrderDTO beerOrderToBeerOrderDto(BeerOrder beerOrder);

    @Mapping(target = "customer", ignore = true)
    BeerOrder beerOrderDtoToBeerOrder(BeerOrderDTO beerOrderDTO);
}
