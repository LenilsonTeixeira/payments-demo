package com.lteixeira.apiproducts.service;

import com.lteixeira.apiproducts.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDTO save(ProductDTO productDTO);

    List<ProductDTO> findAll();

    void update (Long id, ProductDTO productDTO);

    void deleteById (Long id);

    Optional<ProductDTO> findById(Long id);
}
