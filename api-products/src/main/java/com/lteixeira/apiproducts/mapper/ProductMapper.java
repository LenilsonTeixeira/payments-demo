package com.lteixeira.apiproducts.mapper;

import com.lteixeira.apiproducts.dto.ProductDTO;
import com.lteixeira.apiproducts.model.Product;
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
