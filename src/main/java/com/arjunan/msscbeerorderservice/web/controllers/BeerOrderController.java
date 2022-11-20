package com.arjunan.msscbeerorderservice.web.controllers;

import com.arjunan.msscbeerorderservice.constants.CommonConstant;
import com.arjunan.msscbeerorderservice.services.BeerOrderService;
import com.arjunan.msscbeerorderservice.web.model.BeerOrderDTO;
import com.arjunan.msscbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers/{customerId}")
public class BeerOrderController {

    private final BeerOrderService beerOrderService;

    public BeerOrderController(BeerOrderService beerOrderService) {
        this.beerOrderService = beerOrderService;
    }

    @GetMapping("orders")
    public ResponseEntity<BeerOrderPagedList> listOrders(@PathVariable("customerId")UUID customerId ,
                                                         @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                         @RequestParam(value = "pageSize" , required = false) Integer pageSize){
        if(pageNumber == null || pageNumber < 0)
            pageNumber = CommonConstant.DEFAULT_PAGE_NUMBER;

        if(pageSize == null || pageSize < 1)
            pageSize = CommonConstant.DEFAULT_PAGE_SIZE;

        BeerOrderPagedList orderPagedList =   beerOrderService.listOrder(customerId , PageRequest.of(pageNumber,pageSize));

        return new ResponseEntity<>(orderPagedList, HttpStatus.OK);
    }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BeerOrderDTO> placeOrder(@PathVariable("customerId") UUID customerId, @RequestBody BeerOrderDTO beerOrderDTO){
        return new ResponseEntity<BeerOrderDTO>(beerOrderService.placeOrder(customerId, beerOrderDTO),HttpStatus.CREATED);
    }
}
