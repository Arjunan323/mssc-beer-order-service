package com.arjunan.msscbeerorderservice.bootstrap;

import com.arjunan.msscbeerorderservice.domain.Customer;
import com.arjunan.msscbeerorderservice.respository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class BeerOrderBootstrap implements CommandLineRunner {


    public static final String TASTING_ROOM = "Tasting Room";
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final CustomerRepository customerRepository;

    public BeerOrderBootstrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.findAllByCustomerNameLike(BeerOrderBootstrap.TASTING_ROOM).size() == 0) {
            Customer save = customerRepository.save(Customer.builder()
                    .customerName(TASTING_ROOM)
                    .apiKey(UUID.randomUUID())
                    .build());

            log.info("Tasting Room Customer Id : {} ", save.getId());
        }
    }
}
