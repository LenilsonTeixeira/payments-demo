package com.lteixeira.apipayments.mapper;

import com.lteixeira.apipayments.dto.CustomerDTO;
import com.lteixeira.apipayments.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDTO(Customer customer){
        return this.modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer convertToModel(CustomerDTO customerDTO){ return this.modelMapper.map(customerDTO, Customer.class); }
}
