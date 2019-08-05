package com.lteixeira.apiproducts.service.impl;

import com.lteixeira.apiproducts.dto.ProductDTO;
import com.lteixeira.apiproducts.event.enumeration.ProductPublishActionEnum;
import com.lteixeira.apiproducts.event.service.ProductPublisherService;
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
    private ProductPublisherService productPublisherService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        try {

            Product product = productMapper.convertToModel(productDTO);

            final ProductDTO productDb = productMapper.convertToDTO(this.productRepository.save(product));

            productPublisherService.accept(productMapper.convertToModel(productDb), ProductPublishActionEnum.CREATE);

            return productDb;

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
    public void update(Long id, ProductDTO productDTO) {
        this.findById(id);

        final Product product = productRepository.save(productMapper.convertToModel(productDTO));

        productPublisherService.accept(product, ProductPublishActionEnum.UPDATE);
    }

    @Override
    public void deleteById(Long id) {
        Optional<ProductDTO> productDb = this.findById(id);

        productRepository.deleteById(id);

        if(productDb.isPresent()){
            productPublisherService.accept(Product.builder().code(productDb.get().getCode()).build(),ProductPublishActionEnum.DELETE);
        }

    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        Optional<Product> productDb = productRepository.findById(id);
        if(productDb.isPresent()){
            return productRepository.findById(id)
                    .map(productMapper::convertToDTO);
        }else{
            throw new NotFoundException("Produto não encontrado");
        }
    }

}
