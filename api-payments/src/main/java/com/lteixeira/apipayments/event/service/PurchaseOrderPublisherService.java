package com.lteixeira.apipayments.event.service;

import com.lteixeira.apipayments.event.enumeration.PurchaseOrderPublishActionEnum;
import com.lteixeira.apipayments.model.PurchaseOrder;

@FunctionalInterface
public interface PurchaseOrderPublisherService {
    void accept(PurchaseOrder purchaseOrder, PurchaseOrderPublishActionEnum action);
}
