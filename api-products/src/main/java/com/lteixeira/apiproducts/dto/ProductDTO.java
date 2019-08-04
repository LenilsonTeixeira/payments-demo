package com.lteixeira.apiproducts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id;

    @NotNull(message = "Código é obrigatório")
    private Integer code;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 300, message = "O nome do produto deve ter menos que 300 caracteres")
    private String name;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.00", message = "O valor do produto deve ser maior que R$0,00")
    private BigDecimal price;
}
