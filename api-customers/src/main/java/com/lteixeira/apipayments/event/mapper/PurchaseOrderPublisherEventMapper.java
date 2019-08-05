package com.lteixeira.apicustomers.event.mapper;

import com.lteixeira.apicustomers.event.model.PurchaseOrderEvent;
import com.lteixeira.apicustomers.model.PurchaseOrder;
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
