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

    private String customerId;

    private String customerName;

    private String customerSSn;

    private String customerCredit;

    private String productCode;

    private String productName;

    private String productPrice;

    private OrderStatus status;

}
