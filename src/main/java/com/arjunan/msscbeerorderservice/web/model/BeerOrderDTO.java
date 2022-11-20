package com.arjunan.msscbeerorderservice.web.model;

import com.arjunan.msscbeerorderservice.enums.OrderStatusEnum;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDTO extends  BaseItem {

    @Builder
    public BeerOrderDTO(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, UUID customerId, String customerRef, List<BeerOrderLineDTO> beerOrderLines, OrderStatusEnum orderStatus, String orderStatusCallBackUrl) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.customerRef = customerRef;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallBackUrl = orderStatusCallBackUrl;
    }

    private UUID customerId;
    private String customerRef;
    private List<BeerOrderLineDTO> beerOrderLines;
    private OrderStatusEnum orderStatus;
    private String orderStatusCallBackUrl;
}
