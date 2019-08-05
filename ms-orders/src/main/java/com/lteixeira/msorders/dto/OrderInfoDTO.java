package com.lteixeira.msorders.dto;

import com.lteixeira.msorders.enumeration.OrderStatus;
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

    private String customerId;

    private String customerName;

    private String customerSSn;

    private String customerCredit;

    private String productCode;

    private String productName;

    private String productPrice;

    private OrderStatus status;
}
