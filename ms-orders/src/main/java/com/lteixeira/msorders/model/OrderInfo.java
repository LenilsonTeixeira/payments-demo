package com.lteixeira.msorders.model;

import com.lteixeira.msorders.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class OrderInfo implements Serializable {
    @Id
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
