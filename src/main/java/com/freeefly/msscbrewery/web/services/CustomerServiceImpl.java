package com.freeefly.msscbrewery.web.services;

import com.freeefly.msscbrewery.web.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer getCustomerById(UUID customerId) {
        return Customer.builder()
                .id(UUID.randomUUID())
                .name("deokmu lee")
                .build();
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        return Customer.builder()
                .id(UUID.randomUUID())
                .name("new Customer")
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, Customer customer) {
        log.debug("Updating customer...");
        // TODO impl
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.debug("Deleting customer...");

        // TODO impl
    }
}
