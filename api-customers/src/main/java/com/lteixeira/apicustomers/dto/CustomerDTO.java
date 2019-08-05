package com.lteixeira.apicustomers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {

    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "O número do CPF é obrigatório")
    private String ssn;

    @NotNull(message = "Data de nascimento é obrigatória")
    private Date birthDate;

    @NotNull(message = "Valor do crédito é obrigatório")
    private BigDecimal credit;
}
