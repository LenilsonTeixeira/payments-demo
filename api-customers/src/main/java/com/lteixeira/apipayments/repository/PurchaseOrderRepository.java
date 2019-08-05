package com.lteixeira.apicustomers.repository;

import com.lteixeira.apicustomers.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Integer> { }
