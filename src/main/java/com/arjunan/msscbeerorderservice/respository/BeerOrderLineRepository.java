package com.arjunan.msscbeerorderservice.respository;

import com.arjunan.msscbeerorderservice.domain.BeerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderLineRepository extends JpaRepository<BeerOrderLine, UUID> {
}
