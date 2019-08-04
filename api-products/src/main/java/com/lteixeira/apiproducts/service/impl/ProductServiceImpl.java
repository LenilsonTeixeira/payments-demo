package com.lteixeira.apiproducts.service.impl;

import com.lteixeira.apiproducts.dto.ProductDTO;
import com.lteixeira.apiproducts.exception.NotFoundException;
import com.lteixeira.apiproducts.exception.ProductException;
import com.lteixeira.apiproducts.mapper.ProductMapper;
import com.lteixeira.apiproducts.model.Product;
import com.lteixeira.apiproducts.repository.ProductRepository;
import com.lteixeira.apiproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void save(ProductDTO productDTO) {
        try {
            Product product = productMapper.convertToModel(productDTO);
            this.productRepository.save(product);
        }catch (Exception e){
            throw new ProductException("Erro ao salvar produto");
        }
    }

    @Override
    public List<ProductDTO> findAll() {
        return this.productRepository.findAll().stream()
                .map(productMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(String id, ProductDTO productDTO) {
        this.findById(id);
        productRepository.save(productMapper.convertToModel(productDTO));
    }

    @Override
    public void deleteById(String id) {
        this.findById(id);
        productRepository.deleteById(id);
    }

    @Override
    public Optional<ProductDTO> findById(String id) {
        Optional<Product> productDb = productRepository.findById(id);
        if(productDb.isPresent()){
            return productRepository.findById(id)
                    .map(productMapper::convertToDTO);
        }else{
            throw new NotFoundException("Produto não encontrado");
        }
    }

}
