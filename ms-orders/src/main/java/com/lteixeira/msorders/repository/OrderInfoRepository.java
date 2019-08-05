package com.lteixeira.msorders.repository;

import com.lteixeira.msorders.model.OrderInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoRepository extends ReactiveMongoRepository<OrderInfo,String> { }
