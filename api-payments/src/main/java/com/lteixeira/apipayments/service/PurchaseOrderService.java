package com.lteixeira.apipayments.service;

import com.lteixeira.apipayments.dto.PurchaseOrderDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {

    PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO);

    List<PurchaseOrderDTO> findAll();

    void update (Integer id, PurchaseOrderDTO purchaseOrderDTO);

    void deleteById (Integer id);

    Optional<PurchaseOrderDTO> findById(Integer id);
}
