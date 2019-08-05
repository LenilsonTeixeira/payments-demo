package com.lteixeira.apicustomers.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderEvent implements Serializable {

    private String purchaseOrderId;
    private String customerSsn;
    private String customerName;
    private String customerCredit;
    private String productName;
    private String productPrice;
    private String addressStreet;
    private String addressZip;
}
