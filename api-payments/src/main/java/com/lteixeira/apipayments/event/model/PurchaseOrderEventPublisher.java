package com.lteixeira.apipayments.event.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lteixeira.apipayments.event.enumeration.PurchaseOrderPublishActionEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseOrderEventPublisher {
    private final PurchaseOrderEvent payload;
    private final PurchaseOrderPublishActionEnum action;

    public static PurchaseOrderEventPublisher with(final PurchaseOrderEvent payload, final PurchaseOrderPublishActionEnum action) {
        return new PurchaseOrderEventPublisher(payload, action);
    }
}
