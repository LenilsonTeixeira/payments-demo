package com.lteixeira.msorders.repository;

import com.lteixeira.msorders.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo,Long> { }
