package com.arjunan.msscbeerorderservice.services;

import com.arjunan.msscbeerorderservice.web.model.BeerOrderDTO;
import com.arjunan.msscbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface BeerOrderService {
    BeerOrderPagedList listOrder(UUID customerId, Pageable pageable);

    BeerOrderDTO placeOrder(UUID customerId, BeerOrderDTO beerOrderDTO);

    BeerOrderDTO getOrderById(UUID customerId, UUID orderId);
}
