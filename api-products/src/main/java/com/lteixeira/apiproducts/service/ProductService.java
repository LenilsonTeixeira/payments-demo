package com.lteixeira.apiproducts.service;

import com.lteixeira.apiproducts.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(ProductDTO productDTO);

    List<ProductDTO> findAll();

    void update (String id, ProductDTO productDTO);

    void deleteById (String id);

    Optional<ProductDTO> findById(String id);
}
