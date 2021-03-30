package com.freeefly.msscbrewery.web.mappers;

import com.freeefly.msscbrewery.domain.Customer;
import com.freeefly.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto dto);

}




