package com.lteixeira.apiproducts.event.mapper;

import com.lteixeira.apiproducts.event.model.ProductEvent;
import com.lteixeira.apiproducts.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPublisherEventMapper {

    public ProductEvent map (Product product){
        return ProductEvent.builder()
                .productCode(product.getCode().toString())
                .productName(product.getName())
                .productPrice(product.getPrice().toString())
                .build();
    }
}
