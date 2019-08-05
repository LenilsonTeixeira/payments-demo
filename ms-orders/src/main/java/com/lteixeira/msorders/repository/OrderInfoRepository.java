package com.lteixeira.msorders.repository;

import com.lteixeira.msorders.model.OrderInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoRepository extends MongoRepository<OrderInfo,String> { }
