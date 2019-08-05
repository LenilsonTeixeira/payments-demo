package com.lteixeira.apipayments.event.mapper;

import com.lteixeira.apipayments.event.model.PurchaseOrderEvent;
import com.lteixeira.apipayments.model.PurchaseOrder;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderPublisherEventMapper {

    public PurchaseOrderEvent map (PurchaseOrder purchaseOrder){
        return PurchaseOrderEvent.builder()
                .purchaseOrderId(purchaseOrder.getId().toString())
                .customerName(purchaseOrder.getCustomer().getName())
                .customerSsn(purchaseOrder.getCustomer().getSsn())
                .customerCredit(purchaseOrder.getCustomer().getCredit().toString())
                .productName(purchaseOrder.getProduct().getName())
                .productPrice(purchaseOrder.getProduct().getPrice().toString())
                .addressStreet(purchaseOrder.getAddress().getStreet())
                .addressZip(purchaseOrder.getAddress().getZip())
                .purchaseOrderStatus(purchaseOrder.getOrderStatus().name())
                .build();
    }
}
