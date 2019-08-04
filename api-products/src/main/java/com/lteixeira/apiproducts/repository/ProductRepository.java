package com.lteixeira.apiproducts.repository;

import com.lteixeira.apiproducts.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> { }

