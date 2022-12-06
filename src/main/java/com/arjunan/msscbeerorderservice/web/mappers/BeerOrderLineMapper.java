package com.arjunan.msscbeerorderservice.web.mappers;

import com.arjunan.msscbeerorderservice.domain.BeerOrderLine;
import com.arjunan.msscbeerorderservice.web.model.BeerOrderLineDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(value = BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {

    @Mapping(target = "upc", ignore = true)
    @Mapping(target = "beerName", ignore = true)
    BeerOrderLineDTO beerOrderLineToBeerOrderLineDTO(BeerOrderLine beerOrderLine);


    @Mapping(target = "quantityAllocated", ignore = true)
    @Mapping(target = "beerOrder", ignore = true)
    BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDTO beerOrderLineDTO);
}
