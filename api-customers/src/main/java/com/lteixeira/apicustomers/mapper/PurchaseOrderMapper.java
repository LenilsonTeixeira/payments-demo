package com.lteixeira.apicustomers.mapper;

import com.lteixeira.apicustomers.dto.AddressDTO;
import com.lteixeira.apicustomers.dto.CustomerDTO;
import com.lteixeira.apicustomers.dto.PurchaseOrderDTO;
import com.lteixeira.apicustomers.model.Address;
import com.lteixeira.apicustomers.model.Customer;
import com.lteixeira.apicustomers.model.PurchaseOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CustomerMapper customerMapper;


    public PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder){
        CustomerDTO customerDTO = customerMapper.convertToDTO(purchaseOrder.getCustomer());
        AddressDTO addressDTO = addressMapper.convertToDTO(purchaseOrder.getAddress());

        return PurchaseOrderDTO.builder()
                .id(purchaseOrder.getId())
                .customer(customerDTO)
                .address(addressDTO)
                .build();
    }

    public PurchaseOrder convertToModel(PurchaseOrderDTO purchaseOrderDTO){
        Customer customer = customerMapper.convertToModel(purchaseOrderDTO.getCustomer());
        Address address = addressMapper.convertToModel(purchaseOrderDTO.getAddress());

        return PurchaseOrder.builder()
                .id(purchaseOrderDTO.getId())
                .customer(customer)
                .address(address)
                .build();
    }
}
