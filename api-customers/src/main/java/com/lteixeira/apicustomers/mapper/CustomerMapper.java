package com.lteixeira.apicustomers.mapper;

import com.lteixeira.apicustomers.dto.CustomerDTO;
import com.lteixeira.apicustomers.model.Customer;
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
