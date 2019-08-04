package com.lteixeira.apicustomers.mapper;

import com.lteixeira.apicustomers.dto.AddressDTO;
import com.lteixeira.apicustomers.model.Address;
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
