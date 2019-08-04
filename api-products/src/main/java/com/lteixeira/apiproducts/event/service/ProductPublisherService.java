package com.lteixeira.apiproducts.event.service;

import com.lteixeira.apiproducts.event.enumeration.ProductPublishActionEnum;
import com.lteixeira.apiproducts.model.Product;

@FunctionalInterface
public interface ProductPublisherService {
    void accept(Product product, ProductPublishActionEnum action);
}
