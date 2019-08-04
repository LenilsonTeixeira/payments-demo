package com.lteixeira.apicustomers.mapper;

import com.lteixeira.apicustomers.dto.AddressDTO;
import com.lteixeira.apicustomers.dto.CustomerDTO;
import com.lteixeira.apicustomers.model.Address;
import com.lteixeira.apicustomers.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressMapper addressMapper;

    public CustomerDTO convertToDTO(Customer customer){
        AddressDTO addressDTO = addressMapper.convertToDTO(customer.getAddress());
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .ssn(customer.getSsn())
                .birthDate(customer.getBirthDate())
                .credit(customer.getCredit())
                .addressDTO(addressDTO)
                .build();
    }

    public Customer convertToModel(CustomerDTO customerDTO){
        Address address = addressMapper.convertToModel(customerDTO.getAddressDTO());
        return Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .ssn(customerDTO.getSsn())
                .birthDate(customerDTO.getBirthDate())
                .credit(customerDTO.getCredit())
                .address(address)
                .build();
    }
}
