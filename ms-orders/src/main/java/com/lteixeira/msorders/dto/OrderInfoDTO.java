package com.lteixeira.msorders.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lteixeira.msorders.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderInfoDTO implements Serializable {

    private Long id;

    @NotBlank(message = "{order.product.code.required}")
    private Long productCode;

    @NotBlank(message = "{order.product.name.required}")
    private String productName;

    @NotBlank(message = "{order.customer.id.required}")
    private Long customerId;

    @NotEmpty(message = "{order.amount.required}")
    private BigDecimal amount;

    @NotNull(message = "{order.status.required}")
    private OrderStatus status;
}
