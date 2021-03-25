package com.freeefly.msscbreweryclient.web.client;

import com.freeefly.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerClientTest {
    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        // given
        CustomerDto dto = customerClient.getCustomerById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void testSaveNewCustomer() {
        // given
        CustomerDto customerDto = CustomerDto.builder()
                .name("New customer")
                .build();
        // when
        HttpStatus statusCode = customerClient.saveNewCustomer(customerDto).getStatusCode();
        assertEquals(HttpStatus.CREATED, statusCode);
    }

    @Test
    void testUpdateCustomer() {
        // given
        CustomerDto customerDto = CustomerDto.builder()
                .name("New customer")
                .build();
        // when
        customerClient.updateCustomer(UUID.randomUUID(), customerDto);
    }

    @Test
    public void testDeleteCustomer() {
        // given
        customerClient.deleteCustomer(UUID.randomUUID());
    }


}