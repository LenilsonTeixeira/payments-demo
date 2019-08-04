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

    @NotBlank(message = "Código do produto é obrigatório")
    private Long productCode;

    @NotBlank(message = "Nome do produto é obrigatório")
    private String productName;

    @NotBlank(message = "ID do cliente é obrigatório")
    private Long customerId;

    @NotEmpty(message = "Valor total é obrigatório")
    private BigDecimal amount;

    @NotNull(message = "Status da remessa é obrigatório")
    private OrderStatus status;
}
