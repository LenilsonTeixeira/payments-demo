package com.lteixeira.apicustomers.mapper;

import com.lteixeira.apicustomers.dto.ProductDTO;
import com.lteixeira.apicustomers.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO convertToDTO(Product product){
        return this.modelMapper.map(product, ProductDTO.class);
    }

    public Product convertToModel(ProductDTO productDTO){
        return this.modelMapper.map(productDTO, Product.class);
    }
}
