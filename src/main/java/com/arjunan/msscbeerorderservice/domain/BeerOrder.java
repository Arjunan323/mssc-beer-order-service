package com.arjunan.msscbeerorderservice.domain;

import com.arjunan.msscbeerorderservice.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class BeerOrder extends  BaseEntity {

    @Builder
    public BeerOrder(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerRef,
                     Customer customer, Set<BeerOrderLine> beerOrderLines, OrderStatusEnum orderStatusEnum, String orderStatusCallBackUrl) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerRef = customerRef;
        this.customer = customer;
        this.beerOrderLines = beerOrderLines;
        this.orderStatusEnum = orderStatusEnum;
        this.orderStatusCallBackUrl = orderStatusCallBackUrl;
    }

    private String customerRef;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "beerOrder", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN) //TODO Need to check internal logic
    private Set<BeerOrderLine> beerOrderLines;

    private OrderStatusEnum orderStatusEnum = OrderStatusEnum.NEW;

    private String orderStatusCallBackUrl;


}
