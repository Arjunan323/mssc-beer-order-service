package com.arjunan.msscbeerorderservice.respository;

import com.arjunan.msscbeerorderservice.domain.BeerOrder;
import com.arjunan.msscbeerorderservice.domain.Customer;
import com.arjunan.msscbeerorderservice.enums.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);

    List<BeerOrder> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

    @Lock(LockModeType.PESSIMISTIC_WRITE) //TODO Need to study more about this
    BeerOrder findOneById(UUID id);
}
