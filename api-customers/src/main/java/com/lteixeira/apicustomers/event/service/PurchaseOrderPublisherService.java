package com.lteixeira.apicustomers.event.service;

import com.lteixeira.apicustomers.event.enumeration.PurchaseOrderPublishActionEnum;
import com.lteixeira.apicustomers.model.PurchaseOrder;

@FunctionalInterface
public interface PurchaseOrderPublisherService {
    void accept(PurchaseOrder purchaseOrder, PurchaseOrderPublishActionEnum action);
}
