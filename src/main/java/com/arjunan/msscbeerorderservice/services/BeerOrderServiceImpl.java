package com.arjunan.msscbeerorderservice.services;

import com.arjunan.msscbeerorderservice.domain.BeerOrder;
import com.arjunan.msscbeerorderservice.domain.Customer;
import com.arjunan.msscbeerorderservice.enums.OrderStatusEnum;
import com.arjunan.msscbeerorderservice.respository.BeerOrderRepository;
import com.arjunan.msscbeerorderservice.respository.CustomerRepository;
import com.arjunan.msscbeerorderservice.web.mappers.BeerOrderMapper;
import com.arjunan.msscbeerorderservice.web.model.BeerOrderDTO;
import com.arjunan.msscbeerorderservice.web.model.BeerOrderPagedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BeerOrderServiceImpl implements  BeerOrderService{

    private final CustomerRepository customerRepository;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    public BeerOrderServiceImpl(CustomerRepository customerRepository, BeerOrderRepository beerOrderRepository, BeerOrderMapper beerOrderMapper) {
        this.customerRepository = customerRepository;
        this.beerOrderRepository = beerOrderRepository;
        this.beerOrderMapper = beerOrderMapper;
    }

    @Override
    public BeerOrderPagedList listOrder(UUID customerId, Pageable pageable) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent()){
            Page<BeerOrder> allByCustomer = beerOrderRepository.findAllByCustomer(customer.get(), pageable);

            return new BeerOrderPagedList(allByCustomer
                    .stream()
                    .map(beerOrderMapper::beerOrderToBeerOrderDto)
                    .collect(Collectors.toList()),
                    PageRequest.of(
                            allByCustomer.getPageable().getPageNumber(),
                            allByCustomer.getPageable().getPageSize()
                    ),
                    allByCustomer.getTotalElements()
            );

        }

        return null;
    }

    @Transactional
    @Override
    public BeerOrderDTO placeOrder(UUID customerId, BeerOrderDTO beerOrderDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            BeerOrder beerOrder = beerOrderMapper.beerOrderDtoToBeerOrder(beerOrderDTO);
            beerOrder.setId(null);
            beerOrder.setCustomer(customerOptional.get());
            beerOrder.setOrderStatus(OrderStatusEnum.NEW);

            beerOrder.getBeerOrderLines().forEach(beerOrderLine -> beerOrderLine.setBeerOrder(beerOrder));

            BeerOrder savedBeer = beerOrderRepository.saveAndFlush(beerOrder);

            log.debug("Saved Beer Order: "+ savedBeer.getId());

            return beerOrderMapper.beerOrderToBeerOrderDto(savedBeer);

        }
        return null;
    }
}