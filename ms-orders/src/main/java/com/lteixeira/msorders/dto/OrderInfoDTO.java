package com.lteixeira.msorders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO implements Serializable {
    private String id;
    private String purchaseOrderId;
    private String customerSsn;
    private String customerName;
    private String customerCredit;
    private String productName;
    private String productPrice;
    private String addressStreet;
    private String addressZip;
    private String purchaseOrderStatus;
    private String action;
}
