package com.lteixeira.apipayments.mapper;

import com.lteixeira.apipayments.dto.AddressDTO;
import com.lteixeira.apipayments.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AddressDTO convertToDTO(Address address){
        return this.modelMapper.map(address, AddressDTO.class);
    }

    public Address convertToModel(AddressDTO addressDTO){ return this.modelMapper.map(addressDTO, Address.class); }
}
