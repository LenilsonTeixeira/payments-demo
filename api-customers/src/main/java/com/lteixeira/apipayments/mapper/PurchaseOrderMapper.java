package com.lteixeira.apicustomers.mapper;

import com.lteixeira.apicustomers.dto.AddressDTO;
import com.lteixeira.apicustomers.dto.CustomerDTO;
import com.lteixeira.apicustomers.dto.ProductDTO;
import com.lteixeira.apicustomers.dto.PurchaseOrderDTO;
import com.lteixeira.apicustomers.enumeration.PurchaseOrderStatus;
import com.lteixeira.apicustomers.model.Address;
import com.lteixeira.apicustomers.model.Customer;
import com.lteixeira.apicustomers.model.Product;
import com.lteixeira.apicustomers.model.PurchaseOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PurchaseOrderMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ProductMapper productMapper;

    public PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder){
        CustomerDTO customerDTO = customerMapper.convertToDTO(purchaseOrder.getCustomer());
        AddressDTO addressDTO = addressMapper.convertToDTO(purchaseOrder.getAddress());
        ProductDTO productDTO = productMapper.convertToDTO(purchaseOrder.getProduct());

        return PurchaseOrderDTO.builder()
                .id(purchaseOrder.getId())
                .customer(customerDTO)
                .address(addressDTO)
                .product(productDTO)
                .orderStatus(fillOrderStatus(purchaseOrder.getCustomer().getCredit(), purchaseOrder.getProduct().getPrice()))
                .build();
    }

    public PurchaseOrder convertToModel(PurchaseOrderDTO purchaseOrderDTO){
        Customer customer = customerMapper.convertToModel(purchaseOrderDTO.getCustomer());
        Address address = addressMapper.convertToModel(purchaseOrderDTO.getAddress());
        Product product = productMapper.convertToModel(purchaseOrderDTO.getProduct());

        return PurchaseOrder.builder()
                .id(purchaseOrderDTO.getId())
                .customer(customer)
                .address(address)
                .product(product)
                .orderStatus(fillOrderStatus(purchaseOrderDTO.getCustomer().getCredit(), purchaseOrderDTO.getProduct().getPrice()))
                .build();
    }

    private PurchaseOrderStatus fillOrderStatus(BigDecimal customerCredit, BigDecimal productPrice ) {
        if(customerCredit.compareTo(productPrice) == 1){
            return PurchaseOrderStatus.APPROVED;
        }
        return PurchaseOrderStatus.REJECTED;
    }

}
